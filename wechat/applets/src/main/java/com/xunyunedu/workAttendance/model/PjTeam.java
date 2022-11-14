package com.xunyunedu.workAttendance.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * pj_team
 * @author 
 */
@Data
public class PjTeam implements Serializable {
    /**
     * 主健ID
     */
    private Integer id;

    /**
     * 所属学校
     */
    private Integer schoolId;

    /**
     * 所属年级
     */
    private Integer gradeId;

    /**
     * 标准班级名称（学段+标准年级名+班号）
     */
    private String fullName;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 班号：在同一年级中的顺序编号
     */
    private Integer teamNumber;

    /**
     * 编号，全表唯一，由年级代码和班级顺序号组成，分隔符为减号
=grade.code+teamNumber
     */
    private String code;

    /**
     * 校内编号，学校唯一
     */
    private String code2;

    /**
     * 班级类别
     */
    private String teamType;

    /**
     * 班级属性
     */
    private String teamProperty;

    /**
     * 学年的第一部分（冗余）
     */
    private String schoolYear;

    /**
     * 班级成员数
     */
    private Short memberCount;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date finishDate;

    /**
     * 标志是否为删除
     */
    private Boolean isDelete;

    private Date createDate;

    private Date modifyDate;

    /**
     * 本班中学生人数
     */
    private String studentCount;

    /**
     * 本班中教师人数
     */
    private String teacherCount;

    /**
     * 班级UUID
     */
    private String uuid;

    /**
     * 班级变更状态  null=未变更 1=已升级 2=已迁出 3=已毕业
     */
    private Integer alteredStatus;

    private static final long serialVersionUID = 1L;
}