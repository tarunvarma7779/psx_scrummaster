package com.posidex.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.User;
import com.posidex.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

//	public UserServiceImpl(UserDAO ud) {
//		this.userDAO = ud;
//	}

	@Override
	public User getUserByUserName(String username) {
		Optional<User> result = userRepository.findById(username);
		User user = null;
		if (result.isPresent()) {
			user = result.get();
		}
		return user;
	}

	@Transactional
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

}
