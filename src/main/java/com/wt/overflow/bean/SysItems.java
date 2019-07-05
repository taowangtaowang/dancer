package com.wt.overflow.bean;

import java.util.Date;

public class SysItems {
    private String id;

    private String parentid;

    private String encode;

    private String fullname;

    private int istree;

    private int layers;

    private int sortcode;

    private int deletemark;

    private int enabledmark;

    private Date creatortime;

    private String creatoruserid;

    private Date lastmodifytime;

    private String lastmodifyuserid;

    private Date deletetime;

    private String deleteuserid;

    private String description;

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

    public int getIstree() {
        return istree;
    }

    public void setIstree(int istree) {
        this.istree = istree;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}