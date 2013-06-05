package org.opensource.webapp.framework.domain;

import javax.persistence.Entity;

@Entity(name = "Holiday")
public class SysUser extends BasicDomain{

	private String nickName;
	private String loginName;
	private String loginPassword;

	public SysUser() {
	}

	public SysUser(String nickName, String loginName, String loginPassword) {
		this.nickName = nickName;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

}
