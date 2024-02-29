package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.posidex.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
	
	@Query(value = "SELECT * FROM scrum_tasks u WHERE u.assigned_to = ?1 ", nativeQuery = true)
	public List<Task> getAssignedTasksBasedOnEmpId(String empId);

	@Query(value = "SELECT * FROM scrum_tasks u WHERE u.assigned_to = ?1 and u.active=0", nativeQuery = true)
	public List<Task> getCompletedTasksBasedOnEmpId(String empId);
	
	@Query(value = "SELECT * FROM scrum_tasks u WHERE u.assigned_to = ?1 and u.active=1", nativeQuery = true)
	public List<Task> getPendingTasksBasedOnEmpId(String empId);
}
