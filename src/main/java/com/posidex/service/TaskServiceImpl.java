package com.posidex.service;

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
}