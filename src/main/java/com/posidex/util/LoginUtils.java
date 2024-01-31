package com.posidex.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.dto.JwtRequest;
import com.posidex.dto.JwtResponse;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.User;
import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;
import com.posidex.service.UserOpsServiceI;
import com.posidex.service.UserServiceI;
import com.posidex.util.StringEncrypter.EncryptionException;

@Component
public class LoginUtils {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILED = "FAILED";
	private static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	private static final String LOGIN_FAILED = "LOGIN_FAILED";

	@Autowired
	private UserServiceI userService;
	@Autowired
	private UserOpsServiceI userOpsService;
	@Autowired
	private JwtUtils jwtUtils;

	public JwtResponse validateLoginUser(JwtRequest request) throws EncryptionException {
		JwtResponse response = new JwtResponse();
		User user = userService.getUserByUserName(request.getUsername());
		if (user != null) {
			if(user.getActive()==1) {
				if (StringEncrypter.decrypt(user.getPassword()).equals(request.getPassword())) {
					userOpsService.addUserOps(new UserOps(new UserOpsIdentity(request.getUsername(), new Date()),LOGIN_SUCCESS, user.getRole()));
					response.setJwtToken(jwtUtils.generateToken(user));
					response.setStatusCode(200);
					response.setMessage("Logged successfully");
					response.setUser(user);				
					return response;
				} else {
					userOpsService.addUserOps(new UserOps(new UserOpsIdentity(request.getUsername(), new Date()),LOGIN_FAILED, user.getRole()));
					response.setJwtToken(null);
					response.setStatusCode(430);
					response.setMessage("Incorrect Password");
					response.setUser(null);
					return response;
				}
			}
			else {
				userOpsService.addUserOps(new UserOps(new UserOpsIdentity(request.getUsername(), new Date()),LOGIN_FAILED, user.getRole()));
				response.setJwtToken(null);
				response.setStatusCode(460);
				response.setMessage("User Not Activated");
				response.setUser(null);
				return response;
			}			
		} else {
			userOpsService.addUserOps(new UserOps(new UserOpsIdentity(request.getUsername(), new Date()), LOGIN_FAILED, "Invalid"));
			response.setJwtToken(null);
			response.setStatusCode(440);
			response.setMessage("Invalid Credentials");
			response.setUser(null);
			return response;
		}
	}

	public ResponseDTO validateCreateUser(User user) {
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
				} else if(empIdExists) {
					responseDTO.setMessage("EmpId already exists");
					responseDTO.setStatus(FAILED);
					responseDTO.setStatusCode(420);
					return responseDTO;
				} else {
					responseDTO.setMessage("Reporting id doesnt exists");
					responseDTO.setStatus(FAILED);
					responseDTO.setStatusCode(450);
					return responseDTO;
				}
			}
			if (StringEncrypter.isPasswordDecrypted(user.getPassword())) {
				user.setPassword(StringEncrypter.encrypt(user.getPassword()));
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