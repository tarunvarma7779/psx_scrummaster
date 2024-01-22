package com.posidex.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.entity.User;
import com.posidex.service.UserService;
import com.posidex.util.StringEncrypter.EncryptionException;
@Component
public class LoginUtils {
	
	@Autowired
	private UserService userService;
	
	public LoginUtils(UserService userService) {
		this.userService=userService;
	} 
	
	public void addUser(User user) throws EncryptionException {
		user.setPassword(StringEncrypter.encrypt(user.getPassword()));
		userService.addUser(user);		
	}
	
	public String validateUser(Map<String,String> dataMap) throws EncryptionException   {
		User user = userService.getUserByUserName(dataMap.get("username"));
		if(user!=null) {
			if(StringEncrypter.decrypt(user.getPassword()).equals(dataMap.get("password"))) {
				return "Login Successfull";
			}else {
				return "Incorrect Password";
			}
		}else {
			return "Invalid Username";
		}
	}
}
