package org.opensource.webapp.framework.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: panmingzhi815
 * Date: 13-07-26
 * Time: 下午5:33
 * 系统菜单对象
 */
@Entity(name="SysMenu")
public class SysMenu extends BasicDomain implements Comparable<SysMenu>{

    //菜单显示名称
	private String text;
    //菜单连接地址
	private String url;
    //菜单图标对应的css名称
	private String iconCls;
    //菜单顺序
	private int ordinal;
    //菜单描述
	private String descript;
    //菜单默认打开状态
	private String state="open";

    //父菜单
    @ManyToOne
    @JoinColumn(name="parent",nullable = true)
    private SysMenu parent;
    //只映射id,懒加载依然起效
    @Column(name = "parent",insertable = false,updatable = false)
    private Long parentId;

    //子菜单
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "parent")
    private List<SysMenu> sysMenuList;

    //所有控件级别的功能选项
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<SysMenuController> sysMenuControllerSet;

    //所有数据级别功能的表达式选项
    @OneToMany(cascade = CascadeType.REMOVE)
    private Set<SysMenuExpression> sysMenuExpressionSet;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "sysMenu")
    private Set<SysPrivilege> sysPrivilegeSet;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public SysMenu getParent() {
        return parent;
    }

    public void setParent(SysMenu parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<SysMenu> getSysMenuList() {
        return sysMenuList;
    }

    public void setSysMenuList(List<SysMenu> sysMenuList) {
        this.sysMenuList = sysMenuList;
    }

    public Set<SysMenuController> getSysMenuControllerSet() {
        return sysMenuControllerSet;
    }

    public void setSysMenuControllerSet(Set<SysMenuController> sysMenuControllerSet) {
        this.sysMenuControllerSet = sysMenuControllerSet;
    }

    public Set<SysMenuExpression> getSysMenuExpressionSet() {
        return sysMenuExpressionSet;
    }

    public void setSysMenuExpressionSet(Set<SysMenuExpression> sysMenuExpressionSet) {
        this.sysMenuExpressionSet = sysMenuExpressionSet;
    }

    public Set<SysPrivilege> getSysPrivilegeSet() {
        return sysPrivilegeSet;
    }

    public void setSysPrivilegeSet(Set<SysPrivilege> sysPrivilegeSet) {
        this.sysPrivilegeSet = sysPrivilegeSet;
    }

    @Override
    public int compareTo(SysMenu o) {
        return this.getOrdinal() - o.getOrdinal();
    }
}
