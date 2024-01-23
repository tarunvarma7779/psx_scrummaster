package com.posidex.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.entity.User;
import com.posidex.service.UserServiceI;
import com.posidex.util.LoginUtils;
import com.posidex.util.StringEncrypter;
import com.posidex.util.StringEncrypter.EncryptionException;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@RestController
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class.getName());
	
	@Autowired
	private LoginUtils loginUtils;
	@Autowired
	private UserServiceI userService;

	@PostMapping("/validateLogin")
	public String validateLogin(@RequestBody Map<String, String> dataMap) throws EncryptionException {
		return loginUtils.validateUser(dataMap);
	}

	@PostMapping("/createUser")
	public Map<String, String> createUser(@RequestBody User user) {
		Map<String,String> response = new LinkedHashMap<>();
		try{
			try {
				String encryptedPassword = StringEncrypter.encrypt(user.getPassword());
				user.setPassword(encryptedPassword);			
			}catch (Exception e) {
				logger.info("password already encrypted");
			}
			userService.addUser(user);
		}catch (Exception e) {
			response.put("Status", "Failed");
			response.put("Message", e.getLocalizedMessage());
			return  response;
		}
		response.put("Status", "Success");
		response.put("Message", "User created Successfully");
		return  response;
//		return ResponseEntity.ok("{\"Status\":\"Success\"}");
	}
}