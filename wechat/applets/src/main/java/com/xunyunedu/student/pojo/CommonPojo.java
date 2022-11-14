package com.xunyunedu.student.pojo;

import java.io.Serializable;

/**
 * 请求参数
 *
 * @author: yhc
 * @Date: 2020/12/12 10:45
 * @Description:
 */
public class CommonPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 年级id
     */
    private Integer gradeId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 微课id
     */
    private Integer microId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 0学生 1教师
     */
    private Integer userType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getMicroId() {
        return microId;
    }

    public void setMicroId(Integer microId) {
        this.microId = microId;
    }
}
