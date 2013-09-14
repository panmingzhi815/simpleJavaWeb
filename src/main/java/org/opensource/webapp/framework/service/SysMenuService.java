package org.opensource.webapp.framework.service;

import java.util.List;

import org.opensource.webapp.framework.domain.SysMenu;

public interface SysMenuService {
	
	public Long saveSysMenu(SysMenu sysMenu);

    public boolean removeSysMenu(Long id);

	public SysMenu getSysMenuById(Long id);

	public List<SysMenu> getAllSysMenu();

    public List<SysMenu> getChildrenSysMenuById(Long id);
}
