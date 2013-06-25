package org.opensource.webapp.framework.domain;

import java.util.List;

public class PageResult<T> {
	
	// 总行数
	private long total;
	// 当前行结果集
	private List<T> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
