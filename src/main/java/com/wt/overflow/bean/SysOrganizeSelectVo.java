package com.wt.overflow.bean;


public class SysOrganizeSelectVo {

	private String id;
	
	private String text;
	
	private String parentId;
	
	private SysOrganize data;

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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public SysOrganize getData() {
		return data;
	}

	public void setData(SysOrganize data) {
		this.data = data;
	}
	
}
