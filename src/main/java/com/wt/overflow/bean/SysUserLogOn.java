package com.wt.overflow.bean;

import java.util.Date;

public class SysUserLogOn {
    private String fId;

    private String fUserid;

    private String fUserpassword;

    private String fUsersecretkey;

    private Date fAllowstarttime;

    private Date fAllowendtime;

    private Date fLockstartdate;

    private Date fLockenddate;

    private Date fFirstvisittime;

    private Date fPreviousvisittime;

    private Date fLastvisittime;

    private Date fChangepassworddate;

    private Boolean fMultiuserlogin;

    private Integer fLogoncount;

    private Boolean fUseronline;

    private String fQuestion;

    private String fAnswerquestion;

    private Boolean fCheckipaddress;

    private String fLanguage;

    private String fTheme;

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId == null ? null : fId.trim();
    }

    public String getfUserid() {
        return fUserid;
    }

    public void setfUserid(String fUserid) {
        this.fUserid = fUserid == null ? null : fUserid.trim();
    }

    public String getfUserpassword() {
        return fUserpassword;
    }

    public void setfUserpassword(String fUserpassword) {
        this.fUserpassword = fUserpassword == null ? null : fUserpassword.trim();
    }

    public String getfUsersecretkey() {
        return fUsersecretkey;
    }

    public void setfUsersecretkey(String fUsersecretkey) {
        this.fUsersecretkey = fUsersecretkey == null ? null : fUsersecretkey.trim();
    }

    public Date getfAllowstarttime() {
        return fAllowstarttime;
    }

    public void setfAllowstarttime(Date fAllowstarttime) {
        this.fAllowstarttime = fAllowstarttime;
    }

    public Date getfAllowendtime() {
        return fAllowendtime;
    }

    public void setfAllowendtime(Date fAllowendtime) {
        this.fAllowendtime = fAllowendtime;
    }

    public Date getfLockstartdate() {
        return fLockstartdate;
    }

    public void setfLockstartdate(Date fLockstartdate) {
        this.fLockstartdate = fLockstartdate;
    }

    public Date getfLockenddate() {
        return fLockenddate;
    }

    public void setfLockenddate(Date fLockenddate) {
        this.fLockenddate = fLockenddate;
    }

    public Date getfFirstvisittime() {
        return fFirstvisittime;
    }

    public void setfFirstvisittime(Date fFirstvisittime) {
        this.fFirstvisittime = fFirstvisittime;
    }

    public Date getfPreviousvisittime() {
        return fPreviousvisittime;
    }

    public void setfPreviousvisittime(Date fPreviousvisittime) {
        this.fPreviousvisittime = fPreviousvisittime;
    }

    public Date getfLastvisittime() {
        return fLastvisittime;
    }

    public void setfLastvisittime(Date fLastvisittime) {
        this.fLastvisittime = fLastvisittime;
    }

    public Date getfChangepassworddate() {
        return fChangepassworddate;
    }

    public void setfChangepassworddate(Date fChangepassworddate) {
        this.fChangepassworddate = fChangepassworddate;
    }

    public Boolean getfMultiuserlogin() {
        return fMultiuserlogin;
    }

    public void setfMultiuserlogin(Boolean fMultiuserlogin) {
        this.fMultiuserlogin = fMultiuserlogin;
    }

    public Integer getfLogoncount() {
        return fLogoncount;
    }

    public void setfLogoncount(Integer fLogoncount) {
        this.fLogoncount = fLogoncount;
    }

    public Boolean getfUseronline() {
        return fUseronline;
    }

    public void setfUseronline(Boolean fUseronline) {
        this.fUseronline = fUseronline;
    }

    public String getfQuestion() {
        return fQuestion;
    }

    public void setfQuestion(String fQuestion) {
        this.fQuestion = fQuestion == null ? null : fQuestion.trim();
    }

    public String getfAnswerquestion() {
        return fAnswerquestion;
    }

    public void setfAnswerquestion(String fAnswerquestion) {
        this.fAnswerquestion = fAnswerquestion == null ? null : fAnswerquestion.trim();
    }

    public Boolean getfCheckipaddress() {
        return fCheckipaddress;
    }

    public void setfCheckipaddress(Boolean fCheckipaddress) {
        this.fCheckipaddress = fCheckipaddress;
    }

    public String getfLanguage() {
        return fLanguage;
    }

    public void setfLanguage(String fLanguage) {
        this.fLanguage = fLanguage == null ? null : fLanguage.trim();
    }

    public String getfTheme() {
        return fTheme;
    }

    public void setfTheme(String fTheme) {
        this.fTheme = fTheme == null ? null : fTheme.trim();
    }
}