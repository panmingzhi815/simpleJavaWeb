package org.opensource.webapp.framework.domain;

import javax.persistence.Entity;

@Entity(name = "Holiday")
public class SysUser extends BasicDomain{

	//昵称
	private String nickName;
	///登陆用户名
	private String loginName;
	//登陆密码
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
