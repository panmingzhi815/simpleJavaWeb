package org.opensource.webapp.framework.service.impl;

import org.opensource.webapp.framework.dao.SysMenuDao;
import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.service.SysMenuService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

public class SysMenuServiceImpl implements SysMenuService {

	@Qualifier
	private SysMenuDao sysMenuDao;

	@Override
	@Transactional
	public SysMenu createSysMenu(SysMenu sysMenu) {
		return sysMenuDao.save(sysMenu);
	}

}
