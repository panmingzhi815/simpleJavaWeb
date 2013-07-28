package org.opensource.webapp.framework.action;

import java.util.Map;

import org.opensource.webapp.framework.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class SysUserAction {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/MyJsp")
	public String sayHello(Map<String, String> map){
		return "back/menu/MyJsp";	
	}
	
	@RequestMapping(value="/userManager.sys")
	public String userManager(Map<String, String> map){
		return "back/promession/userManager";	
	}

}
