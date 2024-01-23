package com.posidex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.posidex.dao.UserDAO;
import com.posidex.entity.User;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public UserServiceImpl(UserDAO ud) {
		this.userDAO = ud;
	}

	@Override
	public User getUserByUserName(String username) {
		return userDAO.getUserByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getUserList();
	}

	@Override
	public void addUser(User user) {
		userDAO.addUser(user);
	}

}
