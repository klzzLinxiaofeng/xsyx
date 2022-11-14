package com.xunyunedu.schoolbus.pojo;

public class ManualOutParam {
    /**
     * 学生id
     */
    private Integer stuId;
    /**
     * 当前用户userId
     */
    private Integer operatorUserId;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getOperatorUserId() {
        return operatorUserId;
    }

    public void setOperatorUserId(Integer operatorUserId) {
        this.operatorUserId = operatorUserId;
    }
}
