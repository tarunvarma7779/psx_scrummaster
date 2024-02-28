package com.posidex.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.posidex.dto.CreateUserDTO;
import com.posidex.dto.JwtRequest;
import com.posidex.dto.JwtResponse;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.UserActivation;
import com.posidex.entity.User;
import com.posidex.entity.UserDetails;
import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;
import com.posidex.service.RequestServiceI;
import com.posidex.service.UserDetailsServiceI;
import com.posidex.service.UserOpsServiceI;
import com.posidex.service.UserServiceI;
import com.posidex.util.StringEncrypter.EncryptionException;

@Component
public class LoginUtils {

	private static Logger logger = Logger.getLogger(LoginUtils.class.getName());

	@Autowired
	private UserServiceI userService;
	@Autowired
	private UserDetailsServiceI userDetailsService;
	@Autowired
	private UserOpsServiceI userOpsService;
	@Autowired
	private RequestServiceI requestService;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private Environment environment;

	public Object[] getRecentUnSuccessfulLogInAttempts(String username) {
		List<UserOps> userOpsList = userOpsService.getUserOpsByUsername(username);
		Collections.sort(userOpsList, new Comparator<UserOps>() {
			@Override
			public int compare(UserOps o1, UserOps o2) {
				return (o2.getUserOpsIdentity().getOperationTime()
						.compareTo(o1.getUserOpsIdentity().getOperationTime()));
			}
		});
		List<UserOps> lastUnsuccessful = new ArrayList<>();
		for (UserOps op : userOpsList) {
			if (op.getOperationType().equals(CommonStringUtils.LOGOUT)
					|| op.getOperationType().equals(CommonStringUtils.LOGIN_SUCCESS)) {
				break;
			} else {
				lastUnsuccessful.add(op);
			}
		}
		return new Object[] { lastUnsuccessful.size() + 1 };
	}

	public Long getRecentUnSuccessfulLogInTime(String username) {
		List<UserOps> userOpsList = userOpsService.getUserOpsByUsername(username);
		Collections.sort(userOpsList, new Comparator<UserOps>() {
			@Override
			public int compare(UserOps o1, UserOps o2) {
				return (o2.getUserOpsIdentity().getOperationTime()
						.compareTo(o1.getUserOpsIdentity().getOperationTime()));
			}
		});
		for (UserOps op : userOpsList) {
			if (op.getOperationType().equals(CommonStringUtils.LOGIN_FAILED)) {
				return op.getUserOpsIdentity().getOperationTime().getTime();
			}
		}
		return null;
	}

	public JwtResponse loginUser(JwtRequest request) {
		JwtResponse response = new JwtResponse();
		UserOpsIdentity userOpsIdentity = new UserOpsIdentity(request.getUsername(), new Date());
		UserOps userOps = new UserOps();
		userOps.setUserOpsIdentity(userOpsIdentity);
		User user = userService.getUserByUserName(request.getUsername());
		if (user != null && user.getActive() == 1) {
			validateUserCredentials(user, request, response, userOps);
		} else {
			userOps.setOperationType(CommonStringUtils.LOGIN_FAILED);
			userOps.setRole((user != null && user.getActive() == 0) ? user.getRole() : CommonStringUtils.INVALID);
			userOpsService.addUserOps(userOps);
			response.setJwtToken(null);
			response.setMessage(
					(user != null && user.getActive() == 0) ? "Username not activated" : "Invalid Username");
			response.setStatusCode((user != null && user.getActive() == 0) ? 540 : 520);
			response.setUserDetails(null);
		}
		return response;
	}

	private void validateUserCredentials(User user, JwtRequest request, JwtResponse response, UserOps userOps) {
		long disableLoginMillis = Long.parseLong(environment.getProperty("disableLoginMillis"));
		final String token = jwtUtils.generateToken(user);
		Object[] array = getRecentUnSuccessfulLogInAttempts(user.getUsername());
		int loginAttempt = (int) array[0];
		Long timeFromLastInvalidLogin = (loginAttempt > 1)
				? (jwtUtils.getGenerationDateFromToken(token).getTime()
						- getRecentUnSuccessfulLogInTime(user.getUsername()))
				: disableLoginMillis + 1;
		if ((loginAttempt > 3) && timeFromLastInvalidLogin < disableLoginMillis) {
			response.setJwtToken(null);
			response.setMessage("Wait for " + (disableLoginMillis - timeFromLastInvalidLogin) / 1000 + " secs");
			response.setStatusCode(530);
			response.setUserDetails(null);
		} else {
			if (validatePassword(request.getPassword(), user)) {
				userOps.setOperationType(CommonStringUtils.LOGIN_SUCCESS);
				userOps.setRole(user.getRole());
				userOpsService.addUserOps(userOps);
				response.setJwtToken(token);
				response.setMessage("Logged Successfully");
				response.setStatusCode(200);
				response.setUserDetails(userDetailsService.getUserDetailsByUsername(user.getUsername()));
			} else {
				userOps.setOperationType(CommonStringUtils.LOGIN_FAILED);
				userOps.setRole(user.getRole());
				userOpsService.addUserOps(userOps);
				response.setJwtToken(null);
				response.setMessage("Password Incorrect " + loginAttempt + " time");
				response.setStatusCode(510);
				response.setUserDetails(null);
			}
		}
	}

	private boolean validatePassword(String password, User user) {
		String userPassword = user.getPassword();
		try {
			userPassword = StringEncrypter.decrypt(userPassword);
		} catch (EncryptionException e) {
			logger.info("Password already decrypted");
		}
		return password.equals(userPassword);
	}

	public ResponseDTO createUser(CreateUserDTO createUser) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean userExists = userService.userExists(createUser.getUsername());
			boolean empIdExists = userService.empIdExists(createUser.getEmpId());
			boolean reportingIdExists = userService.userExists(createUser.getReportingTo());
			if (userExists || empIdExists || !reportingIdExists) {
				if (userExists) {
					responseDTO.setMessage("UserId already exists");
					responseDTO.setStatus(CommonStringUtils.FAILED);
					responseDTO.setStatusCode(410);
					return responseDTO;
				} else if (empIdExists) {
					responseDTO.setMessage("EmpId already exists");
					responseDTO.setStatus(CommonStringUtils.FAILED);
					responseDTO.setStatusCode(420);
					return responseDTO;
				} else {
					responseDTO.setMessage("Reporting id doesnt exists");
					responseDTO.setStatus(CommonStringUtils.FAILED);
					responseDTO.setStatusCode(430);
					return responseDTO;
				}
			}
			if (StringEncrypter.isPasswordDecrypted(createUser.getPassword())) {
				createUser.setPassword(StringEncrypter.encrypt(createUser.getPassword()));
			}
			User user = fillUser(createUser);
			UserDetails userDetails = fillUserDetails(createUser);
			if (user.getActive() == 0) {
				UserActivation request = new UserActivation();
				request.setRequestId("req_" + System.currentTimeMillis());
				request.setRaisedBy(userDetails.getEmpId());
				request.setRaisedTo(userDetails.getReportingTo());
				request.setOperationTime(new Date());
				request.setActive(1);
				requestService.addRequest(request);
			}
			userService.addUser(user);
			userDetailsService.addUserDetails(userDetails);
			responseDTO.setMessage("User Added Successfully");
			responseDTO.setStatus(CommonStringUtils.SUCCESS);
			responseDTO.setStatusCode(200);
		} catch (Exception e) {
			responseDTO.setMessage(e.getLocalizedMessage());
			responseDTO.setStatus(CommonStringUtils.FAILED);
			responseDTO.setStatusCode(100);
			return responseDTO;
		}
		return responseDTO;
	}

	private User fillUser(CreateUserDTO createUser) {
		User user = new User();
		user.setCreatedOn(new Date());
		user.setPassword(createUser.getPassword());
		user.setRole(createUser.getDesignation());
		user.setUsername(createUser.getUsername());
		if (user.getRole().equals("Project Manager")) {
			user.setLocked(0);
			user.setActive(1);
			user.setApprovedOn(new Date());
			user.setActionBy(user.getUsername());
			user.setReason("Auto approval for designation of project manager and above");
		} else {
			user.setLocked(0);
			user.setActive(0);
		}
		return user;
	}

	private UserDetails fillUserDetails(CreateUserDTO createUser) {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmpId(createUser.getEmpId());
		userDetails.setDepartmentName(createUser.getDepartmentName());
		userDetails.setDesignation(createUser.getDesignation());
		userDetails.setEmailId(createUser.getEmailId());
		userDetails.setFirstName(createUser.getFirstName());
		userDetails.setGender(createUser.getGender());
		userDetails.setLastName(createUser.getLastName());
		userDetails.setReportingTo(createUser.getReportingTo());
		userDetails.setUsername(createUser.getUsername());
		return userDetails;
	}
}