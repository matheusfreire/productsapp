package com.msf.rest;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.msf.rest.bd.EntityManagerUtil;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

import junit.framework.Assert;

public class ImagesTest {
	
	private EntityManager entityManager = EntityManagerUtil.getEntityManager();
	private Product product;

	@Before
	public void setUp() throws Exception {
		entityManager.getTransaction().begin();
	}


	@Test
	public void updateImage() {
		try {
			Image image = findImage(1);
			image.setType("png");
			entityManager.getTransaction().commit();
			Assert.assertEquals("png", findImage(1).getType());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	

	@Test
	public void deleteImage() {
		try {
			Image image = findImage(1);
			entityManager.remove(image);
			entityManager.getTransaction().commit();
			Assert.assertEquals(0, recoverImages().size());
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}
	
	private Image findImage(long id){
		return (Image) entityManager.find(Image.class, id);
	}

	private List<Image> recoverImages() {
		return entityManager.createQuery("from Image").getResultList();
	}
	
}
