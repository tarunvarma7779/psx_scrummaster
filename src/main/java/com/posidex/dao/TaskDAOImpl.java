package com.posidex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.posidex.entity.Task;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Configuration
public class TaskDAOImpl implements TaskDAO {
	@Autowired
	private EntityManager entityManager;

	public TaskDAOImpl(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	@Transactional
	public void addTask(Task task) {
		entityManager.persist(task);
	}

	@Override
	public Task getTaskById(int id) {
		return entityManager.find(Task.class, id);
	}

	@Override
	public List<Task> getTaskList() {
		TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteTaskById(int id) {
		Task task = entityManager.find(Task.class, id);
		entityManager.remove(task);
	}

}
