package org.opensource.webapp.framework.service;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ServiceTest{
	
	protected ClassPathXmlApplicationContext ctx;
	
	@Before
	public void before() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@After
	public void after(){
		ctx.close();
	}

}
