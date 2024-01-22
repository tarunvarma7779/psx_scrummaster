package com.posidex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.dao.TaskDAO;
import com.posidex.entity.Task;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO taskDAO;

	public TaskServiceImpl(TaskDAO td) {
		this.taskDAO = td;
	}

	@Override
	public void addTask(Task task) {
		taskDAO.addTask(task);
	}

	@Override
	public Task getTaskById(int id) {
		return taskDAO.getTaskById(id);
	}

	@Override
	public List<Task> getAllTasks() {
		return taskDAO.getTaskList();
	}

	@Override
	public void deleteTaskById(int id) {
		taskDAO.deleteTaskById(id);
	}

}
