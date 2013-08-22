package org.opensource.webapp.framework.util;

import java.util.Collection;

public class StrUtil {
	
	public static boolean isNotEmpty(String s){
		return s != null && s.length() > 0; 
	}
	
	public static boolean isNotEmpty(Collection<?> c){
		return c != null && !c.isEmpty();
	}

}
