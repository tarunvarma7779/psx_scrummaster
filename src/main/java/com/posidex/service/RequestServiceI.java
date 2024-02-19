package com.posidex.service;

import java.util.List;
import java.util.Map;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.dto.ResponseDTO;
import com.posidex.entity.UserActivation;

public interface RequestServiceI {

	public void addRequest(UserActivation request);
	
	public UserActivation getRequestByRequestID(String requestId);
		
	public List<ProfileRequestDTO> getProfileRequests(String username);
	
	public ResponseDTO approveUser(Map<String,String> dataMap);
	
	public ResponseDTO rejectUser(Map<String,String> dataMap);
}
