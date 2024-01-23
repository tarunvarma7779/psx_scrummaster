package com.posidex.dao;

import java.util.List;

import com.posidex.entity.User;

public interface UserDAO {

	List<User> getUserList();

	User getUserByUsername(String username);
	
	void addUser(User user);
}
