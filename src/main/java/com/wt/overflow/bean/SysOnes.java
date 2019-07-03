package com.wt.overflow.bean;

public class SysOnes {

	private String id; // 地区ID
	private String text; // 地区名称
	private String parentId; // 父类ID
	private String data = null;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void ModulesList() {
		if (!"0".equals(parentId)) {
			this.text = "　　　　"+text;
		}
	}
}
