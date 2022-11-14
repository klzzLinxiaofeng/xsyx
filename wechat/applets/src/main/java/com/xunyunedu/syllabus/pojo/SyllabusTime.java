package com.xunyunedu.syllabus.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 学校作息时间表(SyllabusTime)实体类
 *
 * @author sjz
 * @since 2021-04-07 11:13:23
 */
public class SyllabusTime implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 相关学校pj_school.id
     */
    private Integer schoolId;

    private Integer gradeId;
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
     * 每周哪几天上班的安排次数，用星期代码表示，中间逗号分隔
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
    /**
     * 记录创建时间
     */
    private Date createDate;

    private Date modifyDate;


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

}
