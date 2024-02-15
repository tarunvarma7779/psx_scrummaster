package com.posidex.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scrum_user_details")
public class UserDetails {

	@Id
	@Column(name = "emp_id")
	private String empId;
	@Column(name = "username")
	private String username;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "gender")
	private String gender;
	@Column(name = "department_name")
	private String departmentName;
	@Column(name = "designation")
	private String designation;
	@Column(name = "email_id")
	private String emailId;
	@Column(name = "reporting_to")
	private String reportingTo;

	public UserDetails(String empId, String username, String firstName, String lastName, String gender,
			String departmentName, String designation, String emailId, String reportingTo) {
		super();
		this.empId = empId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.departmentName = departmentName;
		this.designation = designation;
		this.emailId = emailId;
		this.reportingTo = reportingTo;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public UserDetails() {
		super();
	}

	@Override
	public String toString() {
		return "UserDetails [empId=" + empId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", departmentName=" + departmentName + ", designation="
				+ designation + ", emailId=" + emailId + ", reportingTo=" + reportingTo + "]";
	}

}
