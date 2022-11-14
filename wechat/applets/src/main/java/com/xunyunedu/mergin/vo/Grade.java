package com.xunyunedu.mergin.vo;

import framework.generic.dao.Model;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Team;

import java.util.List;

public class Grade implements Model<Integer> {

    private static final long serialVersionUID = 1L;
    /**
     * 主健
     */
    private Integer id;

    /**
     * 编号
     */
    private String code;

    /**
     * 所属学校
     */
    private Integer schoolId;
    /**
     * 学段
     */
    private String stageCode;
    /**
     * 标准年级名称
     */
    private String fullName;
    /**
     * 班级名称
     */
    private String name;

    /**
     * 学年的第一部分
     */
    private String schoolYear;
    /**
     * 年级在学校中的顺序，如初二是2
     */
    private Integer gradeNumber;

    /**
     * 通用年级码
     */
    private String uniGradeCode;

    /**
     * 本年级包含的班级数
     */
    private Integer teamCount;

    /**
     * 剩余多少人
     */
    private Integer lastNum;
    /**
     * 1是创建，2是编辑
     */
    private Integer status;

    public Integer getLastNum() {
        return lastNum;
    }

    public void setLastNum(Integer lastNum) {
        this.lastNum = lastNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPerTeamNum() {
        return perTeamNum;
    }

    public void setPerTeamNum(Integer perTeamNum) {
        this.perTeamNum = perTeamNum;
    }

    /**
     * 每个班多少人
     */
    private Integer perTeamNum;

    /**
     * 开始时间
     */
    private java.util.Date beginDate;
    /**
     * 结束时间
     */
    private java.util.Date finishDate;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    /**
     * 是否删除
     */
    private boolean isDelete;


    /**
     * 学年全名
     * @return
     */
    private String yearName;

    /**
     *班级列表
     */
    private List<Team> teamList;

    /**
     * 科目列表
     * @return
     */
    private List<SubjectGrade> gradeSubjectList = null;

    public List<SubjectGrade> getGradeSubjectList() {
        return gradeSubjectList;
    }

    public void setGradeSubjectList(List<SubjectGrade> gradeSubjectList) {
        this.gradeSubjectList = gradeSubjectList;
    }

    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Grade() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取主健
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置主健
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所属学校
     *
     * @return java.lang.Integer
     */
    public Integer getSchoolId() {
        return this.schoolId;
    }

    /**
     * 设置所属学校
     *
     * @param shoolId
     * @type java.lang.Integer
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取学段
     *
     * @return java.lang.String
     */
    public String getStageCode() {
        return this.stageCode;
    }

    /**
     * 设置学段
     *
     * @param stageCode
     * @type java.lang.String
     */
    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    /**
     * 获取班级名称
     *
     * @return java.lang.String
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置班级名称
     *
     * @param name
     * @type java.lang.String
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取学年的第一部分
     *
     * @return java.lang.String
     */
    public String getSchoolYear() {
        return this.schoolYear;
    }

    /**
     * 设置学年的第一部分
     *
     * @param schoolYear
     * @type java.lang.String
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    /**
     * 获取年级在学校中的顺序，如初二是2
     *
     * @return java.lang.Integer
     */
    public Integer getGradeNumber() {
        return this.gradeNumber;
    }

    /**
     * 设置年级在学校中的顺序，如初二是2
     *
     * @param gradeNumber
     * @type java.lang.Integer
     */
    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    /**
     * 获取本年级包含的班级数
     *
     * @return java.lang.Integer
     */
    public Integer getTeamCount() {
        return this.teamCount;
    }

    /**
     * 设置本年级包含的班级数
     *
     * @param teamCount
     * @type java.lang.Integer
     */
    public void setTeamCount(Integer teamCount) {
        this.teamCount = teamCount;
    }

    /**
     * 获取开始时间
     *
     * @return java.util.Date
     */
    public java.util.Date getBeginDate() {
        return this.beginDate;
    }

    /**
     * 设置开始时间
     *
     * @param beginDate
     * @type java.util.Date
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束时间
     *
     * @return java.util.Date
     */
    public java.util.Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * 设置结束时间
     *
     * @param finishDate
     * @type java.util.Date
     */
    public void setFinishDate(java.util.Date finishDate) {
        this.finishDate = finishDate;
    }

    /**
     * 获取createDate
     *
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     *
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取modifyDate
     *
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置modifyDate
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 通用年级码
     *
     * @return
     */
    public String getUniGradeCode() {
        return uniGradeCode;
    }

    public void setUniGradeCode(String uniGradeCode) {
        this.uniGradeCode = uniGradeCode;
    }

    @Override
    public String toString() {
        return "Grade [id=" + id + ", code=" + code + ", schoolId=" + schoolId
                + ", stageCode=" + stageCode + ", fullName=" + fullName
                + ", name=" + name + ", schoolYear=" + schoolYear
                + ", gradeNumber=" + gradeNumber + ", uniGradeCode="
                + uniGradeCode + ", teamCount=" + teamCount + ", beginDate="
                + beginDate + ", finishDate=" + finishDate + ", isDelete="
                + isDelete + "]";
    }

}
