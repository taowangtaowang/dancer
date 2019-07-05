package com.wt.overflow.bean;

import java.util.Date;

public class SysRole {
    private String id;

    private String organizeid;

    private int category;

    private String encode;

    private String fullname;

    private String type;

    private String responsibility;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganizeid() {
        return organizeid;
    }

    public void setOrganizeid(String organizeid) {
        this.organizeid = organizeid;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
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
}