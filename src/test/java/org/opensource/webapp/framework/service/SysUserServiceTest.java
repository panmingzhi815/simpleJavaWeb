package org.opensource.webapp.framework.service;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.opensource.webapp.framework.domain.SysUser;
import org.opensource.webapp.framework.page.EasyUIPageParam;
import org.opensource.webapp.framework.page.PageParam;
import org.opensource.webapp.framework.page.PageResult;
import org.opensource.webapp.framework.page.SearchFilter;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SysUserServiceTest extends AbstractTest{
	
	public void cleanup(){
		PageParam pageParam = new PageParam(0,Integer.MAX_VALUE);
		SearchFilter filter = new SearchFilter();
		PageResult<SysUser> sysUserList = sysUserService.getSysUserList(pageParam, filter);
		List<SysUser> rows = sysUserList.getRows();
		for (SysUser sysUser2 : rows) {
			sysUserService.removeSysUser(sysUser2.getId());
		}
	}
	
	@Test
	public void getSysUserListTest() throws JsonGenerationException, JsonMappingException, IOException{
		EasyUIPageParam easyuiParam = new EasyUIPageParam(10, 1);
		PageParam pageParam = PageParam.getFromEasyui(easyuiParam);
		SearchFilter filter = new SearchFilter();
		SysUser sysUser = new SysUser("nickName", "loginName", "loginPassword");
		
		PageResult<SysUser> sysUserList = sysUserService.getSysUserList(pageParam, filter);
//		assertEquals(0,sysUserList.getRows().size());

        Long aLong = sysUserService.saveSysUser(sysUser);
        assertNotNull(aLong);
		sysUserList = sysUserService.getSysUserList(pageParam, filter);
//		assertEquals(1,sysUserList.getTotal());
//		assertEquals(1,sysUserList.getRows().size());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(sysUserList.getRows());
		System.out.println(json);
		assertEquals(false, json.length() == 0);
	}

}
