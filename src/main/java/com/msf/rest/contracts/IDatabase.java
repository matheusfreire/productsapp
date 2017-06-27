package com.msf.rest.contracts;

import java.util.List;

import javax.persistence.RollbackException;

public interface IDatabase<T> {
	
	public T find(Integer id);
	public void persist(T t) throws RollbackException;
	public void update(T t, Integer id)  throws RollbackException;
	public void delete(T t)  throws RollbackException;
	public List<T> recoverAll(String query);

}
