package com.posidex.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scrum_tasks")
public class Task {
	@Id
	@Column(name = "task_id")
	private String taskId;
	@Column(name = "task_name")
	private String taskName;
	@Column(name = "project_name")
	private String projectName;
	@Column(name = "description")
	private String description;
	@Column(name = "assigned_to")
	private String assignedTo;
	@Column(name = "assigned_by")
	private String assignedBy;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "deadline")
	private Date deadline;
	@Column(name = "closed_on")
	private Date closedOn;
	@Column(name = "priority")
	private String priority;
	@Column(name = "active")
	private int active;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(Date closedOn) {
		this.closedOn = closedOn;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", projectName=" + projectName + ", description="
				+ description + ", assignedTo=" + assignedTo + ", assignedBy=" + assignedBy + ", createdOn=" + createdOn
				+ ", deadline=" + deadline + ", closedOn=" + closedOn + ", priority=" + priority + ", active=" + active
				+ "]";
	}

	public Task(String taskId, String taskName, String projectName, String description, String assignedTo,
			String assignedBy, Date createdOn, Date deadline, Date closedOn, String priority, int active) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.projectName = projectName;
		this.description = description;
		this.assignedTo = assignedTo;
		this.assignedBy = assignedBy;
		this.createdOn = createdOn;
		this.deadline = deadline;
		this.closedOn = closedOn;
		this.priority = priority;
		this.active = active;
	}

	public Task() {
		super();
	}
}
