package com.posidex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.ResponseDTO;
import com.posidex.entity.Task;
import com.posidex.util.TaskUtils;

@RequestMapping("/task")
@RestController
public class TaskController {
	
	@Autowired
	TaskUtils taskUtils;
	
	@PostMapping("/createTask")
	public ResponseDTO createTask(@RequestBody Task task) {
		return taskUtils.createTask(task);
	}

}
