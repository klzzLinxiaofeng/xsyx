package platform.education.service.vo;

import platform.education.service.model.OutSchoolActivity;

public class OutSchoolActivityAndApprovalVo extends OutSchoolActivity {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String approvalName;
    private String approvalAlias;
    private String fileName;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalAlias() {
        return approvalAlias;
    }

    public void setApprovalAlias(String approvalAlias) {
        this.approvalAlias = approvalAlias;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
