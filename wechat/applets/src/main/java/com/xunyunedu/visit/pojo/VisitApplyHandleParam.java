package com.xunyunedu.visit.pojo;

public class VisitApplyHandleParam {

    /**
     * 当前用户userId
     */
    private Integer userId;
    /**
     * 当前用户姓名
     */
    private String userName;
    /**
     * 当前用户是否是管理员
     */
    private Boolean admin;
    /**
     * 访校申请id
     */
    private Integer applyId;
    /**
     * 审批结果：0：同意，1：驳回
     */
    private Integer result;
    /**
     * 驳回理由
     */
    private String refuseCause;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
