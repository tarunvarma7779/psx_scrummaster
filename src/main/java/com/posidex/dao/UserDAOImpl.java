package com.posidex.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.posidex.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Component
public class UserDAOImpl implements UserDAO {

	@Autowired
	private EntityManager entityManager;

	public UserDAOImpl(EntityManager em) {
		this.entityManager = em;
	}

	@Override
	public List<User> getUserList() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public User getUserByUsername(String username) {
		return entityManager.find(User.class, username);
	}
	@Transactional
	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

}
