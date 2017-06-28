package com.msf.rest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import com.msf.rest.bd.EntityManagerUtil;
import com.msf.rest.contracts.IDatabase;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

public class ProductsDAO implements IDatabase<Product>{
	
	private ImageDAO imageDAO;
	
	@Override
	public void persist(Product p) throws RollbackException{
		try{
			EntityManagerUtil.beginTransaction();
			p = getEntityManager().merge(p);
			getEntityManager().persist(p);
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
			product = getEntityManager().merge(p);
			EntityManagerUtil.commit();
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public void delete(Product p) throws RollbackException{
		try{
			EntityManagerUtil.beginTransaction();
			getEntityManager().remove(p);
			EntityManagerUtil.commit();
		} catch (Exception r){
			throw r;
		}
	}

	@Override
	public List<Product> recoverAll(String query) {
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Product find(Integer id) {
		return (Product) getEntityManager().find(Product.class, id);
	}
	
	public List<Image> getImageByProduct(Product p){
		return new ImageDAO().recoverAllByProduct(p);
	}

	@SuppressWarnings("unchecked")
	public List<Product> recoverAllChildProducts(Product p) {
		return getEntityManager().createQuery("from Product where parent_product_id = :product").setParameter("product",p.getId()).getResultList();
	}

	private EntityManager getEntityManager() {
		return EntityManagerUtil.getEntityManager();
	}
	
	public void persistImage(Image image){
		getImageDAO().persist(image);
	}

	public ImageDAO getImageDAO() {
		if (imageDAO == null) {
			imageDAO = new ImageDAO();
		}
		return imageDAO;
	}

	@Override
	public Product findComplete(Integer id) {
		Product p =find(id);
		getEntityManager().detach(p);
		p.setChildProducts(recoverAllChildProducts(p));
		p.setImages(getImageByProduct(p));
		return p;
	}

}
