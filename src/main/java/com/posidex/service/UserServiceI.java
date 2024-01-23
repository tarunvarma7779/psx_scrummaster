package com.posidex.service;

import java.sql.SQLException;

import com.posidex.entity.User;

public interface UserServiceI {

	User getUserByUserName(String username);
	
	boolean empIdExists(String empId) throws SQLException;

	void addUser(User user);

	boolean userExists(String userId);

}
