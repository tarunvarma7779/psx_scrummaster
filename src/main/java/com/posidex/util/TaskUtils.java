package com.posidex.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.posidex.dto.ResponseDTO;
import com.posidex.entity.Task;
import com.posidex.service.TaskServiceI;

@Component
public class TaskUtils {
	
	@Autowired
	TaskServiceI taskService;
	
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

}
