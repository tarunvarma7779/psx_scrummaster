package com.posidex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posidex.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}