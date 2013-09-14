package org.opensource.webapp.framework.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping(value="/admin")
public class adminAction {
	
	@RequestMapping(value="/adminIndex")
	public String adminIndex(Map<String, String> map){
		return "admin/adminIndex";	
	}
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin(Map<String, String> map){
		return "admin/adminLogin";	
	}

}