package com.wt.overflow.bean;

import java.util.Date;

public class SysUserLogOn {
    private String id;

    private String userid;

    private String userpassword;

    private String usersecretkey;

    private Date allowstarttime;

    private Date allowendtime;

    private Date lockstartdate;

    private Date lockenddate;

    private Date firstvisittime;

    private Date previousvisittime;

    private Date lastvisittime;

    private Date changepassworddate;

    private Integer multiuserlogin;

    private Integer logoncount;

    private Integer useronline;

    private String question;

    private String answerquestion;

    private Integer checkipaddress;

    private String language;

    private String theme;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getUsersecretkey() {
        return usersecretkey;
    }

    public void setUsersecretkey(String usersecretkey) {
        this.usersecretkey = usersecretkey;
    }

    public Date getAllowstarttime() {
        return allowstarttime;
    }

    public void setAllowstarttime(Date allowstarttime) {
        this.allowstarttime = allowstarttime;
    }

    public Date getAllowendtime() {
        return allowendtime;
    }

    public void setAllowendtime(Date allowendtime) {
        this.allowendtime = allowendtime;
    }

    public Date getLockstartdate() {
        return lockstartdate;
    }

    public void setLockstartdate(Date lockstartdate) {
        this.lockstartdate = lockstartdate;
    }

    public Date getLockenddate() {
        return lockenddate;
    }

    public void setLockenddate(Date lockenddate) {
        this.lockenddate = lockenddate;
    }

    public Date getFirstvisittime() {
        return firstvisittime;
    }

    public void setFirstvisittime(Date firstvisittime) {
        this.firstvisittime = firstvisittime;
    }

    public Date getPreviousvisittime() {
        return previousvisittime;
    }

    public void setPreviousvisittime(Date previousvisittime) {
        this.previousvisittime = previousvisittime;
    }

    public Date getLastvisittime() {
        return lastvisittime;
    }

    public void setLastvisittime(Date lastvisittime) {
        this.lastvisittime = lastvisittime;
    }

    public Date getChangepassworddate() {
        return changepassworddate;
    }

    public void setChangepassworddate(Date changepassworddate) {
        this.changepassworddate = changepassworddate;
    }

    public Integer getMultiuserlogin() {
        return multiuserlogin;
    }

    public void setMultiuserlogin(Integer multiuserlogin) {
        this.multiuserlogin = multiuserlogin;
    }

    public Integer getLogoncount() {
        return logoncount;
    }

    public void setLogoncount(Integer logoncount) {
        this.logoncount = logoncount;
    }

    public Integer getUseronline() {
        return useronline;
    }

    public void setUseronline(Integer useronline) {
        this.useronline = useronline;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerquestion() {
        return answerquestion;
    }

    public void setAnswerquestion(String answerquestion) {
        this.answerquestion = answerquestion;
    }

    public Integer getCheckipaddress() {
        return checkipaddress;
    }

    public void setCheckipaddress(Integer checkipaddress) {
        this.checkipaddress = checkipaddress;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
