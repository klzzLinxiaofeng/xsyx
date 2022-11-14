package com.xunyunedu.visit.pojo;

import java.util.List;

public class VisitApplyCondition {
    /**
     * 状态集合（0：待被访人审批，1：待管理员审批，2：被访人拒绝，3：待入校，4：管理员拒绝，5：已入校，6：已失效）
     */
    private List<Integer> statusList;
    /**
     * 访客openId
     */
    private String openId;
    /**
     * 教师userId
     */
    private Integer userId;

    /**
     * 是否管理员
     */
    private boolean isAdmin;

    /**
     * 来访日期
     */
    private String date;

    /**
     * 0：包含“待入校”，不包含“已失效”
     * 1：包含“已失效”，不包含“待入校”
     */
    private Integer drxFlag;

    private String nowDate;


    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDrxFlag() {
        return drxFlag;
    }

    public void setDrxFlag(Integer drxFlag) {
        this.drxFlag = drxFlag;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
