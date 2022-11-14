package com.xunyunedu.repair.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 评价信息
 * @author chenjiaxin
 */
@Data
public class ApplyrepairCommentParam implements Serializable {
    /**
     * 维修单id
     */
    private Integer repairId;
    /**
     * 评价等级
     */
    private Integer appraise;
    /**
     * 评价内容
     */
    private String remark;



}
