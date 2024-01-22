package com.posidex.dao;

import java.util.List;

import com.posidex.entity.Task;

public interface TaskDAO {

	void addTask(Task task);

	Task getTaskById(int id);

	List<Task> getTaskList();

	void deleteTaskById(int id);

}
