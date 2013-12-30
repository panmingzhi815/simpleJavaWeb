package org.opensource.webapp.framework.action;

import org.opensource.webapp.framework.domain.SysMenu;
import org.opensource.webapp.framework.service.SysMenuService;
import org.opensource.webapp.framework.util.MapOutUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/admin/menu/")
public class SysMenuAction {

	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/menuManager")
	public String menuManager(Map<String, String> map){
		return "admin/menuManager";	
	}

    @RequestMapping(value="/saveSysMenu")
    @ResponseBody
    public String saveSysMenu(SysMenu sysMenu){
        return "{id:"+ sysMenuService.saveSysMenu(sysMenu) +"}";
    }

    @RequestMapping(value="/deleteSysMenu")
    @ResponseBody
    public boolean deleteSysMenu(Long id){
        return sysMenuService.removeSysMenu(id);
    }

    @RequestMapping(value="/getSysMenuList")
    @ResponseBody
    public List<SysMenu> getSysMenuList(Long id){
        return MapOutUtil.mapOutList(sysMenuService.getChildrenSysMenuById(id));
    }
	
}
