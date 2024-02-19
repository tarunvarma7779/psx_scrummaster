package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.posidex.entity.UserActivation;

@Repository
public interface UserRequestRepository extends JpaRepository<UserActivation, String> {
	
	@Query(value = "SELECT * FROM scrum_user_requests r WHERE r.raised_to = ?1", nativeQuery = true)
	List<UserActivation> getRequestRaisedToUsername(String username);
	
	@Query(value = "SELECT * FROM scrum_user_requests r WHERE r.raised_by = ?1 and active = 1", nativeQuery = true)
	UserActivation getRequestsBasedOnUserAndOperation(String username);
}
