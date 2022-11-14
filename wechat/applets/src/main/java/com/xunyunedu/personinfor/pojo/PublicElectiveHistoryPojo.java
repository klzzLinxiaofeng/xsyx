package com.xunyunedu.personinfor.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 选课历史记录
 *
 * @author: yhc
 * @Date: 2020/11/20 17:48
 * @Description:
 */
public class PublicElectiveHistoryPojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 学生id pj_student.id
     */
    private Integer studentId;

    /**
     * 开课管理id pj_public_class
     */
    private Integer publicClassId;

    /**
     * 插入时间
     */
    private Date createdAt;

    /**
     * 对应的学校id
     */
    private Integer schoolId;

    private List<Integer> studentIds;

    public List<Integer> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Integer> studentIds) {
        this.studentIds = studentIds;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPublicClassId() {
        return publicClassId;
    }

    public void setPublicClassId(Integer publicClassId) {
        this.publicClassId = publicClassId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}

