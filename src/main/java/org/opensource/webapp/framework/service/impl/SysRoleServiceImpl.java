package org.opensource.webapp.framework.service.impl;

import org.opensource.webapp.framework.dao.SysRoleDao;
import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-15
 * Time: 上午1:31
 * To change this template use File | Settings | File Templates.
 */
@Service("SysRoleService")
public class SysRoleServiceImpl extends PageServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    public Long save(SysRole sysRole) {
        return sysRoleDao.save(sysRole).getId();
    }

    @Override
    public boolean remove(Long id) {
        try {
            sysRoleDao.delete(getById(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public SysRole getById(Long id) {
        return sysRoleDao.findOne(id);
    }

    @Override
    public PageResult<SysRole> getPageList(PageParam pageParam, SearchFilter searchFilter) {
        // specification 和 pageRequest
        // 为分页的两个参数,specification为查询时的构造条件,pageRequest为分页与排序的相关参数
        Specification<SysRole> specification = buildSpecification(searchFilter);
        PageRequest pageRequest = buildPageRequest(pageParam);

        // page 中包含总记录数与当前分页的数据
        Page<SysRole> page = sysRoleDao.findAll(specification, pageRequest);

        return buildPageResult(page);
    }
}
