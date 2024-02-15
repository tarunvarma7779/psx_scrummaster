package com.posidex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.posidex.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	@Query(value = "SELECT * FROM scrum_users u WHERE u.active = 0 and u.locked = 0", nativeQuery = true)
	List<User> getInactiveUsers();
}