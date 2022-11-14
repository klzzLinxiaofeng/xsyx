package com.xunyunedu.team.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级学生相册归档
 *
 * @author: yhc
 * @Date: 2020/12/16 14:02
 * @Description:
 */
public class ClassStudentPhotoPojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 班级纪念册id
     */
    private Integer classYearbookId;

    /**
     * 学生id pj_student.id
     */
    private Integer studentId;

    /**
     * 学期id pj_school_term
     */
    private Integer termId;

    /**
     * 班级id
     */
    private Integer teamId;

    /**
     *
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassYearbookId() {
        return classYearbookId;
    }

    public void setClassYearbookId(Integer classYearbookId) {
        this.classYearbookId = classYearbookId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
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
}

