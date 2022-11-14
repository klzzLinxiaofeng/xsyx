package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 年级
 *
 * @author: yhc
 * @Date: 2020/12/12 10:24
 * @Description:
 */
public class GradePojo implements Serializable {
    /**
     * 主健
     */
    private Integer id;

    /**
     * 标准年级名称（学段+标准年级名）
     */
    private String fullName;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属学校
     */
    private Integer schoolId;

    /**
     * 年级代码，全表唯一
     * schoolId+schoolYear+uniGradeCode
     */
    private String code;

    /**
     * 学段
     */
    private String stageCode;

    /**
     * 通用年级码
     */
    private String uniGradeCode;

    /**
     * 学年的第一部分
     */
    private String schoolYear;

    /**
     * 年级在学校中的顺序，如初二是2
     */
    private Integer gradeNumber;

    /**
     * 本年级包含的班级数
     */
    private Integer teamCount;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date finishDate;

    /**
     * 标志是否为删除
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getUniGradeCode() {
        return uniGradeCode;
    }

    public void setUniGradeCode(String uniGradeCode) {
        this.uniGradeCode = uniGradeCode;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Integer getTeamCount() {
        return teamCount;
    }

    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
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

