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
	@Column(name = "emp_id")
	private String empId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "role")
	private String role;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "reporting_to")
	private String reportingTo;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "approved_on")
	private Date approvedOn;
	@Column(name = "active")
	private int active;
	@Column(name = "locked")
	private int locked;
	@Column(name = "gender")
	private String gender;
	@Column(name = "reason")
	private String reason;

	public User() {
		super();
	}

	public User(String username, String password, String empId, String firstName, String lastName,
			String departmentName, String role, String emailId, String reportingTo, Date createdOn, Date approvedOn,
			int active, int locked, String gender, String reason) {
		super();
		this.username = username;
		this.password = password;
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentName = departmentName;
		this.role = role;
		this.emailId = emailId;
		this.reportingTo = reportingTo;
		this.createdOn = createdOn;
		this.approvedOn = approvedOn;
		this.active = active;
		this.locked = locked;
		this.gender = gender;
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", empId=" + empId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", departmentName=" + departmentName + ", role=" + role + ", emailId="
				+ emailId + ", reportingTo=" + reportingTo + ", createdOn=" + createdOn + ", approvedOn=" + approvedOn
				+ ", active=" + active + ", locked=" + locked + ", gender=" + gender + ", reason=" + reason + "]";
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

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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