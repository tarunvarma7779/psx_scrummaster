package com.posidex.dto;

import com.posidex.entity.Request;
import com.posidex.entity.UserDetails;

public class ProfileRequestDTO {
	private UserDetails userDetails;
	private Request request;

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "ProfileRequestDTO [userDetails=" + userDetails + ", request=" + request + "]";
	}

	public ProfileRequestDTO(UserDetails userDetails, Request request) {
		super();
		this.userDetails = userDetails;
		this.request = request;
	}

	public ProfileRequestDTO() {
		super();
	}

}
