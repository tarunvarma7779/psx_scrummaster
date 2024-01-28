package com.posidex.dto;

import com.posidex.entity.User;

public class JwtResponse {
	private String jwtToken;
	private String message;
	private int statusCode;
	private User user;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JwtResponse(String jwtToken, String message, int statusCode, User user) {
		super();
		this.jwtToken = jwtToken;
		this.message = message;
		this.statusCode = statusCode;
		this.user = user;
	}

	public JwtResponse() {
		super();
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + ", message=" + message + ", statusCode=" + statusCode + ", user="
				+ user + "]";
	}

}