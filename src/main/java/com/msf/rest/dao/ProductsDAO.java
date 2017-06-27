package com.msf.rest.dao;

import java.util.List;

import javax.persistence.RollbackException;

import com.msf.rest.bd.EntityManagerUtil;
import com.msf.rest.contracts.IDatabase;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

public class ProductsDAO implements IDatabase<Product>{
	
	@Override
	public void persist(Product p) throws RollbackException{
		try{
			EntityManagerUtil.beginTransaction();
			p = EntityManagerUtil.getEntityManager().merge(p);
			EntityManagerUtil.getEntityManager().persist(p);
			EntityManagerUtil.commit();
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public void update(Product p, Integer id) throws RollbackException{
		try{
			Product product = find(id);
			EntityManagerUtil.beginTransaction();
			product.setDescription(p.getDescription());
			product.setName(p.getName());
			EntityManagerUtil.commit();
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public void delete(Product p) throws RollbackException{
		try{
			EntityManagerUtil.beginTransaction();
			EntityManagerUtil.getEntityManager().remove(p);
			EntityManagerUtil.commit();
		} catch (Exception r){
			throw r;
		}
	}

	@Override
	public List<Product> recoverAll(String query) {
		return EntityManagerUtil.getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Product find(Integer id) {
		return (Product) EntityManagerUtil.getEntityManager().find(Product.class, id);
	}
	
	public List<Image> getImageByProduct(Product p){
		return new ImageDAO().recoverAllByProduct(p);
	}

	@SuppressWarnings("unchecked")
	public List<Product> recoverAllChildProducts(Product p) {
		return EntityManagerUtil.getEntityManager().createQuery("from Product where parent_product_id = :product").setParameter("product",p.getId()).getResultList();
	}

}
