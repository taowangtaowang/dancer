package com.wt.overflow.bean;

import java.util.Date;

public class SysUser {
	private String fId;

	private String fAccount;

	private String fRealname;

	private String fNickname;

	private String fHeadicon;

	private Boolean fGender;

	private Date fBirthday;

	private String fMobilephone;

	private String fEmail;

	private String fWechat;

	private String fManagerid;

	private Integer fSecuritylevel;

	private String fSignature;

	private String fOrganizeid;

	private String fDepartmentid;

	private String fRoleid;

	private String fDutyid;

	private Boolean fIsadministrator;

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

	private String fUserPassword;//用户附表信息
	
	private String fQuestion;//找回密码问题
	
	private String fAnswerQuestion;//找回密码答案
	
	private String fChangePasswordDate;//上次修改密码时间
	
	private String fOrganizename;
	
	private String fJobNumber;
	
	public String getfJobNumber() {
		return fJobNumber;
	}

	public void setfJobNumber(String fJobNumber) {
		this.fJobNumber = fJobNumber;
	}

	public String getfOrganizename() {
		return fOrganizename;
	}

	public void setfOrganizename(String fOrganizename) {
		this.fOrganizename = fOrganizename;
	}

	public String getfId() {
		return fId;
	}

	public void setfId(String fId) {
		this.fId = fId == null ? null : fId.trim();
	}

	public String getfAccount() {
		return fAccount;
	}

	public void setfAccount(String fAccount) {
		this.fAccount = fAccount == null ? null : fAccount.trim();
	}

	public String getfRealname() {
		return fRealname;
	}

	public void setfRealname(String fRealname) {
		this.fRealname = fRealname == null ? null : fRealname.trim();
	}

	public String getfNickname() {
		return fNickname;
	}

	public void setfNickname(String fNickname) {
		this.fNickname = fNickname == null ? null : fNickname.trim();
	}

	public String getfHeadicon() {
		return fHeadicon;
	}

	public void setfHeadicon(String fHeadicon) {
		this.fHeadicon = fHeadicon == null ? null : fHeadicon.trim();
	}

	public Boolean getfGender() {
		return fGender;
	}

	public void setfGender(Boolean fGender) {
		this.fGender = fGender;
	}

	public Date getfBirthday() {
		return fBirthday;
	}

	public void setfBirthday(Date fBirthday) {
		this.fBirthday = fBirthday;
	}

	public String getfMobilephone() {
		return fMobilephone;
	}

	public void setfMobilephone(String fMobilephone) {
		this.fMobilephone = fMobilephone == null ? null : fMobilephone.trim();
	}

	public String getfEmail() {
		return fEmail;
	}

	public void setfEmail(String fEmail) {
		this.fEmail = fEmail == null ? null : fEmail.trim();
	}

	public String getfWechat() {
		return fWechat;
	}

	public void setfWechat(String fWechat) {
		this.fWechat = fWechat == null ? null : fWechat.trim();
	}

	public String getfManagerid() {
		return fManagerid;
	}

	public void setfManagerid(String fManagerid) {
		this.fManagerid = fManagerid == null ? null : fManagerid.trim();
	}

	public Integer getfSecuritylevel() {
		return fSecuritylevel;
	}

	public void setfSecuritylevel(Integer fSecuritylevel) {
		this.fSecuritylevel = fSecuritylevel;
	}

	public String getfSignature() {
		return fSignature;
	}

	public void setfSignature(String fSignature) {
		this.fSignature = fSignature == null ? null : fSignature.trim();
	}

	public String getfOrganizeid() {
		return fOrganizeid;
	}

	public void setfOrganizeid(String fOrganizeid) {
		this.fOrganizeid = fOrganizeid == null ? null : fOrganizeid.trim();
	}

	public String getfDepartmentid() {
		return fDepartmentid;
	}

	public void setfDepartmentid(String fDepartmentid) {
		this.fDepartmentid = fDepartmentid == null ? null : fDepartmentid.trim();
	}

	public String getfRoleid() {
		return fRoleid;
	}

	public void setfRoleid(String fRoleid) {
		this.fRoleid = fRoleid == null ? null : fRoleid.trim();
	}

	public String getfDutyid() {
		return fDutyid;
	}

	public void setfDutyid(String fDutyid) {
		this.fDutyid = fDutyid == null ? null : fDutyid.trim();
	}

	public Boolean getfIsadministrator() {
		return fIsadministrator;
	}

	public void setfIsadministrator(Boolean fIsadministrator) {
		this.fIsadministrator = fIsadministrator;
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

	public String getfUserPassword() {
		return fUserPassword;
	}

	public void setfUserPassword(String fUserPassword) {
		this.fUserPassword = fUserPassword;
	}

	public String getfQuestion() {
		return fQuestion;
	}

	public void setfQuestion(String fQuestion) {
		this.fQuestion = fQuestion;
	}

	public String getfAnswerQuestion() {
		return fAnswerQuestion;
	}

	public void setfAnswerQuestion(String fAnswerQuestion) {
		this.fAnswerQuestion = fAnswerQuestion;
	}

	public String getfChangePasswordDate() {
		return fChangePasswordDate;
	}

	public void setfChangePasswordDate(String fChangePasswordDate) {
		this.fChangePasswordDate = fChangePasswordDate;
	}
	
}