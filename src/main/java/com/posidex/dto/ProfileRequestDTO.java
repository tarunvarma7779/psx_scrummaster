package com.posidex.dto;

import com.posidex.entity.Request;
import com.posidex.entity.User;

public class ProfileRequestDTO {
	private User user;
	private Request request;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	@Override
	public String toString() {
		return "ProfileRequestDTO [user=" + user + ", request=" + request + "]";
	}

	public ProfileRequestDTO(User user, Request request) {
		super();
		this.user = user;
		this.request = request;
	}

	public ProfileRequestDTO() {
		super();
	}

}
