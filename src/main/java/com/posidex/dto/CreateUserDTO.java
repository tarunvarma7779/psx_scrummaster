package com.posidex.dto;

public class CreateUserDTO {

	private String firstName;
	private String lastName;
	private String emailId;
	private String departmentName;
	private String designation;
	private String gender;
	private String empId;
	private String reportingTo;
	private String username;
	private String password;

	@Override
	public String toString() {
		return "CreateUserDTO [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", departmentName=" + departmentName + ", designation=" + designation + ", gender=" + gender
				+ ", empId=" + empId + ", reportingTo=" + reportingTo + ", username=" + username + ", password="
				+ password + "]";
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getReportingTo() {
		return reportingTo;
	}

	public void setReportingTo(String reportingTo) {
		this.reportingTo = reportingTo;
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

	public CreateUserDTO(String firstName, String lastName, String emailId, String departmentName, String designation,
			String gender, String empId, String reportingTo, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.departmentName = departmentName;
		this.designation = designation;
		this.gender = gender;
		this.empId = empId;
		this.reportingTo = reportingTo;
		this.username = username;
		this.password = password;
	}

	public CreateUserDTO() {
		super();
	}

}
