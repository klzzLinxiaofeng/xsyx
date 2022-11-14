package com.xunyunedu.repair.pojo;

import lombok.Data;

/**
 * 保修单维修参数
 * @author chenjiaxin
 */
@Data
public class RepairRepairParam {
    /**
     * 当前用户id
     */
    private Integer userId;

    /**
     * 当前用户schoolId
     */
    private Integer schoolId;
    /**
     * 维修单id
     */
    private Integer repariId;

    /**
     * 维修状态(03:已处理，04：未修好)
     */
    private String state;
    /**
     * 维修说明
     */
    private String remark;

    /*
    * 是否耗材
    */
    private Integer isHaoCai;
    /*
     * 维修图片
     */
    private String picture;
}
