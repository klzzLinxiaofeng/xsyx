package platform.szxyzxx.notice.pojo;


import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;

/**
 * 申请审批事项通知，包含审批结果
 */
public class OaApplyNotice {
    /**
     * 标题
     */
    private String title;
    /**
     * 接收者id
     */
    private Integer receiverId;
    /**
     * 申请者姓名
     */
    private String applicantName;
    /**
     * 申请理由
     */
    private String applyExplain;
    /**
     * 审批结果：0：待审批，1：审批通过，2：审批不通过
     */
    private Integer applyResult;
    /**
     * 数据id
     */
    private Integer dataId;

    /**
     * 数据类型：xsqj：学生请假，jsqj：教师请假，gw：公文，yz：用章，dk：代课，xnhd：校内活动，xwhd：校外活动
     */
    private SystemNoticeDataIdType dataIdType;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplyExplain() {
        return applyExplain;
    }

    public void setApplyExplain(String applyExplain) {
        this.applyExplain = applyExplain;
    }

    public Integer getApplyResult() {
        return applyResult;
    }

    public void setApplyResult(Integer applyResult) {
        this.applyResult = applyResult;
    }

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public SystemNoticeDataIdType getDataIdType() {
        return dataIdType;
    }

    public void setDataIdType(SystemNoticeDataIdType dataIdType) {
        this.dataIdType = dataIdType;
    }
}
