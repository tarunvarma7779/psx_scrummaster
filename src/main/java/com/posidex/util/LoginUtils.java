package com.posidex.util;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.dto.ResponseDTO;
import com.posidex.entity.User;
import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;
import com.posidex.service.UserOpsServiceI;
import com.posidex.service.UserServiceI;
import com.posidex.util.StringEncrypter.EncryptionException;

@Component
public class LoginUtils {

	private static Logger logger = Logger.getLogger(LoginUtils.class.getName());
	private static final String success = "SUCCESS";
	private static final String failed = "FAILED";
	
	@Autowired
	private UserServiceI userService;
	@Autowired
	private UserOpsServiceI userOpsService;

	public String validateLoginUser(Map<String, String> dataMap) throws EncryptionException {
		User user = userService.getUserByUserName(dataMap.get("username"));
		if (user != null) {
			if (StringEncrypter.decrypt(user.getPassword()).equals(dataMap.get("password"))) {
				userOpsService.addUserOps(new UserOps(new UserOpsIdentity(dataMap.get("username"), new Date()),
						"LOGIN_SUCCESS", user.getRole()));
				return "Login Successfull";
			} else {
				userOpsService.addUserOps(new UserOps(new UserOpsIdentity(dataMap.get("username"), new Date()),
						"LOGIN_FAILED", user.getRole()));
				return "Incorrect Password";
			}
		} else {
			userOpsService.addUserOps(
					new UserOps(new UserOpsIdentity(dataMap.get("username"), new Date()), "LOGIN_FAILED", "Invalid"));
			return "Invalid Username";
		}
	}

	public ResponseDTO validateCreateUser(User user, ResponseDTO responseDTO) {
		try {
			boolean userExists = userService.userExists(user.getUserId());
			boolean empIdExists = userService.empIdExists(user.getEmpId());
			if (userExists||empIdExists) {
				if(userExists) {
					responseDTO.setMessage("UserId already exists");
					responseDTO.setStatus(failed);
					responseDTO.setStatusCode(410);
					return responseDTO;
				}
				else if(empIdExists) {
					responseDTO.setMessage("EmpId already exists");
					responseDTO.setStatus(failed);
					responseDTO.setStatusCode(420);
					return responseDTO;
				}				
			}
			try {
				String encryptedPassword = StringEncrypter.encrypt(user.getPassword());
				user.setPassword(encryptedPassword);
			} catch (EncryptionException e) {
				logger.info("password already encrypted");
			}
			userService.addUser(user);
			responseDTO.setMessage("User Added Successfully");
			responseDTO.setStatus(success);
			responseDTO.setStatusCode(200);
		} catch (Exception e) {
			responseDTO.setMessage(e.getLocalizedMessage());
			responseDTO.setStatus(failed);
			responseDTO.setStatusCode(100);
			return responseDTO;
		}
		return responseDTO;
	}
}
