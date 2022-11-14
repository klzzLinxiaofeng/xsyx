package com.xunyunedu.syllabus.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级课程表的子表,记录课程表中每节课的任课老师
 * 本表是每周固定的课程安排表,如果临时调课不在此表描述
 * (PjSyllabusLesson)实体类
 *
 * @author sjz
 * @since 2021-03-29 10:21:09
 */
public class SyllabusLessonPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * pj_syllabus.id
     */
    private Integer syllabusId;


    /**
     * 节次，第几节
     */
    private Integer lesson;
    /**
     * 星期几 XY-JY-XQ 0=星期日 1=星期一
     */
    private String dayOfWeek;
    /**
     * pj_teacher.id
     */
    private Integer teacherId;
    /**
     * pj_subject
     */
    private String subjectCode;

    private String subjectName;

    private Integer roomId;

    private Date createDate;

    private Date modifyDate;

    private Integer isDeleted;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private Date startDate;

    private Date endDate;

    private Integer defaultFlag;

    private Integer adjustFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(Integer syllabusId) {
        this.syllabusId = syllabusId;
    }

    public Integer getLesson() {
        return lesson;
    }

    public void setLesson(Integer lesson) {
        this.lesson = lesson;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public Integer getAdjustFlag() {
        return adjustFlag;
    }

    public void setAdjustFlag(Integer adjustFlag) {
        this.adjustFlag = adjustFlag;
    }


}
