package com.xunyunedu.repair.pojo;


import lombok.Data;

import java.io.Serializable;

/**
 * 报修信息
 * @author chenjiaxin
 */
@Data
public class ApplyrepairInsertParam implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 申请人userId
     */
    private Integer proposerId;
    /**
     * 申请人schoolId
     */
    private Integer schoolId;
    /**
     * 故障标题
     */
    private String title;
    /**
     * 故障描述
     */
    private String details;
    /**
     * 维修地点
     */
    private String place;
    /**
     * 维修类型
     */
    private Integer typeId;

    /*
    * 审核人
    */
    private Integer shenherenId;
    /*
     * 审核人
     */
    private String shenherenName;
    /*
     * 申请图片
     */
    private String shenqingPictureId;

}
