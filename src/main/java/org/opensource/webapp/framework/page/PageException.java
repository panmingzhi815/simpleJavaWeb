package org.opensource.webapp.framework.page;

public class PageException extends RuntimeException {

	private static final long serialVersionUID = -7482916421282536233L;

	public PageException(){
		
	}
	
	public PageException(String msg){
		super(msg);
	}
	
}
