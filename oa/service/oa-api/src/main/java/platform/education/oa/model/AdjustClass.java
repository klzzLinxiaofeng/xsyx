package platform.education.oa.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-26 16:16
 */
public class AdjustClass implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 说明（理由）
     */
    private String reason;

    /**
     * 申请调课的节次
     */
    private String applyLesson;

    /**
     * 申请调到的节次
     */
    private String setLesson;

    /**
     * 申请调课的日期
     */
    private Date applyDate;

    /**
     * 申请调到的日期
     */
    private Date setDate;

    /**
     * 申请人id
     */
    private Integer applicantId;

    /**
     * 审批人id
     */
    private Integer approverId;

    /**
     * 驳回理由
     */
    private String rejectionReason;

    /**
     * 状态 0 审批中 1 同意 2 驳回
     */
    private Integer status;

    /**
     * 提交时间
     */
    private Date createDate;

    /**
     * 审批时间
     */
    private Date approveDate;

    /**
     * 申请人姓名
     */
    private String applicantName;

    /**
     * 审批人姓名
     */
    private String approverName;

    /**
     * 通知接受者
     */
    private String receivers;

    @Override
    public Integer getKey() {
        return this.id;
    }

    public AdjustClass() {
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdjustClass{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", reason='").append(reason).append('\'');
        sb.append(", applyLesson='").append(applyLesson).append('\'');
        sb.append(", setLesson='").append(setLesson).append('\'');
        sb.append(", applyDate=").append(applyDate);
        sb.append(", setDate=").append(setDate);
        sb.append(", applicantId=").append(applicantId);
        sb.append(", approverId=").append(approverId);
        sb.append(", rejectionReason='").append(rejectionReason).append('\'');
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", approveDate=").append(approveDate);
        sb.append(", applicantName='").append(applicantName).append('\'');
        sb.append(", approverName='").append(approverName).append('\'');
        sb.append(", receivers='").append(receivers).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
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

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }
}
