package com.wt.overflow.bean;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    private static final long serialVersionUID = -1664777923450505084L;

    private String id;
    private String nickname;
    private String operateUser;
    private Date createTime;
    private String notes;
    private String functionValue;
    private String result;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(String functionValue) {
        this.functionValue = functionValue;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", operateUser='" + operateUser + '\'' +
                ", createTime=" + createTime +
                ", notes='" + notes + '\'' +
                ", functionValue='" + functionValue + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
