package com.posidex.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "psx_tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	@Column(name = "task_name")
	private String taskName;
	@Column(name = "task_details")
	private String taskDetails;
	@Column(name = "assigned_by")
	private String assignedBy;
	@Column(name = "assigned_to")
	private String assignedTo;
	@Column(name = "priority")
	private String priority;
	@Column(name = "status")
	private String status;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "closed_on")
	private Date closedOn;

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskDetails=" + taskDetails + ", assignedBy="
				+ assignedBy + ", asssignedTo=" + assignedTo + ", priority=" + priority + ", status=" + status
				+ ", createdOn=" + createdOn + ", closedOn=" + closedOn + "]";
	}

	public Task(String id, String taskName, String taskDetails, String assignedBy, String assignedTo, String priority,
			String status, Date createdOn, Date closedOn) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.assignedBy = assignedBy;
		this.assignedTo = assignedTo;
		this.priority = priority;
		this.status = status;
		this.createdOn = createdOn;
		this.closedOn = closedOn;
	}

	public Task() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

}
