package com.wt.overflow.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "account")
    private String account;
    @Column(name = "realName")
    private String realname;
    @Column(name = "nickName")
    private String nickname;
    @Column(name = "headIcon")
    private String headicon;
    @Column(name = "gender")
    private int gender;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "mobilePhone")
    private String mobilephone;
    @Column(name = "email")
    private String email;
    @Column(name = "weChat")
    private String wechat;
    @Column(name = "managerId")
    private String managerid;
    @Column(name = "securityLevel")
    private int securitylevel;
    @Column(name = "signature")
    private String signature;
    @Column(name = "organizeId")
    private String organizeid;
    @Column(name = "departmentId")
    private String departmentid;
    @Column(name = "roleId")
    private String roleid;
    @Column(name = "dutyId")
    private String dutyid;
    @Column(name = "jobNumber")
    private String jobnumber;
    @Column(name = "isAdministrator")
    private int isadministrator;
    @Column(name = "sortCode")
    private int sortcode;
    @Column(name = "deleteMark")
    private int deletemark;
    @Column(name = "enabledMark")
    private int enabledmark;
    @Column(name = "description")
    private String description;
    @Column(name = "creatorTime")
    private Date creatortime;
    @Column(name = "creatorUserId")
    private String creatoruserid;
    @Column(name = "lastModifyTime")
    private Date lastmodifytime;
    @Column(name = "lastModifyUserId")
    private String lastmodifyuserid;
    @Column(name = "deleteTime")
    private Date deletetime;
    @Column(name = "deleteUserId")
    private String deleteuserid;
    @Column(name = "userPassword")
    private String userPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
    }

    public int getSecuritylevel() {
        return securitylevel;
    }

    public void setSecuritylevel(int securitylevel) {
        this.securitylevel = securitylevel;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOrganizeid() {
        return organizeid;
    }

    public void setOrganizeid(String organizeid) {
        this.organizeid = organizeid;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getDutyid() {
        return dutyid;
    }

    public void setDutyid(String dutyid) {
        this.dutyid = dutyid;
    }

    public String getJobnumber() {
        return jobnumber;
    }

    public void setJobnumber(String jobnumber) {
        this.jobnumber = jobnumber;
    }

    public int getIsadministrator() {
        return isadministrator;
    }

    public void setIsadministrator(int isadministrator) {
        this.isadministrator = isadministrator;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}