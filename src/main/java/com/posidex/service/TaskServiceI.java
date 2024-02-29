package com.posidex.service;

import java.util.List;

import com.posidex.entity.Task;
import com.posidex.entity.UserDetails;

public interface TaskServiceI {
	
	public void addTask(Task task);
	
	public List<Task> getAssignedTasks(String empId);

	public List<Task> getCompletedTasks(String empId);
	
	public List<Task> getPendingTasks(String empId);
}