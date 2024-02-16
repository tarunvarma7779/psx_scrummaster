package com.posidex.service;

import java.util.List;

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

	@Override
	public List<UserDetails> getReportees(String username) {
		UserDetails currentUser = getUserDetailsByUsername(username);
		return userDetailsRepository.getReporteesByEmpID(currentUser.getEmpId());
	}

}
