package com.posidex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.Task;
import com.posidex.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskServiceI {
	
	@Autowired
	TaskRepository taskRepository;
	
	@Override
	public void addTask(Task task) {
		taskRepository.save(task);		
	}

	@Override
	public List<Task> getAssignedTasks(String empId) {
		return taskRepository.getAssignedTasksBasedOnEmpId(empId);
	}

	@Override
	public List<Task> getCompletedTasks(String empId) {
		return taskRepository.getCompletedTasksBasedOnEmpId(empId);
	}

	@Override
	public List<Task> getPendingTasks(String empId) {
		return taskRepository.getPendingTasksBasedOnEmpId(empId);
	}
}