package com.xunyunedu.syllabus.pojo;


import java.io.Serializable;
import java.util.Date;

/**
 * 课程表以每班每学期为单位,每个班每个学期生成一个课程表
 * 一个课程表只产生一个记录,课程表每节编排内容在班级课程安排明细表(Syllabus)实体类
 *
 * @author sjz
 * @since 2021-04-01 14:04:23
 */
public class SyllabusPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 相关学校pj_school.id
     */
    private Integer schoolId;
    /**
     * 相关班级pj_school_term.id
     */
    private Integer teamId;
    /**
     * pj_school_year.year
     */
    private String schoolYear;
    /**
     * pj_school_term.code
     */
    private String termCode;
    /**
     * 课程表每周安排天数
     */
    private Integer days;
    /**
     * 每周哪几天上课的安排次数，用星期代码表示，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"}, {"lesson":"2", "time":"09:00-09:40"}
     */
    private String daysPlan;
    /**
     * 上午安排节数
     */
    private Integer lessonOfMorning;
    /**
     * 下午安排节数
     */
    private Integer lessonOfAfternoon;
    /**
     * 晚上安排节数
     */
    private Integer lessonOfEvening;

    private String lessonTimes;

    private Date createDate;

    private Date modifyDate;
    /**
     * 0正常  1删除
     */
    private Integer isDeleted;


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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDaysPlan() {
        return daysPlan;
    }

    public void setDaysPlan(String daysPlan) {
        this.daysPlan = daysPlan;
    }

    public Integer getLessonOfMorning() {
        return lessonOfMorning;
    }

    public void setLessonOfMorning(Integer lessonOfMorning) {
        this.lessonOfMorning = lessonOfMorning;
    }

    public Integer getLessonOfAfternoon() {
        return lessonOfAfternoon;
    }

    public void setLessonOfAfternoon(Integer lessonOfAfternoon) {
        this.lessonOfAfternoon = lessonOfAfternoon;
    }

    public Integer getLessonOfEvening() {
        return lessonOfEvening;
    }

    public void setLessonOfEvening(Integer lessonOfEvening) {
        this.lessonOfEvening = lessonOfEvening;
    }

    public String getLessonTimes() {
        return lessonTimes;
    }

    public void setLessonTimes(String lessonTimes) {
        this.lessonTimes = lessonTimes;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

}
