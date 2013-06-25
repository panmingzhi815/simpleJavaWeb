package org.opensource.webapp.framework.domain.enums;

public enum SysUserState {

	normal, forbidden, expire;

	final private static String NORMAL = "正常";
	final private static String FORBIDDEN = "禁用";
	final private static String EXPIRE = "过期";

	public static String parse(SysUserState sysUserState) {
		switch (sysUserState) {
		case normal:
			return SysUserState.NORMAL;
		case forbidden:
			return SysUserState.FORBIDDEN;
		case expire:
			return SysUserState.EXPIRE;
		default:
			return "";
		}
	}

	@Override
	public String toString() {
		return parse(this);
	}
	
	
}
