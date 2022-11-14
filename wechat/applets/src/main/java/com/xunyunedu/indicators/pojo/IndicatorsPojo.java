package com.xunyunedu.indicators.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class IndicatorsPojo {
    /*
     * 主键id
     */
    private Integer id;
    /*
     *班级id
     */
    private Integer gradeId;
    /*
     * 班级name
     */
    private String gradeName;
    /*
     * 体测指标名称
     */
    private String name;
    /*
     * 单位
     */
    private String danwei;
    /*
     * 分数
     */
    private String score;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modieTime;
    /*
     * 主键id
     */
    private Integer isDelete;
    /*
     * 学年
     */
    private String schoolYear;

    /*
     *  学校id
     */
    private  Integer schoolId;
}
