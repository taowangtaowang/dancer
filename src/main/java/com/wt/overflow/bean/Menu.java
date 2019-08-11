package com.wt.overflow.bean;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Table(name = "Menu")
public class Menu implements Serializable {
    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "主键")
    private String id;//

    @Column(name = "parent_id")
    private String parentId; //菜单上级id
    @Column(name = "name")
    private String name;//菜单名字
    @Column(name = "url")
    private String url;//菜单链接地址
    @Column(name = "orderby")
    private String orderby;//菜单排序.


    @Transient
    private String icon;//菜单图片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}