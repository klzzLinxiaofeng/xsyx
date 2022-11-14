package com.xunyunedu.pay.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 食堂补卡
 *
 * @author: yhc
 * @Date: 2020/12/3 17:36
 * @Description:
 */
public class CanteenCardPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Integer id;

    /**
     * 学生或者教师id
     */
    private Integer userId;

    /**
     * 对应的学校id
     */
    private Integer schoolId;

    /**
     * 补卡理由
     */
    private String mesg;

    /**
     *
     */
    private Integer isDeleted;

    /**
     * 是否发卡0：否1：是
     */
    private Integer isSend;

    /**
     * 0学生 1教师
     */
    private Integer userType;


    /**
     * 丢失卡号
     */
    private String oldIcNumber;

    /**
     * 补办新卡号
     */
    private String newIcNumber;

    /**
     * 提交时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 修改日期
     */
    private Date modifyDate;

    /**
     * 姓名
     */
    private String name;

    /**
     * 部门或者班级名称
     */
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOldIcNumber() {
        return oldIcNumber;
    }

    public void setOldIcNumber(String oldIcNumber) {
        this.oldIcNumber = oldIcNumber;
    }

    public String getNewIcNumber() {
        return newIcNumber;
    }

    public void setNewIcNumber(String newIcNumber) {
        this.newIcNumber = newIcNumber;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}

