package com.posidex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.Request;
import com.posidex.repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestServiceI {

	@Autowired
	private RequestRepository requestRepository;

	@Override
	public void addRequest(Request request) {
		requestRepository.save(request);
	}

	@Override
	public List<Request> getRequestByUserName(String username) {
		return requestRepository.getRequestByUsername(username);
	}

}
