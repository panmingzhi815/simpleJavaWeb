package org.opensource.webapp.framework.page;

/**
 * 返回页面简单的json信息
 * @author pan
 *
 */
public class JsonSimpleResult {
	
	//返回结果
	private boolean success;
	//当success为false时的为原因
	private String message;
	
	public JsonSimpleResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String isMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
}
