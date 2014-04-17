package org.opensource.webapp.framework.action;

import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.service.SysRoleService;
import org.opensource.webapp.framework.util.LazySerializationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Role: Administrator
 * Date: 13-9-15
 * Time: 上午1:19
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value="/admin/role/")
public class SysRoleAction {

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value="/roleManager")
    public String RoleManager(Map<String, String> map){
        return "admin/roleManager";
    }

    @RequestMapping(value="/saveSysRole")
    @ResponseBody
    public String saveSysRole(SysRole sysRole){
        return "{id:"+ sysRoleService.saveSysRole(sysRole) +"}";
    }

    @RequestMapping(value="/deleteSysRole")
    @ResponseBody
    public boolean deleteSysRole(Long id){
        return sysRoleService.removeSysRole(id);
    }

    @RequestMapping(value="/getSysRoleList")
    @ResponseBody
    public List<SysRole> getSysRoleList(Long id){
        return LazySerializationUtil.serializeList(sysRoleService.getChildrenSysRoleById(id));
    }
}
