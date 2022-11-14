package com.xunyunedu.login.pojo;

import java.io.Serializable;
import java.util.Date;


/**
 * pj_parent
 * @author 
 */

public class PjParent implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 家长用户帐号ID yh_user.id
     */
    private Integer userId;

    /**
     * 用户姓名(冗余) yh_user.username
     */
    private String userName;

    /**
     * 家长档案记录 pj_person.id
     */
    private Integer personId;

    /**
     * 家长姓名
     */
    private String name;

    /**
     * 家长手机号
     */
    private String mobile;

    /**
     * 家长邮箱
     */
    private String email;

    /**
     * 是否删除，0为不删除，1为删除
     */
    private Boolean isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "PjParent{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", personId=" + personId +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", isDelete=" + isDelete +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}