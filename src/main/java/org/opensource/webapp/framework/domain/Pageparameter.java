package org.opensource.webapp.framework.domain;

public class Pageparameter {

	private int page;
	private int rows;
	private String sort;
	private String order;

	public Pageparameter() {
	}

	public Pageparameter(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}

	public Pageparameter(int page, int rows, String sort, String order) {
		this.page = page;
		this.rows = rows;
		this.sort = sort;
		this.order = order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
