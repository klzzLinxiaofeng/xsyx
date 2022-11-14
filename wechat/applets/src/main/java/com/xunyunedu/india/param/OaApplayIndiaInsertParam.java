package com.xunyunedu.india.param;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OaApplayIndiaInsertParam {

    private Integer schoolId;

    private String groupType;
    /**
     * 申请人userId
     */
    private Integer proposerId;
    /**
     * 申请人姓名
     */
    private String proposerName;
    /**
     * 申请人手机号
     */
    private String proposerTelephone;
    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startDate;
    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endDate;
    /**
     * 申请说明
     */
    private String remark;
    /**
     * 附件id
     */
    private String uploadId;

    /**
     * 审批人userId
     */
    private Integer approverId;

    /**
     * 部门id，不需要传
     */
    private Integer departmentId;
}
