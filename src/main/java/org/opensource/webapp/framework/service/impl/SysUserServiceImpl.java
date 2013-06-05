package org.opensource.webapp.framework.service.impl;

import org.opensource.webapp.framework.dao.SysUserDao;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserService") 
public class SysUserServiceImpl implements SysUserService{

	 @Autowired 
	 private SysUserDao sysUserDao;
	 
	 @Override
	 @Transactional
	 public SysUser createSysUser(SysUser sysUser) {
		 return sysUserDao.save(sysUser);
	 }
}
