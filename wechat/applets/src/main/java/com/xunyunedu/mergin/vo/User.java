package com.xunyunedu.mergin.vo;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import framework.generic.dao.Model;
import java.util.Date;

public class User implements Model<Integer> {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer personId;
    private String userName;
    private String password;
    private Date validDate;
    private String state;
    private Boolean isDeleted;
    private Date createDate;
    private Date modifyDate;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getValidDate() {
        return this.validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPersonId() {
        return this.personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String toString() {
        return "User [id=" + this.id + ", userName=" + this.userName + ", password=" + this.password + ", validDate=" + this.validDate + ", state=" + this.state + ", isDeleted=" + this.isDeleted + "]";
    }
}

