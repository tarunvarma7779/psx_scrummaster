package com.posidex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.entity.Request;
import com.posidex.service.RequestServiceI;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/request")
@RestController
public class RequestController {
	
	@Autowired
	private RequestServiceI requestService;
	
	@GetMapping("/getUserActivationRequest")
	public List<Request> getUserActivationRequests(@RequestParam String user){
		return requestService.getRequestByUserName(user);
	}
	@PostMapping("/getProfileRequests")
	public List<ProfileRequestDTO> getProfileRequests(@RequestBody String currentUser){		
		return requestService.getProfileRequests(currentUser);
	}
}
