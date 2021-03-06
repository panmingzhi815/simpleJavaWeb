package org.opensource.webapp.framework.service.impl;

import org.opensource.webapp.framework.dao.SysMenuDao;
import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("sysMenuService")
public class SysMenuServiceImpl implements SysMenuService {

    private Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    @Transactional
    public Long saveSysMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() != null) {
            SysMenu parent = sysMenuDao.findOne(sysMenu.getParentId());
            if (parent != null) {
                sysMenu.setParent(parent);
                //让父菜单改变关闭标识
                parent.setState("closed");
                sysMenu.setParent(parent);
                sysMenuDao.save(parent);
            }
        }

        if (sysMenu.getId() != null) {
            Long aLong = sysMenuDao.countByParent(sysMenu.getId());
            sysMenu.setState(aLong == 0 ? "open" : "closed");
        }

        return sysMenuDao.save(sysMenu).getId();
    }

    @Override
    @Transactional
    public boolean removeSysMenu(Long id) {
        try {
            SysMenu sysMenuById = getSysMenuById(id);
            sysMenuDao.delete(sysMenuById);

            //如果父菜单的子项数量为0，则更新交菜单的关闭标识
            if (sysMenuById.getParentId() == null) {
                return true;
            }
            Long count = sysMenuDao.countByParent(sysMenuById.getParentId());
            if (count == 0) {
                SysMenu parent = getSysMenuById(sysMenuById.getParentId());
                parent.setState("open");
                saveSysMenu(parent);
            }
            return true;
        } catch (Exception e) {
            logger.error("remove SysMenu ivoke exception when id :{},excpetion :{}", id, e);
            return false;
        }
    }

    @Override
    public SysMenu getSysMenuById(Long id) {
        return sysMenuDao.findOne(id);
    }

    @Override
    public List<SysMenu> getAllSysMenu() {
        return (List<SysMenu>) sysMenuDao.findAll();
    }

    @Override
    public List<SysMenu> getChildrenSysMenuById(Long id) {
        List<SysMenu> resultList;
        if (id == null) {
            resultList = sysMenuDao.findByParentIsNull();
        } else {
            resultList = sysMenuDao.findByParent(sysMenuDao.findOne(id));
        }
        //有菜单，则对菜单进行排序
        if (resultList != null) {
            Collections.sort(resultList);
        }
        return resultList;
    }
}
