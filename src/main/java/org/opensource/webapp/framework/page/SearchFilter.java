package org.opensource.webapp.framework.page;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class SearchFilter {

	public static final String STARTPREFIX = "search";
	public static final String SPLITTAG = "_";
	
	public List<SearchOperator> searchOperatorList = new ArrayList<SearchOperator>();

	public List<SearchOperator> getSearchOperatorList() {
		return searchOperatorList;
	}

	public void setSearchOperatorList(List<SearchOperator> searchOperatorList) {
		this.searchOperatorList = searchOperatorList;
	}

}
