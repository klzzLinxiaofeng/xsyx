package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 家长信息
 *
 * @author: yhc
 * @Date: 2020/12/2 10:25
 * @Description:
 */
public class ParentPojo implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 家长用户帐号ID yh_user.id
     */
    private Integer userId;

    /**
     * 用户帐号 yh_user.username
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
    /**
     * 学生Id表中不存在
     */
    private Integer stuId;

    private static final long serialVersionUID = 1L;

    /**
     * 车牌
     */
    private String licensePlate;

    private List<String> licensePlates;


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public List<String> getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(List<String> licensePlates) {
        this.licensePlates = licensePlates;
    }

    @Override
    public String toString() {
        return "ParentPojo{" +
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
                ", stuId=" + stuId +
                '}';
    }
}

