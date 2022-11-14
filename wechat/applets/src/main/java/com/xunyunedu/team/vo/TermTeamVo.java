package com.xunyunedu.team.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 学期班级教师信息
 *
 * @author: yhc
 * @Date: 2020/12/16 12:04
 * @Description:
 */
public class TermTeamVo implements Serializable {
    /**
     * 班级id
     */
    private Integer teamId;
    /**
     * 年级
     */
    private Integer gradeId;
    /**
     * 教师
     */
    private Integer teacherId;
    /**
     * 学期id pj_school_term_current
     */
    private Integer termId;

    private String code;

    private Integer schoolId;


    private static final long serialVersionUID = 1L;

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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }
}

