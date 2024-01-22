package com.posidex.service;

import java.util.List;

import com.posidex.entity.User;

public interface UserService {

	User getUserByUserName(String username);

	List<User> getAllUsers();
	
	void addUser(User user);

}
