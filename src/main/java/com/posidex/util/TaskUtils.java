package com.posidex.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.dto.ResponseDTO;
import com.posidex.dto.TableDTO;
import com.posidex.entity.Task;
import com.posidex.entity.UserDetails;
import com.posidex.service.TaskServiceI;
import com.posidex.service.UserDetailsServiceI;

@Component
public class TaskUtils {
	
	@Autowired
	TaskServiceI taskService;
	@Autowired
	UserDetailsServiceI userDetailsService;
	
	private static int escalatedCount;
	
	public ResponseDTO createTask(Task task) {
		ResponseDTO retValue = new ResponseDTO();
		if(task.getDescription().equals("")) {
			retValue.setStatus(CommonStringUtils.WARN);
			retValue.setMessage("Description is empty");
			retValue.setStatusCode(320);
			return retValue;
		}
		if(validateDeadline(task.getDeadline(), task.getCreatedOn())) {
			retValue.setStatus(CommonStringUtils.WARN);
			retValue.setMessage("deadline should be atleast 2 day");
			retValue.setStatusCode(310);
			return retValue;
		}
		taskService.addTask(task);
		retValue.setStatus(CommonStringUtils.SUCCESS);
		retValue.setMessage("Task Created");
		retValue.setStatusCode(200);
		return retValue;
	}
	
	private static boolean validateDeadline(Date d1 , Date d2) {
		long differenceInMilliseconds = d1.getTime() - d2.getTime();
        long differenceInDays = differenceInMilliseconds / (24 * 60 * 60 * 1000);
        return differenceInDays < 2;
	}

	public TableDTO getAssignedByMe(String empId) {
		TableDTO retValue = new TableDTO();
		retValue.setHeaders("SNo::Reportee::Assigned::Completed::Pending::Escalation".split("::"));
		List<UserDetails> reportees = new ArrayList<>();
		reportees = userDetailsService.getReportees(empId);
		List<String[]> records = new ArrayList<>();
		reportees.forEach(x->{
			StringBuilder sb = new StringBuilder();
			sb.append(x.getFirstName()+" "+ x.getLastName()+",");
			sb.append(taskService.getAssignedTasks(x.getEmpId()).size()+",");
			sb.append(taskService.getCompletedTasks(x.getEmpId()).size()+",");
			sb.append(taskService.getPendingTasks(x.getEmpId()).size()+",");
			sb.append(getEscalatedCount(taskService.getAssignedTasks(x.getEmpId())));
			records.add(sb.toString().split(","));
		});
		retValue.setRecords(records);		
		return retValue;
	}

	private int getEscalatedCount(List<Task> assignedTasks) {
		escalatedCount = 0;
		assignedTasks.forEach(x->{
			if(x.getClosedOn()!=null && (x.getClosedOn().compareTo(x.getDeadline()))>0) {
				escalatedCount++;
			}
			else if(x.getDeadline().compareTo(new Date())<0) {
				escalatedCount++;
			}
		});
		return escalatedCount;
	}

	public TableDTO getAssignedToMe(String empId) {
		TableDTO retValue = new TableDTO();
		retValue.setHeaders("SNo::TaskId::TaskName".split("::"));
		List<String[]> records = new ArrayList<>();
		List<Task> tasks = taskService.getAssignedTasks(empId);
		tasks.forEach(x->{
			StringBuilder sb = new StringBuilder();
			sb.append(x.getTaskId()+",");
			sb.append(x.getTaskName());
			records.add(sb.toString().split(","));			
		});
		retValue.setRecords(records);
		return retValue;
	}

}
