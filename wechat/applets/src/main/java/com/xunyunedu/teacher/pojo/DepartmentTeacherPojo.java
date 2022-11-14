package com.xunyunedu.teacher.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 教师部门
 *
 * @author: yhc
 * @Date: 2020/12/7 22:35
 * @Description:
 */
public class DepartmentTeacherPojo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所在学校
     */
    private Integer schoolId;

    /**
     * 所在部门
     */
    private Integer departmentId;

    /**
     * 部门名称（冗余）
     */
    private String departmentName;

    /**
     * 对应教师
     */
    private Integer teacherId;

    /**
     * 教师名称（冗余）
     */
    private String teacherName;

    /**
     * 部门教师排序号
     */
    private Integer orderNumber;

    /**
     *
     */
    private Boolean isDeleted;

    /**
     *
     */
    private Date createDate;

    /**
     *
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
}