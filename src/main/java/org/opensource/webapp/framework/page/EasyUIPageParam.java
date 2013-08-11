package org.opensource.webapp.framework.page;

import java.util.Map;

public class EasyUIPageParam {

	private int rows;
	private int page;

	public EasyUIPageParam() {
	}

	public EasyUIPageParam(int rows, int page) {
		this.rows = rows;
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public static EasyUIPageParam createFromMap(Map<String, Object> map) {
		Object object = map.get("rows");
		Object object2 = map.get("page");
		try {
			int r = (Integer) object;
			int p = (Integer) object2;
			return new EasyUIPageParam(r, p);
		} catch (Exception e) {
			return null;
		}
	}

}
