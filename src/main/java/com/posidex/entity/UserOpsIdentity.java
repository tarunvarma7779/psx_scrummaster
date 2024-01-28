package com.posidex.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserOpsIdentity {

	@Column(name = "user_id")
	private String userID;
	@Column(name = "operation_time")
	private Date operationTime;

	public UserOpsIdentity() {
	}

	public UserOpsIdentity(String userID, Date operationTime) {
		this.userID = userID;
		this.operationTime = operationTime;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
}