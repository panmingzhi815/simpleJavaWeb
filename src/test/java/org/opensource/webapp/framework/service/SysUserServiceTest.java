package org.opensource.webapp.framework.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.MapperConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.domain.page.EasyUIPageParam;
import org.opensource.webapp.framework.domain.page.PageParam;
import org.opensource.webapp.framework.domain.page.PageResult;

public class SysUserServiceTest extends AbstractTest{
	
	public void cleanup(){
		PageParam pageParam = new PageParam(0,Integer.MAX_VALUE);
		SysUser sysUser = new SysUser();
		PageResult<SysUser> sysUserList = sysUserService.getSysUserList(pageParam, sysUser);
		List<SysUser> rows = sysUserList.getRows();
		for (SysUser sysUser2 : rows) {
			sysUserService.removeSysUser(sysUser2.getId());
		}
	}
	
	@Test
	public void getSysUserListTest() throws JsonGenerationException, JsonMappingException, IOException{
		EasyUIPageParam easyuiParam = new EasyUIPageParam(10, 1);
		PageParam pageParam = PageParam.getFromEasyui(easyuiParam);
		SysUser sysUser = new SysUser("nickName", "loginName", "loginPassword");
		
		PageResult<SysUser> sysUserList = sysUserService.getSysUserList(pageParam, sysUser);
//		assertEquals(0,sysUserList.getRows().size());
		
		boolean saveSysUser = sysUserService.saveSysUser(sysUser);
		assertEquals(true,saveSysUser);		
		sysUserList = sysUserService.getSysUserList(pageParam, sysUser);
//		assertEquals(1,sysUserList.getTotal());
//		assertEquals(1,sysUserList.getRows().size());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(sysUserList.getRows());
		System.out.println(json);
		assertEquals(false, json.length() == 0);
	}

}
