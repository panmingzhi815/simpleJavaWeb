package org.opensource.webapp.framework.page;

import org.opensource.webapp.framework.util.LazySerializationUtil;

import java.util.ArrayList;
import java.util.Collection;

public class PageResult<T> {
	
	// 总行数
	private long total;
	// 当前行结果集
	private Collection<T> rows = new ArrayList<T>();

    public PageResult() {
    }

    public PageResult(long total, Collection<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Collection<T> getRows() {
        return LazySerializationUtil.serializeCollections(rows);
//		return MapOutUtil.mapOutList(rows);
	}

	public void setRows(Collection<T> rows) {
		this.rows.clear();
		this.rows.addAll(rows);
	}

}
