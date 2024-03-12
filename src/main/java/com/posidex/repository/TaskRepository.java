package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.posidex.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
	
	@Query(value = "SELECT * FROM scrum_tasks t WHERE t.assigned_to = ?1 ", nativeQuery = true)
	public List<Task> getAssignedTasksBasedOnEmpId(String empId);

	@Query(value = "SELECT * FROM scrum_tasks t WHERE t.assigned_to = ?1 AND t.active=0", nativeQuery = true)
	public List<Task> getCompletedTasksBasedOnEmpId(String empId);
	
	@Query(value = "SELECT * FROM scrum_tasks t WHERE t.assigned_to = ?1 AND t.active=1", nativeQuery = true)
	public List<Task> getPendingTasksBasedOnEmpId(String empId);
	
	@Query(value = "SELECT * FROM scrum_tasks t WHERE t.active=1 AND t.assigned_by IN (SELECT emp_id FROM scrum_user_details u WHERE u.department_name IN (SELECT department_name FROM scrum_user_details v WHERE v.emp_id = ?1))", nativeQuery = true)
	public List<Task> getTeamTasksBasedOnEmpId(String empId);
}
