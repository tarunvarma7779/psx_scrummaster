package com.posidex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.entity.UserDetails;
import com.posidex.service.UserDetailsServiceI;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	UserDetailsServiceI userDetailsService;
	
	@GetMapping("/getUserDetails")
	public UserDetails getUserDetails(@RequestParam String username) {
		return userDetailsService.getUserDetailsByUsername(username);
	}
	
	@GetMapping("/getTeam")
	public Map<String,List<UserDetails>> getTeam(@RequestParam String empId) {
		return userDetailsService.getTeamMemberDetatils(empId);
	}
	
	@GetMapping("/getReportees")
	public List<UserDetails> getReportees(@RequestParam String empId) {
		return userDetailsService.getReportees(empId);
	}
}
