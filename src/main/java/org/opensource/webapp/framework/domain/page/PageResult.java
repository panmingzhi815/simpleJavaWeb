package org.opensource.webapp.framework.domain.page;

import java.util.ArrayList;
import java.util.List;

import org.opensource.webapp.framework.MapOutUtil;

public class PageResult<T> {
	
	// 总行数
	private long total;
	// 当前行结果集
	private List<T> rows = new ArrayList<T>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return MapOutUtil.mapOutList(rows);
	}

	public void setRows(List<T> rows) {
		this.rows.clear();
		this.rows.addAll(rows);
	}

}
