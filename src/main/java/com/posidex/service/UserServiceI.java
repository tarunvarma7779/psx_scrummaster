package com.posidex.service;

import com.posidex.entity.User;

public interface UserServiceI {

	User getUserByUserName(String username);

	void addUser(User user);

}
