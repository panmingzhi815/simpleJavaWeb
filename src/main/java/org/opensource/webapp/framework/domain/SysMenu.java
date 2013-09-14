package org.opensource.webapp.framework.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity(name="SysMenu")
public class SysMenu extends BasicDomain{

	private String text;
	private String url;
	private String iconCls;
	private int ordinal;
	private String descript;
	private String state="open";
	
	//直接拥有该菜单的用户
	@ManyToMany(mappedBy="sysMenuSet",cascade=CascadeType.REMOVE)
	private Set<SysUser> sysUserSet;
	
	//直接拥有该菜单的角色
	@ManyToMany(mappedBy="sysMenuSet",cascade=CascadeType.REMOVE)
	private Set<SysRole> sysRoleSet;

    @ManyToOne
    @JoinColumn(name="parent",nullable = true)
    private SysMenu parent;
    //只映射id,懒加载依然起效
    @Column(name = "parent",insertable = false,updatable = false)
    private Long parentId;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "parent")
    private List<SysMenu> sysMenuList;

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
