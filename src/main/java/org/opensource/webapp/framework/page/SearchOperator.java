package org.opensource.webapp.framework.page;

public class SearchOperator {
	
	private String fieldName;
	private String fieldValue;
	private SearchType searchType;

	public SearchOperator(String fieldName,String fieldValue, SearchType searchType) {
		this.fieldName = fieldName;
		this.searchType = searchType;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
