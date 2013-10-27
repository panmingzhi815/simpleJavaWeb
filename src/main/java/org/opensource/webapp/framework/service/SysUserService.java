package org.opensource.webapp.framework.service;

import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;

import java.util.Set;


public interface SysUserService {

	public Long saveSysUser(SysUser sysUser);
	
	public boolean removeSysUser(Long id);
	
	public boolean removeSysUser(SysUser sysUser);
	
	public SysUser getSysUserById(Long id);
	
	public PageResult<SysUser> getSysUserList(PageParam pageParam, SearchFilter searchFilter);

    public Set<SysRole> getSysUserRoleSet(Long userId);

    public boolean assignRoleToUser(Long roleId,Long userId);

    public boolean removeRoleFromUser(Long roleId, Long userId);
}
