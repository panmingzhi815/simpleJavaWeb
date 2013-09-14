package org.opensource.webapp.framework.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.opensource.webapp.framework.domain.SysMenu;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-9-14
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */
public class SysMenuServiceTest extends AbstractTest {

    public void cleanUp(){
        List<SysMenu> allSysMenu = sysMenuService.getChildrenSysMenuById(null);
        for (SysMenu sm : allSysMenu){
            sysMenuService.removeSysMenu(sm);
        }
    }
    //测试前后清空所有数据
    @Before
    public void before(){
        cleanUp();
    }

    @After
    public void after(){
        before();
    }

    @Test
    public void saveAndList(){
        //添加你菜单
        for (int i=0 ; i < 5 ; i++){
            SysMenu sm = new SysMenu();
            sm.setText("主菜单");
            Long aLong = sysMenuService.saveSysMenu(sm);
            assertNotNull(aLong);
        }
        List<SysMenu> allSysMenu = sysMenuService.getAllSysMenu();
        assertEquals(5,allSysMenu.size());

        List<SysMenu> childrenSysMenuById = sysMenuService.getChildrenSysMenuById(null);
        assertEquals(5,childrenSysMenuById.size());

        //添加及搜索子菜单
        for (int i=0 ; i < 4 ; i++){
            SysMenu sm = new SysMenu();
            sm.setParent(childrenSysMenuById.get(0));
            sm.setText("子菜单");
            Long aLong = sysMenuService.saveSysMenu(sm);
            assertNotNull(aLong);
        }

        childrenSysMenuById = sysMenuService.getChildrenSysMenuById(childrenSysMenuById.get(0).getId());
        assertEquals(4,childrenSysMenuById.size());
    }

}
