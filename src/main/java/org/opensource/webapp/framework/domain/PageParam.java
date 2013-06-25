package org.opensource.webapp.framework.domain;

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
	private Map<String, PageParam.SortType> sortMap;
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

}
