package com.posidex.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "psx_users")
public class User {

	@Id
	@Column(name = "user_name")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name = "designation")
	private String designation;
	@Column(name = "teammates")
	private String teammates;

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", designation="
				+ designation + ", teammates=" + teammates + "]";
	}

	public User(String username, String password, String name, String designation, String teammates) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.designation = designation;
		this.teammates = teammates;
	}

	public User() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getTeammates() {
		return teammates;
	}

	public void setTeammates(String teammates) {
		this.teammates = teammates;
	}

}
