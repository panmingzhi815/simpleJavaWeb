package org.opensource.webapp.framework.action;

import org.opensource.webapp.framework.domain.SysRole;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;
import org.opensource.webapp.framework.page.ServletUtil;
import org.opensource.webapp.framework.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value="/admin/user/")
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
	public String saveSysUser(SysUser sysUser){
        return "{id:"+sysUserService.saveSysUser(sysUser)+"}";
	}

	@RequestMapping(value="/deleteSysUser")
    @ResponseBody
	public boolean deleteSysUser(Long id){
		 return  sysUserService.removeSysUser(id);
	}

    @RequestMapping(value="/userRoleJSONPage")
    @ResponseBody
    public PageResult<SysRole> getUserRoleJSONPage(Long userId){
        Set<SysRole> sysRoleSet = sysUserService.getSysUserRoleSet(userId);
        return new PageResult<SysRole>(sysRoleSet.size(),sysRoleSet);
    }

    @RequestMapping(value="/assignRoleToUser")
    @ResponseBody
    public boolean assignRoleToUser(
            @RequestParam(value = "roleId") Long roleId,
            @RequestParam(value = "userId") Long userId){
        return sysUserService.assignRoleToUser(roleId,userId);
    }

    @RequestMapping(value="/removeRoleFromUser")
    @ResponseBody
    public boolean removeRoleFromUser(
            @RequestParam(value = "roleId") Long roleId,
            @RequestParam(value = "userId") Long userId){
        return sysUserService.removeRoleFromUser(roleId,userId);
    }

}
