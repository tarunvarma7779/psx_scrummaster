package com.posidex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.CreateUserDTO;
import com.posidex.dto.JwtRequest;
import com.posidex.dto.JwtResponse;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.User;
import com.posidex.util.LoginUtils;

@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private LoginUtils loginUtils;

	@PostMapping("/validateLogin")
	public JwtResponse validateLogin(@RequestBody JwtRequest request) {
		return loginUtils.loginUser(request);
	}

	@PostMapping("/createUser")
	public ResponseDTO createUser(@RequestBody CreateUserDTO createUser) {
		return loginUtils.createUser(createUser);
	}

}