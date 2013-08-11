package org.opensource.webapp.framework.action;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.EasyUIPageParam;
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
@RequestMapping(value="/user")
public class SysUserAction {
	
	@Autowired
	private SysUserService sysUserService;
	
	
	@RequestMapping(value="/MyJsp")
	public String sayHello(Map<String, String> map){
		return "back/menu/MyJsp";	
	}
	
	@RequestMapping(value="/userManager.user")
	public String userManager(Map<String, String> map){
		return "back/promession/userManager";	
	}
	
	@RequestMapping(value="/userJSONPage.user")
	@ResponseBody
	public PageResult<SysUser> getJSONPage(HttpServletRequest request){
		PageParam pageParam = ServletUtil.getPageParam(request);
		SearchFilter searchFilter = ServletUtil.getSearchFilter(request);
		PageResult<SysUser> pageResult = sysUserService.getSysUserList(pageParam,searchFilter);
		return pageResult;
	}
	
	@RequestMapping(value="/saveSysUser.user")
	@ResponseBody
	public boolean saveSysUser(SysUser sysUser){
		return sysUserService.saveSysUser(sysUser);
	}
	
	@RequestMapping(value="/deleteSysUser.user")
	@ResponseBody
	public boolean deleteSysUser(Long id){
		return sysUserService.removeSysUser(id);
	}

}
