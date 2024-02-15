package com.posidex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "scrum_userops")
public class UserOps {

	@EmbeddedId
	private UserOpsIdentity userOpsIdentity;
	@Column(name = "operation_type")
	private String operationType;
	@Column(name = "role")
	private String role;

	public UserOpsIdentity getUserOpsIdentity() {
		return userOpsIdentity;
	}

	public void setUserOpsIdentity(UserOpsIdentity userOpsIdentity) {
		this.userOpsIdentity = userOpsIdentity;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserOps(UserOpsIdentity userOpsIdentity, String operationType, String role) {
		super();
		this.userOpsIdentity = userOpsIdentity;
		this.operationType = operationType;
		this.role = role;
	}

	public UserOps() {
		super();
	}

	@Override
	public String toString() {
		return "UserOps [userOpsIdentity=" + userOpsIdentity + ", operationType=" + operationType + ", role=" + role
				+ "]";
	}

}