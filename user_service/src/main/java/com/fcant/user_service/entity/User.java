package com.fcant.user_service.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-08-14 22:38:36
 */
public class User implements Serializable {
    private static final long serialVersionUID = 919060133294753810L;
    /**
    * 用户ID
    */
    private Integer id;
    /**
    * 实例ID
    */
    private String userId;
    /**
    * 年龄
    */
    private Integer age;
    /**
    * 名称
    */
    private String name;
    /**
    * 登录时间
    */
    private Date loginTime;
    /**
    * 地址
    */
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}