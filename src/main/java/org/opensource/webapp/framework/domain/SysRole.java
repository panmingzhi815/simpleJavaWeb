package org.opensource.webapp.framework.domain;

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
public class SysRole extends BasicDomain {

	private String name;
	private String note;

	// 拥有该角色的用户
	@ManyToMany(mappedBy = "sysRoleSet")
	private Set<SysUser> sysUserSet;

	// 角色直接拥有的权限
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
}
