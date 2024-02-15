package com.posidex.service;

import com.posidex.entity.UserDetails;

public interface UserDetailsServiceI {
	
	public void addUserDetails(UserDetails userDetails);
	
	public UserDetails getUserDetailsByUsername(String Username);

}
