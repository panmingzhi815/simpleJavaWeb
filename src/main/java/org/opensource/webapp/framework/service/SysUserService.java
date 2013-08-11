package org.opensource.webapp.framework.service;

import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;


public interface SysUserService {

	public boolean saveSysUser(SysUser sysUser);
	
	public boolean removeSysUser(Long id);
	
	public boolean removeSysUser(SysUser sysUser);
	
	public SysUser getSysUserById(Long id);
	
	public PageResult<SysUser> getSysUserList(PageParam pageParam, SearchFilter searchFilter);
}
