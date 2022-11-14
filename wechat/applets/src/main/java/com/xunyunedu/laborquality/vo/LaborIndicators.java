package com.xunyunedu.laborquality.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:26
 * @Version 1.0
 * 劳动素养指标
 */
@Data
public class LaborIndicators {
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
    private Date createTime;
    /*
     * 修改时间
     */
    private Date  modiyTime;
    /*
     *
     */
    private Integer  isDelete;
    private String  schoolYear;
    private String  schoolTrem;
}
