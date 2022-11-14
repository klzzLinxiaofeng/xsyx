package com.xunyunedu.activitieshome.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:52
 * @Version 1.0
 */
@Data
public class HomeBehavior {
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
    private Integer homeIndicatorsId;
    /*
     * 指标名称
     */
    private String homeIndicatorsName;
    /*
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /*
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
}
