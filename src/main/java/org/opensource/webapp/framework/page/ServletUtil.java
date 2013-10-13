package org.opensource.webapp.framework.page;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.opensource.webapp.framework.util.StrUtil;

public class ServletUtil {
	
	public static PageParam getPageParam(HttpServletRequest request){
		String row = request.getParameter("rows");
		String page = request.getParameter("page");
		if(StrUtil.isNotEmpty(page) && StrUtil.isNotEmpty(page)){
			int p = Integer.parseInt(page);
			int r = Integer.parseInt(row);
			return new PageParam((p - 1) * r, r);
		}
		return new PageParam(0, 10);
	}
	
	@SuppressWarnings("unchecked")
	public static SearchFilter getSearchFilter(HttpServletRequest request){
		SearchFilter searchFilter = new SearchFilter();
		Enumeration<String> enumeration = request.getParameterNames();
		while(enumeration.hasMoreElements()){
			String string = enumeration.nextElement();
			if(!string.startsWith(SearchFilter.STARTPREFIX)) continue;
			
			String[] split = string.split(SearchFilter.SPLITTAG);
			if(split.length != 3) continue;
			
			SearchType searchType = SearchType.parseString(split[1]);
			String fieldName = split[2];
			String fieldValue = request.getParameter(string);
			SearchOperator searchOperator = new SearchOperator(fieldName,fieldValue,searchType);
			searchFilter.getSearchOperatorList().add(searchOperator);
		}
		return searchFilter;
	}

}
