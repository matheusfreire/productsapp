package com.msf.rest.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	private static EntityTransaction transaction;
	private static EntityManager entityManager;
	
	static {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("appdb");
		entityManager = factory.createEntityManager();
	}
	
	public static void commit(){
		transaction.commit();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}