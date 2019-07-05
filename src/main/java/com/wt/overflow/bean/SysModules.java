package com.wt.overflow.bean;

import java.util.Date;

public class SysModules {
	
	private Integer level; // 层级

	private String parent; // 父类

	private Boolean isLeaf; // 是否为字级

	private Boolean expanded = false; // 是否展开

	private Boolean loaded = false; // 是否初始化

    private String id;

    private String parentid;

    private int layers;

    private String encode;

    private String fullname;

    private String icon;

    private String urladdress;

    private String target;

    private int ismenu;

    private int isexpand;

    private int ispublic;

    private int allowedit;

    private int allowdelete;

    private int sortcode;

    private int deletemark;

    private int enabledmark;

    private String description;

    private Date creatortime;

    private String creatoruserid;

    private Date lastmodifytime;

    private String lastmodifyuserid;

    private Date deletetime;

    private String deleteuserid;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Boolean getLeaf() {
        return isLeaf;
    }

    public void setLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getLoaded() {
        return loaded;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrladdress() {
        return urladdress;
    }

    public void setUrladdress(String urladdress) {
        this.urladdress = urladdress;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getIsmenu() {
        return ismenu;
    }

    public void setIsmenu(int ismenu) {
        this.ismenu = ismenu;
    }

    public int getIsexpand() {
        return isexpand;
    }

    public void setIsexpand(int isexpand) {
        this.isexpand = isexpand;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public int getAllowedit() {
        return allowedit;
    }

    public void setAllowedit(int allowedit) {
        this.allowedit = allowedit;
    }

    public int getAllowdelete() {
        return allowdelete;
    }

    public void setAllowdelete(int allowdelete) {
        this.allowdelete = allowdelete;
    }

    public int getSortcode() {
        return sortcode;
    }

    public void setSortcode(int sortcode) {
        this.sortcode = sortcode;
    }

    public int getDeletemark() {
        return deletemark;
    }

    public void setDeletemark(int deletemark) {
        this.deletemark = deletemark;
    }

    public int getEnabledmark() {
        return enabledmark;
    }

    public void setEnabledmark(int enabledmark) {
        this.enabledmark = enabledmark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatortime() {
        return creatortime;
    }

    public void setCreatortime(Date creatortime) {
        this.creatortime = creatortime;
    }

    public String getCreatoruserid() {
        return creatoruserid;
    }

    public void setCreatoruserid(String creatoruserid) {
        this.creatoruserid = creatoruserid;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getLastmodifyuserid() {
        return lastmodifyuserid;
    }

    public void setLastmodifyuserid(String lastmodifyuserid) {
        this.lastmodifyuserid = lastmodifyuserid;
    }

    public Date getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Date deletetime) {
        this.deletetime = deletetime;
    }

    public String getDeleteuserid() {
        return deleteuserid;
    }

    public void setDeleteuserid(String deleteuserid) {
        this.deleteuserid = deleteuserid;
    }

    public void ModulesList() {
		if (0 != layers)
			this.level = layers - 1;

		if ("0".equals(parentid)) {
			this.parent = "0";
			this.isLeaf = false;
		}

		else {
			this.parent = parentid;
			this.isLeaf = true;
		}

	}
}