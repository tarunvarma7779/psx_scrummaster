package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.posidex.entity.UserDetails;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String>{

	@Query(value = "SELECT * FROM scrum_user_details u WHERE u.username = ?1 ", nativeQuery = true)
	public UserDetails getUserDetailsByUsername(String username);
	
	@Query(value = "SELECT ud.* FROM scrum_user_details ud INNER JOIN scrum_users u WHERE ud.username = u.username AND u.active=1 AND ud.reporting_to = ?1 ", nativeQuery = true)
	public List<UserDetails> getReporteesByEmpID(String empId);
	
	@Query(value = "SELECT ud.* FROM scrum_user_details ud INNER JOIN scrum_users u WHERE ud.username = u.username AND u.active=1 AND ud.department_name=?1 ", nativeQuery = true)
	public List<UserDetails> getTeamMembersByDepartment(String department);
	
}
