package org.opensource.webapp.framework.action;

import java.util.Map;

import org.opensource.webapp.framework.service.SysMenuService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/menu")
public class SysMenuActionImpl {
	
	@Qualifier
	private SysMenuService sysMenuService;
	
	@RequestMapping(value="/MyJsp")
	public String sayHello(Map<String, String> map){
		return "back/menu/MyJsp";	
	}

}
