package org.opensource.webapp.framework.domain.enums;

public enum EmailSendStateEnum {
	sendSuccess,waitSend,sendFaile;
	private static final String SENDSUCCESS="发送成功";
	private static final String WAITSEND="等待发送";
	private static final String SENDFAILE="发送失败";
	
	public String toString(){
		switch (this) {
		case sendSuccess:
			return SENDSUCCESS;
		case waitSend:
			return WAITSEND;
		case sendFaile:
			return SENDFAILE;
		default:
			return "";
		}
	}
}
