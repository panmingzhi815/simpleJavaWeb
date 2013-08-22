package org.opensource.webapp.framework.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.simple.JSONValue;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.JsonSimpleResult;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.page.ServletUtil;
import org.opensource.webapp.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/admin")
public class SysUserAction {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value="/userManager")
	public String userManager(Map<String, String> map){
		return "admin/userManager";	
	}
	
	@RequestMapping(value="/userJSONPage")
	@ResponseBody
	public PageResult<SysUser> getJSONPage(HttpServletRequest request){
		PageParam pageParam = ServletUtil.getPageParam(request);
		SearchFilter searchFilter = ServletUtil.getSearchFilter(request);
		PageResult<SysUser> pageResult = sysUserService.getSysUserList(pageParam,searchFilter);
		return pageResult;
	}
	
	@RequestMapping(value="/saveSysUser")
	@ResponseBody
	public String saveSysUser(SysUser sysUser) throws JsonGenerationException, JsonMappingException, IOException{
		boolean saveSysUser = sysUserService.saveSysUser(sysUser);
		return saveSysUser == true ? "1":"0";
	}
	
	@RequestMapping(value="/deleteSysUser")
	public String deleteSysUser(Long id){
		 boolean removeSysUser = sysUserService.removeSysUser(id);
		 return removeSysUser == true ? "1":"0";
	}

}
