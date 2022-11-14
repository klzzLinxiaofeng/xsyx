package com.xunyunedu.leave.param;

import lombok.Data;

import java.util.Date;

@Data
public class OaApplayLeaveInsertParam {


    /**
     * 學校Id
     */
    Integer schoolId;

    /**
     * 申请人id
     */
    Integer propserId;

    /**
     * 申请人姓名
     */
    String propserName;

    /**
     * 请假说明
     */
    String detail;

    /**
     * 审批人userId列表，多个用英文逗号隔开
     */
    String approveIds;

    /**
     * 请假类型
     */
    Integer leaveType;

    /**
     * 附件uuid
     */
    String attachmentUuid;

    /**
     * 请假开始时间
     */
    String startDate;

    /**
     * 请假结束时间
     */
    String endDate;
    /**
     * 部门id,不需要传
     */
    private Integer propserDepartmentId;
    /**
     * 请假时长
     */
    private String timeConsuming;
}
