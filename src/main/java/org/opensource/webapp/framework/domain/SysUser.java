package org.opensource.webapp.framework.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.opensource.webapp.framework.domain.enums.SysUserState;


@Entity(name = "SysUser")
public class SysUser extends BasicDomain {

	private String loginName;
	private String loginPassword;
	private String nickName;
	private String phone;
	private String email;
	@Lob
	@Basic(fetch=FetchType.EAGER)
	private byte[] headImage;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	@Enumerated(EnumType.STRING)
	private SysUserState sysUserState;
	//用户所拥有的角色
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(name="SysUser_SysRole",joinColumns={@JoinColumn(name="SysUserId",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="SysRoleId",referencedColumnName="id")})
	private Set<SysRole> sysRoleSet = new HashSet<SysRole>();
	
	//用户直接拥有的菜单
	@ManyToMany(cascade=CascadeType.REMOVE)
	@JoinTable(name="SysUser_SysMenu",joinColumns={@JoinColumn(name="SysUserId",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="SysMenuId",referencedColumnName="id")})
	private Set<SysMenu> sysMenuSet = new HashSet<SysMenu>();

	public SysUser() {
	}

	public SysUser(String nickName, String loginName, String loginPassword) {
		this.nickName = nickName;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getHeadImage() {
		return headImage;
	}

	public void setHeadImage(byte[] headImage) {
		this.headImage = headImage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public SysUserState getSysUserState() {
		return sysUserState;
	}

	public void setSysUserState(SysUserState sysUserState) {
		this.sysUserState = sysUserState;
	}

	public Set<SysRole> getSysRoleSet() {
		return sysRoleSet;
	}

	public void setSysRoleSet(Set<SysRole> sysRoleSet) {
		this.sysRoleSet = sysRoleSet;
	}

	public Set<SysMenu> getSysMenuSet() {
		return sysMenuSet;
	}

	public void setSysMenuSet(Set<SysMenu> sysMenuSet) {
		this.sysMenuSet = sysMenuSet;
	}

	public Set<SysMenu> getAllSysMenuSet(){
		Set<SysMenu> sysMenuSet = new HashSet<SysMenu>();
		sysMenuSet.addAll(getSysMenuSet());	
		for (SysRole sysRole : getSysRoleSet()) {
			sysMenuSet.addAll(sysRole.getSysMenuSet());
		}
		return sysMenuSet;
	}

}

