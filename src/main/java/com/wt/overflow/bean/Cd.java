package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Cd implements Serializable {

    @ApiModelProperty(value = "主键")
    private int id;//account.id (用户id)

    private String names;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }
}