package com.posidex.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.Request;
import com.posidex.entity.User;
import com.posidex.repository.RequestRepository;
import com.posidex.repository.UserRepository;
import com.posidex.util.LoginUtils;

import jakarta.transaction.Transactional;

@Service
public class RequestServiceImpl implements RequestServiceI {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserServiceI userService;

	@Transactional
	@Override
	public void addRequest(Request request) {
		requestRepository.save(request);
	}

	@Override
	public Request getRequestByRequestID(String requestId) {
		Optional<Request> result = requestRepository.findById(requestId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<ProfileRequestDTO> getProfileRequests(String currentUser) {
		List<ProfileRequestDTO> retValue = new ArrayList<>();
		List<User> inactiveUserList = userRepository.getInactiveUserforReporting(currentUser);
		inactiveUserList
				.forEach(x -> retValue.add(new ProfileRequestDTO(x, getProfileActivationRequests(x.getUsername()))));
		return retValue;
	}

	public Request getProfileActivationRequests(String username) {
		return requestRepository.getRequestsBasedOnUserAndOperation(username, LoginUtils.USER_ACTIVATION);
	}

	@Override
	public ResponseDTO approveUser(Map<String, String> dataMap) {
		ResponseDTO response = new ResponseDTO();
		try {
			User user = userService.getUserByUserName(dataMap.get("username"));
			Request request = getRequestByRequestID(dataMap.get("requestId"));
			user.setActive(1);
			user.setLocked(0);
			user.setApprovedOn(new Date());
			user.setReason(dataMap.get("message"));
			request.setActive(0);
			userRepository.save(user);
			requestRepository.save(request);
			response.setMessage("User Activated");
			response.setStatusCode(200);
			response.setStatus(LoginUtils.SUCCESS);
		} catch (Exception e) {
			response.setMessage("User Activation Failed");
			response.setStatusCode(100);
			response.setStatus(LoginUtils.FAILED);
		}
		return response;
	}

	@Override
	public ResponseDTO rejectUser(Map<String, String> dataMap) {
		ResponseDTO response = new ResponseDTO();
		try {
			User user = userService.getUserByUserName(dataMap.get("username"));
			Request request = getRequestByRequestID(dataMap.get("requestId"));
			user.setActive(0);
			user.setLocked(1);
			user.setReason(dataMap.get("message"));
			request.setActive(0);
			userRepository.save(user);
			requestRepository.save(request);
			response.setMessage("User Rejected");
			response.setStatusCode(200);
			response.setStatus(LoginUtils.SUCCESS);
		} catch (Exception e) {
			response.setMessage("User Rejection Failed");
			response.setStatusCode(100);
			response.setStatus(LoginUtils.FAILED);
		}
		return response;
	}

}
