package com.msf.rest.dao;

import java.util.List;

import javax.persistence.RollbackException;

import com.msf.rest.contracts.IDatabase;
import com.msf.rest.models.Image;
import com.msf.rest.models.Product;

public class ImageDAO implements IDatabase<Image>{
	
	@Override
	public void persist(Image p) throws RollbackException{
		try{
			EntityManagerUtil.beginTransaction();
			p = EntityManagerUtil.getEntityManager().merge(p);
			EntityManagerUtil.getEntityManager().persist(p);			
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public void update(Image p, int id) throws RollbackException{
		try{
			Image image = find(id);
			EntityManagerUtil.beginTransaction();
			image.setType(p.getType());
			EntityManagerUtil.commit();
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public void delete(Image i) throws RollbackException{
		try{
			EntityManagerUtil.getEntityManager().remove(i);
			EntityManagerUtil.commit();
		} catch (RollbackException r){
			throw r;
		}
	}

	@Override
	public List<Image> recoverAll(String query) {
		return EntityManagerUtil.getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public Image find(int id) {
		return (Image) EntityManagerUtil.getEntityManager().find(Image.class, id);
	}
	
	public List<Image> recoverAllByProduct(Product p){
		return EntityManagerUtil.getEntityManager().createQuery("from Image where product = :product").setParameter("product",p).getResultList();
	}

}
