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
import com.posidex.entity.UserActivation;
import com.posidex.entity.User;
import com.posidex.entity.UserDetails;
import com.posidex.repository.UserRequestRepository;
import com.posidex.repository.UserRepository;
import com.posidex.util.LoginUtils;

import jakarta.transaction.Transactional;

@Service
public class RequestServiceImpl implements RequestServiceI {

	@Autowired
	private UserRequestRepository requestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserDetailsServiceI userDetailsService;

	@Autowired
	private UserServiceI userService;

	@Transactional
	@Override
	public void addRequest(UserActivation request) {
		requestRepository.save(request);
	}

	@Override
	public UserActivation getRequestByRequestID(String requestId) {
		Optional<UserActivation> result = requestRepository.findById(requestId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}

	@Override
	public List<ProfileRequestDTO> getProfileRequests(String username) {
		List<ProfileRequestDTO> retValue = new ArrayList<>();
		UserDetails currentUser = userDetailsService.getUserDetailsByUsername(username);
		List<User> inactiveUsersList = userRepository.getInactiveUsers();
		inactiveUsersList.forEach(x -> {
			UserDetails tempUserDetails = userDetailsService.getUserDetailsByUsername(x.getUsername());
			if (tempUserDetails.getReportingTo().equals(currentUser.getEmpId())) {
				retValue.add(new ProfileRequestDTO(tempUserDetails, getProfileActivationRequests(x.getUsername())));
			}
		});
		return retValue;
	}

	public UserActivation getProfileActivationRequests(String username) {
		return requestRepository.getRequestsBasedOnUserAndOperation(username);
	}

	@Override
	public ResponseDTO approveUser(Map<String, String> dataMap) {
		ResponseDTO response = new ResponseDTO();
		try {
			User user = userService.getUserByUserName(dataMap.get("username"));
			UserActivation request = getRequestByRequestID(dataMap.get("requestId"));
			user.setLocked(0);
			user.setActive(1);
			user.setApprovedOn(new Date());
			user.setActionBy(dataMap.get("actionBy"));
			user.setReason(dataMap.get("message"));
			request.setActive(0);
			userRepository.save(user);
			requestRepository.save(request);
			response.setMessage("User Approved");
			response.setStatusCode(200);
			response.setStatus(LoginUtils.SUCCESS);
		} catch (Exception e) {
			response.setMessage("User Approval Failed");
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
			UserActivation request = getRequestByRequestID(dataMap.get("requestId"));
			user.setLocked(1);
			user.setReason(dataMap.get("message"));
			user.setActionBy(dataMap.get("actionBy"));
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
