package platform.education.service.vo;

import platform.education.service.model.OutSchoolActivity;

/**
 * OutSchoolActivity
 *
 * @author AutoCreate
 */
public class OutSchoolActivityCondition extends OutSchoolActivity {
    private static final long serialVersionUID = 1L;
    // 审批者
    private Integer approvalId;
    // 审批状态
    private Integer status;

    public Integer getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Integer approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}