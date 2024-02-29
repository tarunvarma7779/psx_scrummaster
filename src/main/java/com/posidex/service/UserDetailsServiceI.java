package com.posidex.service;

import java.util.List;
import java.util.Map;

import com.posidex.entity.UserDetails;

public interface UserDetailsServiceI {
	
	public void addUserDetails(UserDetails userDetails);
	
	public UserDetails getUserDetailsByUsername(String username);
	
	public List<UserDetails> getReportees(String empId);
	
	public Map<String,List<UserDetails>> getTeamMemberDetatils(String username);

}
