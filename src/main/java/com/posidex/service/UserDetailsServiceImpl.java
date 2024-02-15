package com.posidex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.UserDetails;
import com.posidex.repository.UserDetailsRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceI {

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Transactional
	@Override
	public void addUserDetails(UserDetails userDetails) {
		userDetailsRepository.save(userDetails);
	}

	@Override
	public UserDetails getUserDetailsByUsername(String username) {
		return userDetailsRepository.getUserDetailsByUsername(username);
	}

}
