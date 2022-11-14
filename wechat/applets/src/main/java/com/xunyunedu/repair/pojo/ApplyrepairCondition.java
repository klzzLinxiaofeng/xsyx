package com.xunyunedu.repair.pojo;

import lombok.Data;

/**
 * 查询条件
 */
@Data
public class ApplyrepairCondition {
    /**
     * 申请人userId
     */
    private Integer proposerId;

    /**
     * 维修工userId.status为2必传
     */
    private Integer workerUserId;

    /**
     * 维修单状态（1：待维修,2：已维修）
     */
    private Integer status;

    private Integer id;
}
