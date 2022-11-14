package com.xunyunedu.workAttendance.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pj_team_teacher
 * @author  cmb
 */
@Data
public class PjTeamTeacher implements Serializable {
    private Integer id;

    /**
     * 所在年级
     */
    private Integer gradeId;

    /**
     * 所在班级
     */
    private Integer teamId;

    /**
     * 教师ID
     */
    private Integer teacherId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 教师在在班中的任职类型
     */
    private Integer type;

    /**
     * 担任科目
     */
    private String subjectCode;

    /**
     * 科目名称
     */
    private String subjectName;

    /**
     * 担任班级教师所处的学年，与年级的学年相
     */
    private String schoolYear;

    /**
     * 加入时间
     */
    private Date joinDate;

    /**
     * 结束时间
     */
    private Date finishDate;

    /**
     * 职务，班主任值为【班主任】，任课教师值为【subject教师】
     */
    private String position;

    private Date createDate;

    /**
     * 删除标识
     */
    private Boolean isDelete;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;
}