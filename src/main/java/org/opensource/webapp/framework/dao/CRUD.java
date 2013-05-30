package org.opensource.webapp.framework.dao;

public interface CRUD<T,ID>{
	
	public T save(T t);
	
	public void delete(T t,ID id);
	
	public T get(T t,ID id);
	
		
}
