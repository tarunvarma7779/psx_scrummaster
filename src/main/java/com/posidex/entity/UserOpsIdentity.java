package com.posidex.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserOpsIdentity {

	@Column(name = "username")
	private String username;
	@Column(name = "operation_time")
	private Date operationTime;

	public UserOpsIdentity() {
	}

	public UserOpsIdentity(String username, Date operationTime) {
		this.username = username;
		this.operationTime = operationTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
}