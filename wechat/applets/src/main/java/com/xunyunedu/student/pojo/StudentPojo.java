package com.xunyunedu.student.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息
 *
 * @author: yhc
 * @Date: 2020/12/2 10:46
 * @Description:
 */
public class StudentPojo implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 所属学校
     */
    private Integer schoolId;

    /**
     * 对应的人
     */
    private Integer personId;

    /**
     * 用户帐号
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 姓名
     */
    private String name;

    /**
     * 必别
     */
    private String sex;

    /**
     * 全国统一学籍号
     */
    private String studentNumber;

    /**
     * 学籍辅助号
     */
    private String studentNumber2;

    /**
     * 入学时间
     */
    private Date enrollDate;

    /**
     * 离校时间
     */
    private Date leaveDate;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡
     * 14=开除。99=其它
     */
    private String studyState;

    /**
     * 学生类别
     */
    private String studentType;

    /**
     * 职务
     */
    private String position;

    /**
     *
     */
    private Integer teamId;

    /**
     * 当前最后所在班级名称
     */
    private String teamName;

    /**
     * 是否住宿
     */
    private String isBoarded;

    /**
     * 删除标志
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 别名
     */
    private String alias;

    /**
     * 食堂充值卡号，用到地方食堂、图书馆、闸机、班牌
     */
    private String empCard;

    /**
     * 对应食堂工号，用到地方食堂、图书馆、闸机、班牌
     */
    private String empCode;

    /**
     * 学生信息是否发送到图书馆，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)
     */
    private Integer isSendLibrary;

    /**
     * 学生信息是否发送到海康闸机，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)
     */
    private Integer isSendHikdoor;
    /**
     * 老师id 实体表内不存在
     */
    private Integer teacherId;

    /**
     * 学生学号
     */
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber2() {
        return studentNumber2;
    }

    public void setStudentNumber2(String studentNumber2) {
        this.studentNumber2 = studentNumber2;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStudyState() {
        return studyState;
    }

    public void setStudyState(String studyState) {
        this.studyState = studyState;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getIsBoarded() {
        return isBoarded;
    }

    public void setIsBoarded(String isBoarded) {
        this.isBoarded = isBoarded;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Integer getIsSendLibrary() {
        return isSendLibrary;
    }

    public void setIsSendLibrary(Integer isSendLibrary) {
        this.isSendLibrary = isSendLibrary;
    }

    public Integer getIsSendHikdoor() {
        return isSendHikdoor;
    }

    public void setIsSendHikdoor(Integer isSendHikdoor) {
        this.isSendHikdoor = isSendHikdoor;
    }
}

