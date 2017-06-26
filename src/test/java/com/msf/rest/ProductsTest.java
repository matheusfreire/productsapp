package com.msf.rest;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.msf.rest.models.Product;

public class ProductsTest {

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	@Before
	public void setUp() throws Exception {
		entityManager.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		entityManager.close();
	}

	@Test
	public void createProduct() {
		Product product = new Product();
		product.setName("Teste create");
		product.setDescription("description awesome");
		product = entityManager.merge(product);
		entityManager.getTransaction().commit();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
