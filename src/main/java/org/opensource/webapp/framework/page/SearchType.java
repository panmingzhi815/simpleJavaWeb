package org.opensource.webapp.framework.page;


public enum SearchType {
	
	eq("等于"), start("起头"),end("结尾"),any("任意包含"), gt("大于"), lt("小于"), gte("大于且等于"), lte("小于且等于");
	
	private String desc;
	
	private SearchType(String desc){
		this.desc = desc;
	}
	
	public String toString(){
		return desc;
	}
	
	public static SearchType parseString(String parse){
		SearchType[] values = SearchType.values();
		for (SearchType searchType : values) {
			if(searchType.name().equals(parse)){
				return searchType;
			}
		}
		throw new PageException(String.format("%s cannot be parse to SearchType!", parse));
	}

}
