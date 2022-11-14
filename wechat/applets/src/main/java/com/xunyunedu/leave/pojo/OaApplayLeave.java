package com.xunyunedu.leave.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunyunedu.common.pojo.EntityFile;
import lombok.Data;

import java.util.Date;

/**
 * @author edison
 */
@Data
public class OaApplayLeave {


    Integer id;

    /**
     * UUID
     */
    String uuid;

    /**
     * appId
     */
    Integer appId;

    /**
     * 公文所属的单位，学校
     */
    Integer ownerId;

    /**
     * 组的类型 ， 1：学校
     */
    String ownerType;

    /**
     * 申请人id
     */
    Integer propserId;

    /**
     * 申请人姓名
     */
    String propserName;

    /**
     * 申请人所属部门
     */
    Integer propserDepartmentId;

    /**
     * 标题
     */
    String title;

    /**
     * 请假类型
     */
    Integer leaveType;

    /**
     * 开始时间
     */
    String startDate;

    /**
     * 结束时间
     */
    String endDate;

    /**
     * 联系电话
     */
    String mobile;

    /**
     * 详情
     */
    String detail;

    /**
     * 有无代课教师  0：有 1：无
     */
    Integer isDaike = 0;

    /**
     * 审批状态 0:未审批，其他：已审批
     */
    String auditStatus;

    /**
     * 审批通过状态  0：审批通过 ，为空或者其他都是审批未通过
     */
    String approveState;

    /**
     * 审批人id
     */
    Integer approveId;

    /**
     * 审批人姓名
     */
    String approveName;

    /**
     * 发布日期
     */
    Date publishDate;

    /**
     * 审批备注
     */
    String approveRemark;

    /**
     * 删除标志
     */
    Boolean isDeleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date createDate;

    /**
     * 修改时间
     */
    Date modifyDate;

    /**
     * 附件uuid
     */
    String attachmentUuid;
    /**
     * 请假时长
     */
    private String timeConsuming;

    private Object attachmentFile;

}
