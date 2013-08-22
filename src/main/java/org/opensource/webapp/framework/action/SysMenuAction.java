package org.opensource.webapp.framework.action;

import java.util.Map;

import org.opensource.webapp.framework.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class SysMenuAction {

	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/menuManager")
	public String menuManager(Map<String, String> map){
		return "admin/menuManager";	
	}
	
}
