package com.wt.overflow.bean;

import java.util.Date;

public class SysRoleauthorize {
    private String fId;

    private Integer fItemtype;

    private String fItemid;

    private Integer fObjecttype;

    private String fObjectid;

    private Integer fSortcode;

    private Date fCreatortime;

    private String fCreatoruserid;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public Integer getfItemtype() {
        return fItemtype;
    }

    public void setfItemtype(Integer fItemtype) {
        this.fItemtype = fItemtype;
    }

    public String getfItemid() {
        return fItemid;
    }

    public void setfItemid(String fItemid) {
        this.fItemid = fItemid == null ? null : fItemid.trim();
    }

    public Integer getfObjecttype() {
        return fObjecttype;
    }

    public void setfObjecttype(Integer fObjecttype) {
        this.fObjecttype = fObjecttype;
    }

    public String getfObjectid() {
        return fObjectid;
    }

    public void setfObjectid(String fObjectid) {
        this.fObjectid = fObjectid == null ? null : fObjectid.trim();
    }

    public Integer getfSortcode() {
        return fSortcode;
    }

    public void setfSortcode(Integer fSortcode) {
        this.fSortcode = fSortcode;
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
}