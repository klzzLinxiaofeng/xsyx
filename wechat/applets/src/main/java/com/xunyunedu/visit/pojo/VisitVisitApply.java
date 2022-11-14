package com.xunyunedu.visit.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * visit_visit_apply
 * @author 
 */
public class VisitVisitApply implements Serializable {
    private Integer id;

    /**
     * 申请者openId
     */
    private String applicantOpenId;

    /**
     * 申请者姓名
     */
    private String applicantName;
    /**
     * 申请者手机号
     */
    private String applicantPhone;
    /**
     * 申请者所在单位
     */
    private String applicantUnits;

    /**
     * 来访目的
     */
    private String purpose;

    /**
     * 被访者userId
     */
    private Integer visitUserId;

    /**
     * 被访者姓名
     */
    private String visitUserName;

    /**
     * 被访者审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date visisUserApprovalTime;

    /**
     * 管理员审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date adminApprovalTime;

    /**
     * 来访状态（0：待被访人审批，1：待管理员审批，2：被访人拒绝，3：待入校，4：管理员拒绝，5：已入校）
     */
    private Integer visitStatus;

    /**
     * 驳回理由
     */
    private String refuseCause;

    /**
     * 审批管理员userId
     */
    private Integer approvalAdminUserId;

    /**
     * 审批管理员姓名
     */
    private String approvalAdminUserName;

    /**
     * 二维码内容
     */
    private String qrcodeContent;
    /**
     * 来访日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date visitDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date createDate;
    /**
     * 二维码是否已使用
     */
    private Boolean usedQrcode;
    /**
     * 来访人数
     */
    private Integer visitUserCount;

    /**
     * 来访用户信息列表
     */
    private List<VisitVisitApplyUser> visitUserList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApplicantOpenId() {
        return applicantOpenId;
    }

    public void setApplicantOpenId(String applicantOpenId) {
        this.applicantOpenId = applicantOpenId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getVisitUserId() {
        return visitUserId;
    }

    public void setVisitUserId(Integer visitUserId) {
        this.visitUserId = visitUserId;
    }

    public String getVisitUserName() {
        return visitUserName;
    }

    public void setVisitUserName(String visitUserName) {
        this.visitUserName = visitUserName;
    }

    public Date getVisisUserApprovalTime() {
        return visisUserApprovalTime;
    }

    public void setVisisUserApprovalTime(Date visisUserApprovalTime) {
        this.visisUserApprovalTime = visisUserApprovalTime;
    }

    public Date getAdminApprovalTime() {
        return adminApprovalTime;
    }

    public void setAdminApprovalTime(Date adminApprovalTime) {
        this.adminApprovalTime = adminApprovalTime;
    }

    public Integer getVisitStatus() {
        return visitStatus;
    }

    public void setVisitStatus(Integer visitStatus) {
        this.visitStatus = visitStatus;
    }

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }

    public Integer getApprovalAdminUserId() {
        return approvalAdminUserId;
    }

    public void setApprovalAdminUserId(Integer approvalAdminUserId) {
        this.approvalAdminUserId = approvalAdminUserId;
    }

    public String getApprovalAdminUserName() {
        return approvalAdminUserName;
    }

    public void setApprovalAdminUserName(String approvalAdminUserName) {
        this.approvalAdminUserName = approvalAdminUserName;
    }

    public String getQrcodeContent() {
        return qrcodeContent;
    }

    public void setQrcodeContent(String qrcodeContent) {
        this.qrcodeContent = qrcodeContent;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<VisitVisitApplyUser> getVisitUserList() {
        return visitUserList;
    }

    public void setVisitUserList(List<VisitVisitApplyUser> visitUserList) {
        this.visitUserList = visitUserList;
    }

    public Boolean getUsedQrcode() {
        return usedQrcode;
    }

    public void setUsedQrcode(Boolean usedQrcode) {
        this.usedQrcode = usedQrcode;
    }


    public Integer getVisitUserCount() {
        return visitUserCount;
    }

    public void setVisitUserCount(Integer visitUserCount) {
        this.visitUserCount = visitUserCount;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getApplicantUnits() {
        return applicantUnits;
    }

    public void setApplicantUnits(String applicantUnits) {
        this.applicantUnits = applicantUnits;
    }
}