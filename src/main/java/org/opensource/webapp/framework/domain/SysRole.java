package org.opensource.webapp.framework.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-08-26
 * Time: 下午9:33
 * 系统角色
 */
@Entity(name = "SysRole")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysRole extends BasicDomain implements Comparable<SysRole>{
	
	//名称
	private String name;
	//描述
	private String note;
    //顺序
	private int ordinal;
    //默认打开状态
	private String state="open";
    //父角色
    @ManyToOne
    @JoinColumn(name="parent",nullable = true,updatable = false)
    private SysRole parent;
    //只映射id,懒加载依然起效
    @Column(name = "parent",insertable = false,updatable = false)
    private Long parentId;
    //子角色列表
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "parent")
    private List<SysRole> sysRoleList;
	//拥有该角色的用户
	@ManyToMany(mappedBy = "sysRoleSet")
	private Set<SysUser> sysUserSet;
	//角色直接拥有的权限
	@ManyToMany(cascade = CascadeType.REMOVE)
	@JoinTable(
            name = "SysRole_SysPrivilege",
            joinColumns = { @JoinColumn(name = "SysRoleId", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "SysPrivilegeId", referencedColumnName = "id") })
	private Set<SysPrivilege> SysPrivilegeSet;

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

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public SysRole getParent() {
		return parent;
	}

	public void setParent(SysRole parent) {
		this.parent = parent;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

	public Set<SysUser> getSysUserSet() {
		return sysUserSet;
	}

	public void setSysUserSet(Set<SysUser> sysUserSet) {
		this.sysUserSet = sysUserSet;
	}

	public Set<SysPrivilege> getSysPrivilegeSet() {
		return SysPrivilegeSet;
	}

	public void setSysPrivilegeSet(Set<SysPrivilege> sysPrivilegeSet) {
		SysPrivilegeSet = sysPrivilegeSet;
	}

	@Override
	public int compareTo(SysRole o) {
		return this.getOrdinal() - o.getOrdinal();
	}
    
}
