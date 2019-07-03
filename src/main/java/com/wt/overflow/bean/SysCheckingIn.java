package com.wt.overflow.bean;

import java.util.Date;

public class SysCheckingIn {
    private String ID;

    private String LOG_NUM;

    private String CUSTOM_NUM;

    private String NAME;

    private Date OUT_TIME;

    private String OUT_STATUS;

    private String UPDATE_STATUS;

    private String EXCEPTION_STATUS;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID == null ? null : ID.trim();
    }

    public String getLOG_NUM() {
        return LOG_NUM;
    }

    public void setLOG_NUM(String LOG_NUM) {
        this.LOG_NUM = LOG_NUM == null ? null : LOG_NUM.trim();
    }

    public String getCUSTOM_NUM() {
        return CUSTOM_NUM;
    }

    public void setCUSTOM_NUM(String CUSTOM_NUM) {
        this.CUSTOM_NUM = CUSTOM_NUM == null ? null : CUSTOM_NUM.trim();
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME == null ? null : NAME.trim();
    }

    public Date getOUT_TIME() {
        return OUT_TIME;
    }

    public void setOUT_TIME(Date OUT_TIME) {
        this.OUT_TIME = OUT_TIME;
    }

    public String getOUT_STATUS() {
        return OUT_STATUS;
    }

    public void setOUT_STATUS(String OUT_STATUS) {
        this.OUT_STATUS = OUT_STATUS == null ? null : OUT_STATUS.trim();
    }

    public String getUPDATE_STATUS() {
        return UPDATE_STATUS;
    }

    public void setUPDATE_STATUS(String UPDATE_STATUS) {
        this.UPDATE_STATUS = UPDATE_STATUS == null ? null : UPDATE_STATUS.trim();
    }

    public String getEXCEPTION_STATUS() {
        return EXCEPTION_STATUS;
    }

    public void setEXCEPTION_STATUS(String EXCEPTION_STATUS) {
        this.EXCEPTION_STATUS = EXCEPTION_STATUS == null ? null : EXCEPTION_STATUS.trim();
    }
}