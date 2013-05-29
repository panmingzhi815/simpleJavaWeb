package org.opensource.webapp.framework.domain;

import java.util.Map;
import java.util.Set;

public class EasyuiTree {
	
	private String id;
	private String text;
	private String iconCls;
	private String state;
	private boolean checked;
	private Map<String,String> attributes;
	private Set<EasyuiTree> easyuiTreeSet;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Set<EasyuiTree> getEasyuiTreeSet() {
		return easyuiTreeSet;
	}
	public void setEasyuiTreeSet(Set<EasyuiTree> easyuiTreeSet) {
		this.easyuiTreeSet = easyuiTreeSet;
	}
	
	
}
