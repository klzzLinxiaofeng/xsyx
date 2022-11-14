package com.xunyunedu.medal.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: education
 * @description: 颁发奖章类
 * @author: cmb
 * @create: 2020-10-13 15:37
 **/
public class MvAwardsMedal  implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * id
   */
  private long id;
  /**
   * 学校id
   */
  private long schoolId;
  /**
   * 学期
   */
  private String year;
  /**
   * 奖章id
   */
  @NotNull(message = "奖章id不能为空")
  private Integer medalId;
  /**
   * 奖章级别
   */
  @NotBlank(message = "奖章级别不能为空")
  private String grade;
  /**
   * 学号
   */
  private String studentNumber;
  /**
   * 班级
   */
  @NotBlank(message = "班级不能为空")
  private String classes;
  /**
   *学生姓名
   */
  @NotBlank(message = "学生姓名不能为空")
  private String awardsStudent;
  /**
   * 教师名
   */
  private String teacherName;
  /**
   * 创建时间
   */
  private Date createDate;
  /**
   * 修改时间
   */
  private Date modifyDate;
  /**
   * 学生id
   */
private Integer StudentId;
/**
 * 学年year
 */
private String schoolTerm;
/**
 * 奖章名称
 */
private  String MedelName;
/**
 * 奖章详情
 */
private String description;
/**
 * 奖章背景
 */
private String background;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getMedelName() {
    return MedelName;
  }

  public void setMedelName(String medelName) {
    MedelName = medelName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSchoolTerm() {
    return schoolTerm;
  }

  public void setSchoolTerm(String schoolTerm) {
    this.schoolTerm = schoolTerm;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSchoolId() {
    return schoolId;
  }

  public void setSchoolId(long schoolId) {
    this.schoolId = schoolId;
  }

  public Integer getStudentId() {
    return StudentId;
  }

  public void setStudentId(Integer studentId) {
    StudentId = studentId;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }


  public Integer getMedalId() {
    return medalId;
  }

  public void setMedalId(Integer medalId) {
    this.medalId = medalId;
  }


  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }


  public String getStudentNumber() {
    return studentNumber;
  }

  public void setStudentNumber(String studentNumber) {
    this.studentNumber = studentNumber;
  }


  public String getClasses() {
    return classes;
  }

  public void setClasses(String classes) {
    this.classes = classes;
  }


  public String getAwardsStudent() {
    return awardsStudent;
  }

  public void setAwardsStudent(String awardsStudent) {
    this.awardsStudent = awardsStudent;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(java.sql.Timestamp createDate) {
    this.createDate = createDate;
  }


  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(java.sql.Timestamp modifyDate) {
    this.modifyDate = modifyDate;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

    @Override
    public String toString() {
        return "MvAwardsMedal{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", year='" + year + '\'' +
                ", medalId=" + medalId +
                ", grade='" + grade + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", classes='" + classes + '\'' +
                ", awardsStudent='" + awardsStudent + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", StudentId=" + StudentId +
                ", schoolTerm='" + schoolTerm + '\'' +
                ", MedelName='" + MedelName + '\'' +
                ", description='" + description + '\'' +
                ", background=" + background +
                '}';
    }

    public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }


}
