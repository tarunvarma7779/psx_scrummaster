package com.posidex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posidex.entity.Task;

public interface TaskRepository extends JpaRepository<Task, String> {

}