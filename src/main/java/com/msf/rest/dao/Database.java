package com.msf.rest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Database {
	
	private static EntityTransaction transaction;
	private static EntityManager entityManager;
	
	static {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("appdb");
		entityManager = factory.createEntityManager();
		transaction = entityManager.getTransaction();
	}
	
	public static void beginTransaction(){
		transaction.begin();
	}

	public static EntityTransaction getTransaction() {
		return transaction;
	}
	
	public void commit(){
		transaction.commit();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}