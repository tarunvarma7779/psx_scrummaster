package com.posidex.service;

import java.util.List;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.entity.Request;

public interface RequestServiceI {

	public void addRequest(Request request);
	
	public List<Request> getRequestByUserName(String username);
	
	public List<ProfileRequestDTO> getProfileRequests(String currentUser);
}
