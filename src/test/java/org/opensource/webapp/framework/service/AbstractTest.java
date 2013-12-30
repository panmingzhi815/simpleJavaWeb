package org.opensource.webapp.framework.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AbstractTest {
	
	private static ClassPathXmlApplicationContext applicationContext;
	protected static SysMenuService sysMenuService;
	protected static SysUserService sysUserService;
    protected static SysRoleService sysRoleService;
	
	@BeforeClass
	public static void startup(){
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		sysUserService = applicationContext.getBean(SysUserService.class);
		sysMenuService = applicationContext.getBean(SysMenuService.class);
        sysRoleService = applicationContext.getBean(SysRoleService.class);
	}
	
	@AfterClass
	public static void destory(){
        if(applicationContext != null)
		applicationContext.close();
	}

}
