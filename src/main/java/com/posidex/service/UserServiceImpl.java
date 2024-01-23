package com.posidex.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.User;
import com.posidex.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserServiceI {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	BasicDataSource dataSource;

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

	@Override
	public boolean userExists(String userId) {
		return userRepository.existsById(userId);
	}

	@Override
	public boolean empIdExists(String empId) throws SQLException {
		try (Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement("select * from psx_users where emp_id = '"+empId+"'");
				ResultSet rs=ps.executeQuery();) {
			if(rs.next()) {
				return true;
			}
		}
		return false;
	}

}
