package com.xunyunedu.team.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级表
 *
 * @author: yhc
 * @Date: 2020/12/18 15:48
 * @Description:
 */
public class TeamPojo implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 所属学校
     */
    private Integer schoolId;

    /**
     * 所属年级
     */
    private Integer gradeId;

    /**
     * 标准班级名称（学段+标准年级名+班号）
     */
    private String fullName;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班号：在同一年级中的顺序编号
     */
    private Integer teamNumber;

    /**
     * 编号，全表唯一，由年级代码和班级顺序号组成，分隔符为减号
     * =grade.code+teamNumber
     */
    private String code;

    /**
     * 校内编号，学校唯一
     */
    private String code2;

    /**
     * 班级类别
     */
    private String teamType;

    /**
     * 班级属性
     */
    private String teamProperty;

    /**
     * 学年的第一部分（冗余）
     */
    private String schoolYear;

    /**
     * 班级成员数
     */
    private Short memberCount;

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
    private Boolean isDelete;

    /**
     *
     */
    private Date createDate;

    /**
     *
     */
    private Date modifyDate;

    /**
     * 本班中学生人数
     */
    private String studentCount;

    /**
     * 本班中教师人数
     */
    private String teacherCount;

    /**
     * 班级UUID
     */
    private String uuid;

    /**
     * 班级变更状态  null=未变更 1=已升级 2=已迁出 3=已毕业
     */
    private Integer alteredStatus;

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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamProperty() {
        return teamProperty;
    }

    public void setTeamProperty(String teamProperty) {
        this.teamProperty = teamProperty;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Short getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Short memberCount) {
        this.memberCount = memberCount;
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

    public String getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(String studentCount) {
        this.studentCount = studentCount;
    }

    public String getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(String teacherCount) {
        this.teacherCount = teacherCount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getAlteredStatus() {
        return alteredStatus;
    }

    public void setAlteredStatus(Integer alteredStatus) {
        this.alteredStatus = alteredStatus;
    }
}

