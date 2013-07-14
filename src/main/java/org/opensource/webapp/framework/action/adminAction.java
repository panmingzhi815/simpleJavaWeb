package org.opensource.webapp.framework.action;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class adminAction {
	
	@RequestMapping(value="/adminIndex")
	public String adminIndex(Map<String, String> map){
		return "back/adminIndex";	
	}
	
	@RequestMapping(value="/adminLogin")
	public String adminLogin(Map<String, String> map){
		return "back/adminLogin";	
	}

}