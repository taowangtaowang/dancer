package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Account")
public class Account implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private String id;//account.id (用户id)

    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "register_time")
    private String register_time;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegister_time() {
        return register_time;
    }

    public void setRegister_time(String register_time) {
        this.register_time = register_time;
    }
}