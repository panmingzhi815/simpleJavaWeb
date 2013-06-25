package org.opensource.webapp.framework.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "SysRole")
public class SysRole extends BasicDomain {

	private String name;
	private String note;

	// 拥有该角色的用户
	@ManyToMany(cascade = CascadeType.REMOVE)
	private Set<SysUser> sysUserSet;

	// 角色直接拥有的菜单
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(name = "SysRole_SysMenu", joinColumns = { @JoinColumn(name = "SysRoleId", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "SysMenuId", referencedColumnName = "id") })
	private Set<SysMenu> sysMenuSet;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Set<SysUser> getSysUserSet() {
		return sysUserSet;
	}

	public void setSysUserSet(Set<SysUser> sysUserSet) {
		this.sysUserSet = sysUserSet;
	}

	public Set<SysMenu> getSysMenuSet() {
		return sysMenuSet;
	}

	public void setSysMenuSet(Set<SysMenu> sysMenuSet) {
		this.sysMenuSet = sysMenuSet;
	}

}
