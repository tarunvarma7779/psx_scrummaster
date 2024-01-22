package com.posidex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.posidex.entity.Task;
import com.posidex.entity.User;
import com.posidex.service.TaskService;
import com.posidex.service.UserService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScrumController {

	@Autowired
	private TaskService ts;
	@Autowired
	private UserService us;

	public ScrumController(TaskService taskService, UserService userService) {
		this.ts = taskService;
		this.us = userService;
	}

	@GetMapping("/getTasks")
	public List<Task> getTasks(){
		return ts.getAllTasks();
	}
	
	@GetMapping("/getTask")
	public Task getTask(@RequestParam int id) {
		return ts.getTaskById(id);
	}
	
	@DeleteMapping("/deleteTask")
	public void deleteTask(@RequestParam int id) {
		ts.deleteTaskById(id);
	}
	
	@PostMapping("/addTask")
	public void addTask(@RequestBody Task task) {
		ts.addTask(task);
	}
	
	@GetMapping("/getUser")
	public User getUser(@RequestParam String username) {
		return us.getUserByUserName(username);
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers(){
		return us.getAllUsers();
	}
}
