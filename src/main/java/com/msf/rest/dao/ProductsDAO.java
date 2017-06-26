package com.msf.rest.dao;

import java.util.List;

import javax.persistence.Query;

import com.msf.rest.contracts.IMethods;
import com.msf.rest.models.Product;

public class ProductsDAO implements IMethods<Product>{

	@Override
	public void persist(Product p) {
		EntityManagerUtil.getEntityManager().persist(p);
	}

	@Override
	public void update(Product p, long id) {
	}

	@Override
	public void delete(Product p) {
		EntityManagerUtil.getEntityManager().remove(p);
	}

	@Override
	public Product recover(Product p) {
		return null;
	}

	@Override
	public List<Product> recoverAll(String query) {
		Query q = EntityManagerUtil.getEntityManager().createQuery("SELECT * FROM products", Product.class);
		List<Product> products = q.getResultList();
		return products;
	}

}
