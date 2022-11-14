package com.xunyunedu.culturalideal.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:52
 * @Version 1.0
 */
@Data
public class IdealBehavior {
    /*
     * id
     */
    private Integer id;
    /*
     * 学生id
     */
    private Integer  studentId;
    /*
     * 学生name
     */
    private String  studentName;

    /*
     * 学生班级
     */
    private String  teamName;
    /*
     * 指标id
     */
    private Integer idealIndicatorsId;
    /*
     * 指标名称
     */
    private String idealIndicatorsName;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date  modiyTime;
    /*
     *
     */
    private Integer  isDelete;
    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String schoolTrem;
    /*
     * 分数
     */
    private Integer fenshu;
    /*
     * 评语
     */
    private String pingyu;
    /*
     * 点评教师
     */
    private String teacherName;
    /*
    * 教师id
    */
    private  Integer teacherId;
}
