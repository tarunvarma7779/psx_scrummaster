package com.posidex.controller;

import java.util.List;
import java.util.Map;

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
	private TaskService taskService;
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String validateLogin(@RequestBody Map<String,Object> dataMap) {
		return null;
	}
	
	@GetMapping("/getTasks")
	public List<Task> getTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/getTask")
	public Task getTask(@RequestParam int id) {
		return taskService.getTaskById(id);
	}
	
	@DeleteMapping("/deleteTask")
	public void deleteTask(@RequestParam int id) {
		taskService.deleteTaskById(id);
	}
	
	@PostMapping("/addTask")
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task);
	}
	
	@GetMapping("/getUser")
	public User getUser(@RequestParam String username) {
		return userService.getUserByUserName(username);
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers(){
		return userService.getAllUsers();
	}
}
