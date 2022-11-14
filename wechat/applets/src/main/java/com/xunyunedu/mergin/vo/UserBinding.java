package com.xunyunedu.mergin.vo;

import framework.generic.dao.Model;

import java.util.Date;

public class UserBinding implements Model<Integer> {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String bindingName;
    private Integer bindingType;
    private Boolean enabled;
    private Boolean isDeleted;
    private Date createDate;
    private Date modifyDate;

    public UserBinding() {
    }

    public UserBinding(Integer id) {
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

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBindingName() {
        return this.bindingName;
    }

    public void setBindingName(String bindingName) {
        this.bindingName = bindingName;
    }

    public Integer getBindingType() {
        return this.bindingType;
    }

    public void setBindingType(Integer bindingType) {
        this.bindingType = bindingType;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        return "UserBinding [id=" + this.id + ", userId=" + this.userId + ", bindingName=" + this.bindingName + ", bindingType=" + this.bindingType + ", enabled=" + this.enabled + ", isDeleted=" + this.isDeleted + "]";
    }
}
