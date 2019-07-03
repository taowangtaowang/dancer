package com.wt.overflow.bean;

import java.util.Date;

public class SysModules {
	
	private Integer level; // 层级

	private String parent; // 父类

	private Boolean isLeaf; // 是否为字级

	private Boolean expanded = false; // 是否展开

	private Boolean loaded = false; // 是否初始化
	
    private String fId;

    private String fParentid;

    private Integer fLayers;

    private String fEncode;

    private String fFullname;

    private String fIcon;

    private String fUrladdress;

    private String fTarget;

    private Boolean fIsmenu;

    private Boolean fIsexpand;

    private Boolean fIspublic;

    private Boolean fAllowedit;

    private Boolean fAllowdelete;

    private Integer fSortcode;

    private Boolean fDeletemark;

    private Boolean fEnabledmark;

    private String fDescription;

    private Date fCreatortime;

    private String fCreatoruserid;

    private Date fLastmodifytime;

    private String fLastmodifyuserid;

    private Date fDeletetime;

    private String fDeleteuserid;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfParentid() {
        return fParentid;
    }

    public void setfParentid(String fParentid) {
        this.fParentid = fParentid == null ? null : fParentid.trim();
    }

    public Integer getfLayers() {
        return fLayers;
    }

    public void setfLayers(Integer fLayers) {
        this.fLayers = fLayers;
    }

    public String getfEncode() {
        return fEncode;
    }

    public void setfEncode(String fEncode) {
        this.fEncode = fEncode == null ? null : fEncode.trim();
    }

    public String getfFullname() {
        return fFullname;
    }

    public void setfFullname(String fFullname) {
        this.fFullname = fFullname == null ? null : fFullname.trim();
    }

    public String getfIcon() {
        return fIcon;
    }

    public void setfIcon(String fIcon) {
        this.fIcon = fIcon == null ? null : fIcon.trim();
    }

    public String getfUrladdress() {
        return fUrladdress;
    }

    public void setfUrladdress(String fUrladdress) {
        this.fUrladdress = fUrladdress == null ? null : fUrladdress.trim();
    }

    public String getfTarget() {
        return fTarget;
    }

    public void setfTarget(String fTarget) {
        this.fTarget = fTarget == null ? null : fTarget.trim();
    }

    public Boolean getfIsmenu() {
        return fIsmenu;
    }

    public void setfIsmenu(Boolean fIsmenu) {
        this.fIsmenu = fIsmenu;
    }

    public Boolean getfIsexpand() {
        return fIsexpand;
    }

    public void setfIsexpand(Boolean fIsexpand) {
        this.fIsexpand = fIsexpand;
    }

    public Boolean getfIspublic() {
        return fIspublic;
    }

    public void setfIspublic(Boolean fIspublic) {
        this.fIspublic = fIspublic;
    }

    public Boolean getfAllowedit() {
        return fAllowedit;
    }

    public void setfAllowedit(Boolean fAllowedit) {
        this.fAllowedit = fAllowedit;
    }

    public Boolean getfAllowdelete() {
        return fAllowdelete;
    }

    public void setfAllowdelete(Boolean fAllowdelete) {
        this.fAllowdelete = fAllowdelete;
    }

    public Integer getfSortcode() {
        return fSortcode;
    }

    public void setfSortcode(Integer fSortcode) {
        this.fSortcode = fSortcode;
    }

    public Boolean getfDeletemark() {
        return fDeletemark;
    }

    public void setfDeletemark(Boolean fDeletemark) {
        this.fDeletemark = fDeletemark;
    }

    public Boolean getfEnabledmark() {
        return fEnabledmark;
    }

    public void setfEnabledmark(Boolean fEnabledmark) {
        this.fEnabledmark = fEnabledmark;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription == null ? null : fDescription.trim();
    }

    public Date getfCreatortime() {
        return fCreatortime;
    }

    public void setfCreatortime(Date fCreatortime) {
        this.fCreatortime = fCreatortime;
    }

    public String getfCreatoruserid() {
        return fCreatoruserid;
    }

    public void setfCreatoruserid(String fCreatoruserid) {
        this.fCreatoruserid = fCreatoruserid == null ? null : fCreatoruserid.trim();
    }

    public Date getfLastmodifytime() {
        return fLastmodifytime;
    }

    public void setfLastmodifytime(Date fLastmodifytime) {
        this.fLastmodifytime = fLastmodifytime;
    }

    public String getfLastmodifyuserid() {
        return fLastmodifyuserid;
    }

    public void setfLastmodifyuserid(String fLastmodifyuserid) {
        this.fLastmodifyuserid = fLastmodifyuserid == null ? null : fLastmodifyuserid.trim();
    }

    public Date getfDeletetime() {
        return fDeletetime;
    }

    public void setfDeletetime(Date fDeletetime) {
        this.fDeletetime = fDeletetime;
    }

    public String getfDeleteuserid() {
        return fDeleteuserid;
    }

    public void setfDeleteuserid(String fDeleteuserid) {
        this.fDeleteuserid = fDeleteuserid == null ? null : fDeleteuserid.trim();
    }

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

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
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
    
	public void ModulesList() {
		if (null != fLayers)
			this.level = fLayers - 1;

		if ("0".equals(fParentid)) {
			this.parent = "0";
			this.isLeaf = false;
		}

		else {
			this.parent = fParentid;
			this.isLeaf = true;
		}

	}
}