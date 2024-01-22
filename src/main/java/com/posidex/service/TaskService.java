package com.posidex.service;

import java.util.List;

import com.posidex.entity.Task;

public interface TaskService {

	void addTask(Task task);

	Task getTaskById(int id);

	List<Task> getAllTasks();

	void deleteTaskById(int id);
}
