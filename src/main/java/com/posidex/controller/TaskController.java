package com.posidex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.dto.ResponseDTO;
import com.posidex.dto.TableDTO;
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
	
	@GetMapping("/getAssignedByMe")
	public TableDTO getAssignedByMe(@RequestParam String empId) {
		return taskUtils.getAssignedByMe(empId);
	}
	
	@GetMapping("/getAssignedToMe")
	public TableDTO getAssignedToMe(@RequestParam String empId) {
		return taskUtils.getAssignedToMe(empId);
	}
	
	@GetMapping("/getTeamTasks")
	public List<Task> getTeamTasks(@RequestParam String empId) {
		return taskUtils.getTeamTasks(empId);
	}
}
