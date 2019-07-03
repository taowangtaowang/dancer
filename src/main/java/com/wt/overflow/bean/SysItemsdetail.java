package com.wt.overflow.bean;

import java.util.Date;

public class SysItemsdetail {
    private String fId;

    private String fItemid;

    private String fParentid;

    private String fItemcode;

    private String fItemname;

    private String fSimplespelling;

    private Boolean fIsdefault;

    private Integer fLayers;

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

    public String getfItemid() {
        return fItemid;
    }

    public void setfItemid(String fItemid) {
        this.fItemid = fItemid == null ? null : fItemid.trim();
    }

    public String getfParentid() {
        return fParentid;
    }

    public void setfParentid(String fParentid) {
        this.fParentid = fParentid == null ? null : fParentid.trim();
    }

    public String getfItemcode() {
        return fItemcode;
    }

    public void setfItemcode(String fItemcode) {
        this.fItemcode = fItemcode == null ? null : fItemcode.trim();
    }

    public String getfItemname() {
        return fItemname;
    }

    public void setfItemname(String fItemname) {
        this.fItemname = fItemname == null ? null : fItemname.trim();
    }

    public String getfSimplespelling() {
        return fSimplespelling;
    }

    public void setfSimplespelling(String fSimplespelling) {
        this.fSimplespelling = fSimplespelling == null ? null : fSimplespelling.trim();
    }

    public Boolean getfIsdefault() {
        return fIsdefault;
    }

    public void setfIsdefault(Boolean fIsdefault) {
        this.fIsdefault = fIsdefault;
    }

    public Integer getfLayers() {
        return fLayers;
    }

    public void setfLayers(Integer fLayers) {
        this.fLayers = fLayers;
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
}