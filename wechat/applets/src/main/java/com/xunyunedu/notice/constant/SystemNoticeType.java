package com.xunyunedu.notice.constant;

public enum SystemNoticeType {
    /**
     * 活动通知
     */
    ACTIVITY("activity","活动通知"),
    /**
     * 审批通知
     */
    APPROVAL("approval","审批通知"),
    /**
     * 请假通知
     */
    LEAVE("leave","请假通知");

    private String noticeType;
    private String noticeTypeName;
    SystemNoticeType(String noticeTyp, String noticeTypeName) {
        this.noticeTypeName=noticeTypeName;
        this.noticeType=noticeTyp;
    }


    public String getType(){
        return this.noticeType;
    }

    public String getTypeName(){
        return this.noticeTypeName;
    }
}
