package org.opensource.webapp.framework.service.impl;

import org.opensource.webapp.framework.dao.SysUserDao;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.service.BasicService;
import org.opensource.webapp.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysUserService")
public class SysUserServiceImpl extends BasicService<SysUser> implements
		SysUserService {

	@Autowired
	private SysUserDao sysUserDao;

	@Override
	@Transactional
	public boolean saveSysUser(SysUser sysUser) {
		try {
			sysUserDao.save(sysUser).getId();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeSysUser(Long id) {
		try {
			sysUserDao.delete(getSysUserById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean removeSysUser(SysUser sysUser) {
		try {
			sysUserDao.delete(sysUser);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public SysUser getSysUserById(Long id) {
		return sysUserDao.findOne(id);
	}

	@Override
	public PageResult<SysUser> getSysUserList(PageParam pageParam,SearchFilter searchFilter) {

		// specification 和 pageRequest
		// 为分页的两个参数,specification为查询时的构造条件,pageRequest为分页与排序的相关参数
		Specification<SysUser> specification = buildSpecification(searchFilter);
		PageRequest pageRequest = buildPageRequest(pageParam);

		// page 中包含总记录数与当前分页的数据
		Page<SysUser> page = sysUserDao.findAll(specification, pageRequest);

		return buildPageResult(page);
	}
	
}
