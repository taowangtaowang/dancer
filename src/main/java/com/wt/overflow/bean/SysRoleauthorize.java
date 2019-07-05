package com.wt.overflow.bean;

import java.util.Date;

public class SysRoleauthorize {
    private String id;

    private int itemtype;

    private String itemid;

    private int objecttype;

    private String objectid;

    private int sortcode;

    private Date creatortime;

    private String creatoruserid;

    private int deletemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public int getObjecttype() {
        return objecttype;
    }

    public void setObjecttype(int objecttype) {
        this.objecttype = objecttype;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public int getSortcode() {
        return sortcode;
    }

    public void setSortcode(int sortcode) {
        this.sortcode = sortcode;
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

    public int getDeletemark() {
        return deletemark;
    }

    public void setDeletemark(int deletemark) {
        this.deletemark = deletemark;
    }
}