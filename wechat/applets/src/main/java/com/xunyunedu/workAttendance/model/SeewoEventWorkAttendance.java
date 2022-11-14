package com.xunyunedu.workAttendance.model;

import java.io.Serializable;

/**
 * @description: 事件考勤
 * @author: cmb
 * @create: 2020-11-05 19:30
 **/
public class SeewoEventWorkAttendance implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * appId true
     */
    private String appId;
    /**
     * 学校uid true
     */
    private String schoolUid;
    /**
     * 考勤日期 true
     */
    private String attendDate;
    /**
     * 考勤事件ID
     */
    private String eventId;
    /**
     * 班级uid
     */
    private String classUid;
    /**
     * 年级序号：
     1-12 小学1年级~高三年级，30 幼儿园。 99 其他自定义
     与classUid二选一，如果传了classUid，则grade和clazz不需要传，否则grade和clazz必传
     如果都传了，以classUid为准
     */
    private Integer grade;
    /**
     * 班级序号，与classUid二选一
     如果传了classUid，则grade和clazz不需要传，否则grade和clazz必传
     如果都传了，以classUid为准
     */
    private Integer clazz;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSchoolUid() {
        return this.schoolUid;
    }

    public void setSchoolUid(String schoolUid) {
        this.schoolUid = schoolUid;
    }

    public String getAttendDate() {
        return this.attendDate;
    }

    public void setAttendDate(String attendDate) {
        this.attendDate = attendDate;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getClassUid() {
        return this.classUid;
    }

    public void setClassUid(String classUid) {
        this.classUid = classUid;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getClazz() {
        return this.clazz;
    }

    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }
}
