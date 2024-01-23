package com.posidex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.ResponseDTO;
import com.posidex.entity.User;
import com.posidex.util.LoginUtils;
import com.posidex.util.StringEncrypter.EncryptionException;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@RestController
public class LoginController {

	@Autowired
	private LoginUtils loginUtils;

	@PostMapping("/validateLogin")
	public String validateLogin(@RequestBody Map<String, String> dataMap) throws EncryptionException {
		return loginUtils.validateLoginUser(dataMap);
	}

	@PostMapping("/createUser")
	public ResponseDTO createUser(@RequestBody User user) {
		ResponseDTO responseDTO = new ResponseDTO();
		return loginUtils.validateCreateUser(user, responseDTO);
	}
}