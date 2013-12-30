package org.opensource.webapp.framework.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by panmingzhi on 13-12-27.
 */
public class SysRoleServiceTest extends AbstractTest {

    @Before
    public void before(){
        PageResult<SysRole> pageList = sysRoleService.getPageList(new PageParam(0, Integer.MAX_VALUE), new SearchFilter());
        Collection<SysRole> rows = pageList.getRows();
        for (SysRole sysRole:rows){
            sysRoleService.removeSysRole(sysRole.getId());
        }
    }

    @Test
    public void integrationTest(){
        PageResult<SysRole> pageList = sysRoleService.getPageList(new PageParam(0, Integer.MAX_VALUE), new SearchFilter());
        Assert.assertEquals(0,pageList.getRows().size());

        for(long i=1;i<=5;i++){
            SysRole sysRole = new SysRole();
            sysRole.setId(i);
            sysRole.setName("name"+i);
            sysRoleService.saveSysRole(sysRole);
        }

        pageList = sysRoleService.getPageList(new PageParam(0, Integer.MAX_VALUE), new SearchFilter());
        Assert.assertEquals(5,pageList.getRows().size());

        List<SysRole> childrenSysRoleById = sysRoleService.getChildrenSysRoleById(null);
        Assert.assertEquals(5,childrenSysRoleById.size());

        SysRole sysRoleById = sysRoleService.getSysRoleById(1L);
        Assert.assertEquals("name1",sysRoleById.getName());

        sysRoleService.removeSysRole(1L);
        sysRoleById = sysRoleService.getSysRoleById(1L);
        Assert.assertNull(sysRoleById);

        childrenSysRoleById = sysRoleService.getChildrenSysRoleById(null);
        Assert.assertEquals(4,childrenSysRoleById.size());

        sysRoleById = sysRoleService.getSysRoleById(2L);
        Assert.assertEquals("name2",sysRoleById.getName());

        SysRole sysRole = new SysRole();
        sysRole.setId(6L);
        sysRole.setName("name6");
        sysRole.setParent(sysRoleById);
        sysRoleService.saveSysRole(sysRole);

        childrenSysRoleById = sysRoleService.getChildrenSysRoleById(2L);
        Assert.assertEquals(1,childrenSysRoleById.size());

        SysRole sysRole2 = new SysRole();
        sysRole2.setId(7L);
        sysRole2.setName("name7");
        sysRole2.setParent(sysRoleById);
        sysRoleService.saveSysRole(sysRole2);

        childrenSysRoleById = sysRoleService.getChildrenSysRoleById(2L);
        Assert.assertEquals(2,childrenSysRoleById.size());

    }

    @Test
    public void cascadeRemove(){
        SysRole sysRole = new SysRole();
        sysRole.setId(1L);
        sysRole.setName("name1");
        sysRoleService.saveSysRole(sysRole);

        SysRole sysRoleById = sysRoleService.getSysRoleById(1L);
        assertNotNull(sysRoleById);

        SysRole sysRole2 = new SysRole();
        sysRole2.setId(2L);
        sysRole2.setName("name2");
        sysRole2.setParent(sysRole);
        sysRoleService.saveSysRole(sysRole2);

        PageResult<SysRole> pageList = sysRoleService.getPageList(new PageParam(0, Integer.MAX_VALUE), new SearchFilter());
        Assert.assertEquals(2,pageList.getRows().size());

        SysRole sysRole3 = new SysRole();
        sysRole3.setId(3L);
        sysRole3.setName("name3");
        sysRole3.setParent(sysRole);
        sysRoleService.saveSysRole(sysRole3);
        List<SysRole> childrenSysRoleById = sysRoleService.getChildrenSysRoleById(1L);
        assertEquals(2,childrenSysRoleById.size());

        sysRoleService.removeSysRole(1L);
        pageList = sysRoleService.getPageList(new PageParam(0, Integer.MAX_VALUE), new SearchFilter());
        Assert.assertEquals(0,pageList.getRows().size());
    }

}
