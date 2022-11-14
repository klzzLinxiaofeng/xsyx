package com.xunyunedu.login.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author: yhc
 * @Date: 2020/9/19 14:17
 * @Description: 用户实体
 */
public class UserPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;
    /**
     * 账号id
     */
    private Integer personId;
    /**
     * 账号用户名称
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    private Date validDate;
    /**
     *
     */
    private String state;
    /**
     * 是否删除
     */
    private Boolean isDeleted;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 微信用户唯一标识
     */
    private String openId;


    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    @Override
    public String toString() {
        return "User [id=" + this.id + ", userName=" + this.userName + ", password=" + this.password + ", validDate=" + this.validDate + ", state=" + this.state + ", isDeleted=" + this.isDeleted + "]";
    }
}
