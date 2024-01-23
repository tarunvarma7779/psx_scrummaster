package com.posidex.dto;

public class ResponseDTO {
	private String status;
	private String message;
	private int statusCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Override
	public String toString() {
		return "ResponseDTO [status=" + status + ", message=" + message + ", statusCode=" + statusCode + "]";
	}

	public ResponseDTO(String status, String message, int statusCode) {
		super();
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
	}

	public ResponseDTO() {
		super();
	}

}
