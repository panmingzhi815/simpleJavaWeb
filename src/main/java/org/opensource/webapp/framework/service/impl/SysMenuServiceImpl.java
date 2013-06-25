package org.opensource.webapp.framework.service.impl;

import java.util.List;

import org.opensource.webapp.framework.dao.SysMenuDao;
import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.service.SysMenuService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysMenuService") 
public class SysMenuServiceImpl implements SysMenuService {

	@Qualifier
	private SysMenuDao sysMenuDao;

	@Override
	@Transactional
	public SysMenu saveSysMenu(SysMenu sysMenu) {
		return sysMenuDao.save(sysMenu);
	}

	@Override
	@Transactional
	public void removeSysMenu(SysMenu sysMenu) {
		sysMenuDao.delete(sysMenu);
	}

	@Override
	public SysMenu getSysMenuById(Long id) {
		return sysMenuDao.findOne(id);
	}

	@Override
	public List<SysMenu> getAllSysMenu() {
		return (List<SysMenu>) sysMenuDao.findAll();
	}
	
}
