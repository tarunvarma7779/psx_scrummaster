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

import com.posidex.dto.JwtRequest;
import com.posidex.dto.JwtResponse;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.Request;
import com.posidex.entity.User;
import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;
import com.posidex.service.RequestServiceI;
import com.posidex.service.UserOpsServiceI;
import com.posidex.service.UserServiceI;
import com.posidex.util.StringEncrypter.EncryptionException;

@Component
public class LoginUtils {

	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";
	public static final String LOGIN_SUCCESS = "LOGIN SUCCESS";
	public static final String LOGIN_FAILED = "LOGIN FAILED";
	public static final String LOGOUT = "LOGOUT";
	public static final String INVALID = "INVALID";
	public static final String USER_ACTIVATION = "USER ACTIVATION";

	private static Logger logger = Logger.getLogger(LoginUtils.class.getName());

	@Autowired
	private UserServiceI userService;
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
			if (op.getOperationType().equals(LoginUtils.LOGOUT)
					|| op.getOperationType().equals(LoginUtils.LOGIN_SUCCESS)) {
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
			if (op.getOperationType().equals(LoginUtils.LOGIN_FAILED)) {
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
			userOps.setOperationType(LOGIN_FAILED);
			userOps.setRole((user != null && user.getActive() == 0) ? user.getRole() : INVALID);
			userOpsService.addUserOps(userOps);
			response.setJwtToken(null);
			response.setMessage(
					(user != null && user.getActive() == 0) ? "Username not activated" : "Invalid Username");
			response.setStatusCode((user != null && user.getActive() == 0) ? 540 : 520);
			response.setUser(null);
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
			response.setUser(null);
		} else {
			if (validatePassword(request.getPassword(), user)) {
				userOps.setOperationType(LOGIN_SUCCESS);
				userOps.setRole(user.getRole());
				userOpsService.addUserOps(userOps);
				response.setJwtToken(token);
				response.setMessage("Logged Successfully");
				response.setStatusCode(200);
				response.setUser(user);
			} else {
				userOps.setOperationType(LOGIN_FAILED);
				userOps.setRole(user.getRole());
				userOpsService.addUserOps(userOps);
				response.setJwtToken(null);
				response.setMessage("Password Incorrect " + loginAttempt + " time");
				response.setStatusCode(510);
				response.setUser(null);
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

	public ResponseDTO createUser(User user) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean userExists = userService.userExists(user.getUsername());
			boolean empIdExists = userService.empIdExists(user.getEmpId());
			boolean reportingIdExists = userService.userExists(user.getReportingTo());
			if (userExists || empIdExists || !reportingIdExists) {
				if (userExists) {
					responseDTO.setMessage("UserId already exists");
					responseDTO.setStatus(FAILED);
					responseDTO.setStatusCode(410);
					return responseDTO;
				} else if (empIdExists) {
					responseDTO.setMessage("EmpId already exists");
					responseDTO.setStatus(FAILED);
					responseDTO.setStatusCode(420);
					return responseDTO;
				} else {
					responseDTO.setMessage("Reporting id doesnt exists");
					responseDTO.setStatus(FAILED);
					responseDTO.setStatusCode(430);
					return responseDTO;
				}
			}
			if (StringEncrypter.isPasswordDecrypted(user.getPassword())) {
				user.setPassword(StringEncrypter.encrypt(user.getPassword()));
			}
			if(user.getActive()==0) {
				Request request = new Request();
				request.setRequestId("req_" + System.currentTimeMillis());
				request.setRaisedBy(user.getUsername());
				request.setRaisedTo(user.getReportingTo());
				request.setOperationType(USER_ACTIVATION);
				request.setOperationTime(new Date());
				request.setActive(1);
				requestService.addRequest(request);
			}
			userService.addUser(user);
			responseDTO.setMessage("User Added Successfully");
			responseDTO.setStatus(SUCCESS);
			responseDTO.setStatusCode(200);
		} catch (Exception e) {
			responseDTO.setMessage(e.getLocalizedMessage());
			responseDTO.setStatus(FAILED);
			responseDTO.setStatusCode(100);
			return responseDTO;
		}
		return responseDTO;
	}
}