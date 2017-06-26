package com.msf.rest.contracts;

import java.util.List;

public interface IMethods<T> {
	
	public void persist(T t);
	public void update(T t, long id);
	public void delete(T t);
	public T recover(T t);
	public List<T> recoverAll(String query);

}
