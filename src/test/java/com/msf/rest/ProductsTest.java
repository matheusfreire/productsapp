package com.msf.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.msf.rest.bd.EntityManagerUtil;
import com.msf.rest.dao.ProductsDAO;
import com.msf.rest.models.Product;

import junit.framework.Assert;

public class ProductsTest {

	private ProductsDAO dao;
	
	@Before()
	public void setUp(){
		EntityManagerUtil.initiateManager();
	}


	@Test
	public void createProduct() {
		Product product = new Product();
		product.setName("Teste create");
		product.setDescription("description awesome");
		List<Product> arrayList = new ArrayList<Product>();
		for(int i = 0; i<5; i++){
			Product p = new Product();
			p.setName("Teste create"+i);
			p.setDescription("description awesome"+i);
			p.setProduct(product);
			arrayList.add(p);
		}
		product.setChildProducts(arrayList);
		getDao().persist(product);
		Assert.assertEquals(6, recoverProducts().size());
		Assert.assertEquals(5, recoverChild(getDao().find(1)).size());
	}

	@Test
	public void updateProduct() {
		try {
			Product product = getDao().find(1);
			product.setName("update");
			Assert.assertEquals("update", getDao().find(1).getName());
		} catch (Exception e) {
			
		}
	}
	

	@Test
	public void deleteProduct() {
		try {
			Product product = getDao().find(1);
			getDao().delete(product);
			Assert.assertEquals(0, recoverProducts().size());
		} catch (Exception e) {
		}
	}
	
	private List<Product> recoverProducts() {
		return getDao().recoverAll("from Product");
	}
	
	private List<Product> recoverChild(Product p){
		return getDao().recoverAllChildProducts(p);
	}
	
	public ProductsDAO getDao() {
		if(dao == null){
			dao = new ProductsDAO();
		}
		return dao;
	}

	public void setDao(ProductsDAO dao) {
		this.dao = dao;
	}
	
	
	
	

}
