package com.xunyunedu.workAttendance.model;

import com.xunyunedu.workAttendance.model.PjTeamStudentKey;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * pj_team_student
 * @author 
 */
@Data
public class PjTeamStudent extends PjTeamStudentKey implements Serializable {
    /**
     * 所在班级ID
     */
    private Integer teamId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 在班中的学号（顺序编号）
     */
    private Integer number;

    /**
     * 加入时间
     */
    private Date joinDate;

    /**
     * 结束时间
     */
    private Date finishDate;

    /**
     * 本记录创建时间
     */
    private Date recordDate;

    /**
     * 职务
     */
    private String position;

    private Date createDate;

    private Date modifyDate;

    /**
     * 删除标记
     */
    private Boolean isDelete;

    /**
     * 迁入/迁出状态
     */
    private Boolean inState;

    private static final long serialVersionUID = 1L;
}