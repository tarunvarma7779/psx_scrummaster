package com.posidex.service;

import java.sql.SQLException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.posidex.entity.User;

public interface UserServiceI extends UserDetailsService {

	User getUserByUserName(String username);
	
	boolean empIdExists(String empId) throws SQLException;

	void addUser(User user);

	boolean userExists(String userId);

}