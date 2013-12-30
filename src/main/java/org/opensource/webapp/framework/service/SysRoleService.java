package org.opensource.webapp.framework.service;

import java.util.List;

import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-15
 * Time: 上午1:19
 * To change this template use File | Settings | File Templates.
 */
public interface SysRoleService{

	public List<SysRole> getChildrenSysRoleById(Long id);

	public Long saveSysRole(SysRole sysRole);

	public boolean removeSysRole(Long id);

	public SysRole getSysRoleById(Long id);

	PageResult<SysRole> getPageList(PageParam pageParam,
			SearchFilter searchFilter);
}
