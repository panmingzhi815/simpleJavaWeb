package org.opensource.webapp.framework.service.impl;

import java.util.List;

import org.opensource.webapp.framework.dao.SysMenuDao;
import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.service.SysMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("sysMenuService") 
public class SysMenuServiceImpl implements SysMenuService {

    private Logger logger = LoggerFactory.getLogger(SysMenuServiceImpl.class);

	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	@Transactional
	public Long saveSysMenu(SysMenu sysMenu) {
        if(sysMenu.getParentId() != null){
            SysMenu parent = sysMenuDao.findOne(sysMenu.getParentId());
            if(parent != null){
                sysMenu.setParent(parent);
                //让父菜单改变关闭标识
                parent.setState("closed");
                parent.setParentId(0L);
                sysMenuDao.save(parent);
            }
        }
		return sysMenuDao.save(sysMenu).getId();
	}

    @Override
    @Transactional
    public boolean removeSysMenu(Long id) {
        try{
            SysMenu sysMenuById = getSysMenuById(id);
            sysMenuDao.delete(sysMenuById);

            //如果父菜单的子项数量为0，则更新交菜单的关闭标识
            if(sysMenuById.getParentId() == null){
                return true;
            }
            Long count = sysMenuDao.countByParent(sysMenuById.getParentId());
            if(count == 0){
                SysMenu parent = getSysMenuById(sysMenuById.getParentId());
                parent.setState("open");
                saveSysMenu(parent);
            }
            return true;
        }catch (Exception e){
            logger.error("remove SysMenu ivoke exception when id :{},excpetion :{}",id,e);
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
        if(id == null){
            return sysMenuDao.findByParentIsNull();
        }
        return sysMenuDao.findByParent(sysMenuDao.findOne(id));
    }
}
