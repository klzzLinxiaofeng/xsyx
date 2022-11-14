package com.xunyunedu.syllabus.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 调课申请表(OaAdjustClass)实体类
 *
 * @author sjz
 * @since 2021-03-30 14:20:28
 */
public class AdjustClassPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 提交时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /**
     * 标题
     */
    private String title;
    /**
     * 说明 理由
     */
    private String reason;
    /**
     * /**
     * 申请人id
     */
    private Integer applicantId;

    /**
     * 申请人名字
     */
    private String applicantName;
    /**
     * 审批人id
     */
    private Integer approverId;

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }

    /**
     * 审批人名字
     */
    private String approverName;
    /**
     * 状态 0 审批中 1 同意 2 驳回
     */
    private Integer status;
    /**
     * 审批时间
     */
    private Date approveDate;
    /**
     * 驳回理由
     */
    private String rejectionReason;

    /**
     * 申请需要调课的日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date applyDate;
    /**
     * 被申请调课的日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date setDate;
    /**
     * 申请调课的节次
     */
    private String applyLesson;
    /**
     * 被申请调课的节次
     */
    private String setLesson;
    /**
     * 通知接收者
     */
    private String receivers;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public Integer getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(Integer applicantId) {
        this.applicantId = applicantId;
    }

    public Integer getApproverId() {
        return approverId;
    }

    public void setApproverId(Integer approverId) {
        this.approverId = approverId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApproverName() {
        return approverName;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }

    public String getApplyLesson() {
        return applyLesson;
    }

    public void setApplyLesson(String applyLesson) {
        this.applyLesson = applyLesson;
    }

    public String getSetLesson() {
        return setLesson;
    }

    public void setSetLesson(String setLesson) {
        this.setLesson = setLesson;
    }

}
