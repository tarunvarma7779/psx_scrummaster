package com.posidex.util;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.entity.User;
import com.posidex.entity.UserOps;
import com.posidex.entity.UserOpsIdentity;
import com.posidex.service.UserOpsServiceI;
import com.posidex.service.UserServiceI;
import com.posidex.util.StringEncrypter.EncryptionException;

@Component
public class LoginUtils {

	@Autowired
	private UserServiceI userService;
	@Autowired
	private UserOpsServiceI userOpsService;

	public String validateUser(Map<String, String> dataMap) throws EncryptionException {
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
}
