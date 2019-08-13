package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "Cd")
public class Cd implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private int id;//account.id (用户id)


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}