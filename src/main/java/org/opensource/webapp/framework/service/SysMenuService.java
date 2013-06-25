package org.opensource.webapp.framework.service;

import java.util.List;

import org.opensource.webapp.framework.domain.SysMenu;

public interface SysMenuService {
	
	public SysMenu saveSysMenu(SysMenu sysMenu);
	
	public void removeSysMenu(SysMenu sysMenu);
	
	public SysMenu getSysMenuById(Long id);
	
	public List<SysMenu> getAllSysMenu();
	
}
