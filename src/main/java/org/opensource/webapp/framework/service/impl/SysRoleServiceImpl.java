package org.opensource.webapp.framework.service.impl;

import java.util.Collections;
import java.util.List;

import org.opensource.webapp.framework.dao.SysRoleDao;
import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.service.SysRoleService;
import org.opensource.webapp.framework.service.WebAppServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-15
 * Time: 上午1:31
 * To change this template use File | Settings | File Templates.
 */
@Service("SysRoleService")
public class SysRoleServiceImpl extends PageServiceImpl<SysRole> implements SysRoleService {

	private Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);
	
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
	@Transactional
	public Long saveSysRole(SysRole sysRole) {
        try{
            logger.debug("开始保存系统角色{}",sysRole);
            if(sysRole.getParentId() != null){
                SysRole parent = sysRoleDao.findOne(sysRole.getParentId());
                if(parent != null){
                    sysRole.setParent(parent);
                    //让父菜单改变关闭标识
                    parent.setState("closed");
                    sysRoleDao.save(parent);
                    logger.debug("父角色 {} 不为空,改变其状态为:{}",sysRole,"colsed");
                }
            }
            if(sysRole.getId() != null){
                Long aLong = sysRoleDao.countByParent(sysRole.getId());
                sysRole.setState(aLong == 0 ? "open":"closed");
            }
            Long id = sysRoleDao.save(sysRole).getId();
            logger.debug("保存系统角色{}成功!",sysRole);
            return id;
        }catch (Exception e){
            throw new WebAppServiceException("保存系统角色失败!",e);
        }
	}

    @Override
    @Transactional
    public boolean removeSysRole(Long id) {
        try{
            logger.debug("开始删除系统角色:id={}",id);

        	SysRole sysRoleId = getSysRoleById(id);
            sysRoleDao.delete(sysRoleId);

            //如果父菜单的子项数量为0，则更新交菜单的关闭标识
            if(sysRoleId.getParentId() == null){
                return true;
            }
            Long count = sysRoleDao.countByParent(sysRoleId.getParentId());
            if(count == 0){
            	SysRole parent = getSysRoleById(sysRoleId.getParentId());
                parent.setState("open");
                saveSysRole(parent);
                logger.debug("父角色 {} 为空,改变其状态为:{}",parent,"open");
            }
            logger.debug("删除系统角色{}成功!",sysRoleId);
            return true;
        }catch (Exception e){
            logger.error("remove SysMenu ivoke exception when id :{},excpetion :{}",id,e);
            return false;
        }
    }

    @Override
    public SysRole getSysRoleById(Long id) {
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

	@Override
	public List<SysRole> getChildrenSysRoleById(Long id) {
		List<SysRole> resultList;
        if(id == null){
            resultList = sysRoleDao.findByParentIsNull();
        }else{
            resultList = sysRoleDao.findByParent(sysRoleDao.findOne(id));
        }
        //有菜单，则对菜单进行排序
        if(resultList != null){
            Collections.sort(resultList);
        }
        return resultList;
	}

}
