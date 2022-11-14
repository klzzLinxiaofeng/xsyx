package com.xunyunedu.development.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/11 9:48
 * @Version 1.0
 */
@Data
public class StudentDevelopment {
    private Integer id;
    /*
    * 指标id
    */
    private Integer developmentId;
    /*
    * 指标名称
    */
    private String developmentName;
    /*
    * 年级Id
    */
    private Integer gradeId;
    /*
     * 年级Name
     */
    private String gradeName;
    /*
     * 班级Id
     */
    private Integer teamId;
    /*
     * 班级Name
     */
    private String teamName;
    /*
    * 评价人id
    */
    private Integer pingjiaId;
    /*
     * 评价人姓名
     */
    private String pingjiaName;

    /*
    * 学生id
    */
    private Integer studentId;
    /*
    * 学生姓名
    */
    private String studentName;
    /*
    * 评价分数
    */
    private Integer score;
    /*
    * 学年
    */
    private String schoolYear;
    /*
    * 学期
    */
    private String schoolTrem;

    private Date createTime;

    private Date modiyTime;

    private Integer isDelete;
}
