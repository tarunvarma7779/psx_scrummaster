package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.posidex.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query(value = "SELECT * FROM psx_users u WHERE u.reporting_to = ?1 and u.active = 0", nativeQuery = true)
	List<User> getInactiveUserforReporting(String username);
}