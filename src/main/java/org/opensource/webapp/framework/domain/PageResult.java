package org.opensource.webapp.framework.domain;

import java.util.List;

@SuppressWarnings("rawtypes")
public class PageResult {

	private int total;
	private List rows;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
