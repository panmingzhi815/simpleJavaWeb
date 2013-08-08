package org.opensource.webapp.framework.domain.page;

import java.util.HashMap;
import java.util.Map;

public class PageParam {
	public static enum SortType {
		DESC, ASC;
	}

	// 起始位置
	private int start;
	// 显示行数
	private int limit;
	// 排序字段
	private Map<String, PageParam.SortType> sortMap = new HashMap<String, PageParam.SortType>();
	
	public PageParam(int start, int limit) {
		this.start = start;
		this.limit = limit;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPageNo(){
		if(limit <= 0) return 1;
		return start/limit + 1;
	}
	public Map<String, PageParam.SortType> getSortMap() {
		return sortMap;
	}
	public void setSortMap(Map<String, PageParam.SortType> sortMap) {
		this.sortMap = sortMap;
	}
	public static PageParam getFromEasyuiMap(Map<String, Object> map){
		Object object = map.get("rows");
		Object object2 = map.get("page");
		try {
			int r = (Integer) object;
			int p = (Integer) object2;
			return new PageParam((p-1)*r,r);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PageParam getFromEasyui(EasyUIPageParam easyUIPageParam) {
		int p = easyUIPageParam.getPage();
		int r = easyUIPageParam.getRows();
		return new PageParam((p - 1) * r, r);
	}
}
