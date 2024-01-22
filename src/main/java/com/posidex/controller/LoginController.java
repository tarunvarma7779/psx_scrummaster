package com.posidex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.entity.User;
import com.posidex.service.TaskService;
import com.posidex.service.UserService;
import com.posidex.util.LoginUtils;
import com.posidex.util.StringEncrypter.EncryptionException;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginUtils loginUtils;
	
	@PostMapping("/validateLogin")
	public String validateLogin(@RequestBody Map<String, String> dataMap) throws EncryptionException {
		return loginUtils.validateUser(dataMap);
	}
	
	@PostMapping("/createUser")
	public String createUser(@RequestBody User user) throws EncryptionException {
		loginUtils.addUser(user);
		return "User Added Successfully";
	}
}