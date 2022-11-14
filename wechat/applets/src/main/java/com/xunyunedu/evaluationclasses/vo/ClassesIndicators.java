package com.xunyunedu.evaluationclasses.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:26
 * @Version 1.0
 */
@Data
public class ClassesIndicators {
    /*
    * id
    */
    private Integer id;
    /*
     * 指标名称
     */
    private String  name;
    /*
     * 指标名称
     */
    private Integer score;
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
    private String  schoolYear;
    private String  schoolTrem;
}
