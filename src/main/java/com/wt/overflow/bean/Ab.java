package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class Ab implements Serializable {


    private static final long serialVersionUID = 5231134212346077681L;

    @ApiModelProperty(value = "主键")
    private int id;

    private String names;

    private String age;


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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}