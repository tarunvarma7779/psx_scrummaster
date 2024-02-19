package com.posidex.dto;

import com.posidex.entity.UserActivation;
import com.posidex.entity.UserDetails;

public class ProfileRequestDTO {
	private UserDetails userDetails;
	private UserActivation request;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserActivation getRequest() {
		return request;
	}

	public void setRequest(UserActivation request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "ProfileRequestDTO [userDetails=" + userDetails + ", request=" + request + "]";
	}

	public ProfileRequestDTO(UserDetails userDetails, UserActivation request) {
		super();
		this.userDetails = userDetails;
		this.request = request;
	}

	public ProfileRequestDTO() {
		super();
	}

}
