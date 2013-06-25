package org.opensource.webapp.framework.service;

import org.opensource.webapp.framework.domain.PageParam;
import org.opensource.webapp.framework.domain.PageResult;
import org.opensource.webapp.framework.domain.SysUser;


public interface SysUserService {

	public SysUser saveSysUser(SysUser sysUser);
	
	public void removeSysUser(SysUser sysUser);
	
	public SysUser getSysUserById(Long id);
	
	public PageResult<SysUser> getSysUserList(PageParam pageParam, SysUser sysUser);

}
