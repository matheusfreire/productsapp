package com.msf.rest;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.msf.rest.dao.EntityManagerUtil;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

import junit.framework.Assert;

public class ProductsTest {

	private EntityManager entityManager = EntityManagerUtil.getEntityManager();

	@Before
	public void setUp() throws Exception {
		entityManager.getTransaction().begin();
	}

	@Test
	public void createProduct() {
		Product product = new Product();
		product.setName("Teste create");
		product.setDescription("description awesome");
		product = entityManager.merge(product);
		
		for(int i = 0; i<5; i++){
			Image image = new Image();
			image.setType("jpg");
			image.setProduct(product);
			entityManager.persist(image);
		}
		entityManager.getTransaction().commit();
		Assert.assertEquals(1, recoverProducts().size());
		Assert.assertEquals(5, recoverImages().size());
	}

	@Test
	public void updateProduct() {
		try {
			Product product = findProduct(1);
			product.setName("update");
			entityManager.getTransaction().commit();
			Assert.assertEquals("update", findProduct(1).getName());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	

	@Test
	public void deleteProduct() {
		try {
			Product product = findProduct(1);
			entityManager.remove(product);
			entityManager.getTransaction().commit();
			Assert.assertEquals(0, recoverProducts().size());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	
	private Product findProduct(long id){
		return (Product) entityManager.find(Product.class, id);
	}

	private List<Product> recoverProducts() {
		return entityManager.createQuery("from Product").getResultList();
	}
	
	private List<Image> recoverImages() {
		return entityManager.createQuery("from Image").getResultList();
	}

}
