package com.posidex.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scrum_users")
public class User implements UserDetails {

	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "approved_on")
	private Date approvedOn;
	@Column(name = "action_by")
	private String actionBy;
	@Column(name = "reason")
	private String reason;
	@Column(name = "locked")
	private int locked;
	@Column(name = "active")
	private int active;

	public User() {
		super();
	}

	public User(String username, String password, String role, Date createdOn, Date approvedOn, String actionBy,
			String reason, int locked,int active) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.createdOn = createdOn;
		this.approvedOn = approvedOn;
		this.actionBy = actionBy;
		this.reason = reason;
		this.locked = locked;
		this.active=active;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", role=" + role + ", createdOn=" + createdOn
				+ ", approvedOn=" + approvedOn + ", actionBy=" + actionBy + ", reason=" + reason + ", active=" + active + ", locked="
				+ locked + "]";
	}
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}