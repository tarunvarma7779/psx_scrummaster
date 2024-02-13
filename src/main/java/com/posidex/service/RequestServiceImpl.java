package com.posidex.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.entity.Request;
import com.posidex.entity.User;
import com.posidex.repository.RequestRepository;
import com.posidex.repository.UserRepository;
import com.posidex.util.LoginUtils;

@Service
public class RequestServiceImpl implements RequestServiceI {

	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void addRequest(Request request) {
		requestRepository.save(request);
	}

	@Override
	public List<Request> getRequestByUserName(String username) {
		return requestRepository.getRequestRaisedToUsername(username);
	}

	@Override
	public List<ProfileRequestDTO> getProfileRequests(String currentUser) {
		List<ProfileRequestDTO> retValue = new ArrayList<>();
		List<User> inactiveUserList= userRepository.getInactiveUserforReporting(currentUser);
		inactiveUserList.forEach(x->{
			retValue.add(new ProfileRequestDTO(x,getProfileActivationRequests(x.getUsername())));
		});
		return retValue;
	}
	
	public Request getProfileActivationRequests(String username) {
		return requestRepository.getRequestsBasedOnUserAndOperation(username,LoginUtils.USER_ACTIVATION );
	}

}
