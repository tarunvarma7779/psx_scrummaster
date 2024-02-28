package com.posidex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.posidex.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

}
