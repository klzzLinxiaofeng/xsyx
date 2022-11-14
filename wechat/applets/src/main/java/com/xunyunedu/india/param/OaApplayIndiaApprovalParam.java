package com.xunyunedu.india.param;

import lombok.Data;

@Data
public class OaApplayIndiaApprovalParam {
    /**
     * 用章申请id
     */
    private Integer id;
    /**
     * 审批结果：3：同意，1：驳回
     */
    private String indiaStatus;
    /**
     * 驳回理由
     */
    private String nonTreatmentCause;
    /**
     * 当前用户id
     */
    private Integer userId;
    /**
     * 申请者userId
     */
    private Integer proposerId;
}
