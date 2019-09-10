package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
public class Account implements Serializable {

    @ApiModelProperty(value = "主键")
    private int id;//account.id (用户id)

    private String account;
    private String password;
    private String registerTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }
}