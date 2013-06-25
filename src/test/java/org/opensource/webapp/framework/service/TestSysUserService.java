package org.opensource.webapp.framework.service;

import org.junit.Test;
import org.opensource.webapp.framework.domain.SysUser;

public class TestSysUserService extends ServiceTest{
	
	@Test
	public void testSaveSysUser(){
		 SysUser sysUser = new SysUser("xiaopan", "panmingzhi815", "123456");
		 SysUserService sysUserService = ctx.getBean(SysUserService.class);
		 sysUserService.saveSysUser(sysUser);
	}
	
}
