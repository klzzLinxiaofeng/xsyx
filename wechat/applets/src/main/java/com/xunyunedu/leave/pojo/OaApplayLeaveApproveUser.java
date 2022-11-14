package com.xunyunedu.leave.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class OaApplayLeaveApproveUser {

    Integer id;

    /**
     * 请假条
     */
    Integer leaveId;

    /**
     * 审批人id
     */
    Integer approveId;

    /**
     * 审批状态： 0、未审批  1、已审批
     */
    Integer approveState;

    /**
     * 创建时间
     */
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;


}
