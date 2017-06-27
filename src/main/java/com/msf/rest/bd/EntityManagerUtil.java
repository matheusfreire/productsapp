package com.msf.rest.bd;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManager entityManager;
	
	public static void initiateManager(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("appdb");
		entityManager = factory.createEntityManager();
	}


	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}
	
	public static void commit() {
		getEntityManager().getTransaction().commit();
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}