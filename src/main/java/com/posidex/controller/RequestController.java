package com.posidex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.ProfileRequestDTO;
import com.posidex.dto.ResponseDTO;
import com.posidex.service.RequestServiceI;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/request")
@RestController
public class RequestController {

	@Autowired
	private RequestServiceI requestService;

	@GetMapping("/getProfileRequests")
	public List<ProfileRequestDTO> getProfileRequests(@RequestParam String currentUser) {
		return requestService.getProfileRequests(currentUser);
	}

	@PostMapping("/approveUser")
	public ResponseDTO approveUser(@RequestBody Map<String, String> dataMap) {
		return requestService.approveUser(dataMap);
	}

	@PostMapping("/rejectUser")
	public ResponseDTO rejectUser(@RequestBody Map<String, String> dataMap) {
		return requestService.rejectUser(dataMap);
	}

}
