package org.opensource.webapp.framework.domain;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-10-26
 * Time: 下午6:07
 * 系统权限,用户与角色不应直接分配菜单,应是分配相应的权限.
 */
@Entity(name = "SysPrivilege")
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SysPrivilege extends BasicDomain{
    //系统菜单
    @ManyToOne
    private SysMenu sysMenu;

    @ElementCollection
    private Set<String> sysMenuControllerSet;

    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<SysMenuExpression> sysMenuExpressionSet;

    //直接拥有该权限的用户
    @ManyToMany(mappedBy="SysPrivilegeSet")
    private Set<SysUser> sysUserSet;

    //直接拥有该权限的角色
    @ManyToMany(mappedBy="SysPrivilegeSet")
    private Set<SysRole> sysRoleSet;

    public SysMenu getSysMenu() {
        return sysMenu;
    }

    public void setSysMenu(SysMenu sysMenu) {
        this.sysMenu = sysMenu;
    }

    public Set<String> getSysMenuControllerSet() {
        return sysMenuControllerSet;
    }

    public void setSysMenuControllerSet(Set<String> sysMenuControllerSet) {
        this.sysMenuControllerSet = sysMenuControllerSet;
    }

    public Set<SysMenuExpression> getSysMenuExpressionSet() {
        return sysMenuExpressionSet;
    }

    public void setSysMenuExpressionSet(Set<SysMenuExpression> sysMenuExpressionSet) {
        this.sysMenuExpressionSet = sysMenuExpressionSet;
    }

    public Set<SysUser> getSysUserSet() {
        return sysUserSet;
    }

    public void setSysUserSet(Set<SysUser> sysUserSet) {
        this.sysUserSet = sysUserSet;
    }

    public Set<SysRole> getSysRoleSet() {
        return sysRoleSet;
    }

    public void setSysRoleSet(Set<SysRole> sysRoleSet) {
        this.sysRoleSet = sysRoleSet;
    }
}
