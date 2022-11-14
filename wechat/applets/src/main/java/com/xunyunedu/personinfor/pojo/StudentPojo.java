package com.xunyunedu.personinfor.pojo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * 学生信息
 *
 * @author: yhc
 * @Date: 2020/9/19 16:53
 * @Description: 学生信息
 */
public class StudentPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生id
     */
    private Integer id;
    /**
     * 学生userId
     */
    private Integer userId;

    /**
     * 学生名称
     */
    @NotBlank(message = "学生姓名不能为空")
    private String name;
    /**
     * 学生年级 1-6
     */
    private Integer grade;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 班级名称 一年级 、二年级...
     */
    @NotBlank(message = "班级不能为空")
    private String teamName;

    /**
     * 班级id
     */
    private Integer teamId;

    /**
     * 年级名称
     */
    private String gradeName;

    /**
     * 年级id
     */
    private Integer gradeId;

    /**
     * 学号
     */

    private String number;
    /**
     * 学年
     */
    private  String year;

    /**
     * 食堂卡号
     */
    private String empCard;

    /**
     * 食堂工号
     */
    private String empCode;



    private List<PublicClassPojo> publicClassList;

    /**
     * 头像id
     */
    private String photoUuid;

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUuid() {
        return photoUuid;
    }

    public void setPhotoUuid(String photoUuid) {
        this.photoUuid = photoUuid;
    }

    public List<PublicClassPojo> getPublicClassList() {
        return publicClassList;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPublicClassList(List<PublicClassPojo> publicClassList) {
        this.publicClassList = publicClassList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StudentPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                ", schoolId=" + schoolId +
                ", schoolName='" + schoolName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", teamId=" + teamId +
                ", gradeName='" + gradeName + '\'' +
                ", gradeId=" + gradeId +
                ", number=" + number +
                ", list=" + publicClassList +
                '}';
    }
}
