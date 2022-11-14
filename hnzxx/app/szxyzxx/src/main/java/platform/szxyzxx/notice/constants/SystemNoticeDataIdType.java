package platform.szxyzxx.notice.constants;

/**
 * 通知dataId对应的数据类别
 */
public enum SystemNoticeDataIdType {
    /**
     * 学生请假
     */
    XSQJ("xsqj"),
    /**
     * 教师请假
     */
    JSQJ("jsqj"),
    /**
     * 校内活动
     */
    XNHD("xnhd"),
    /**
     * 校外活动
     */
    XWHD("xwhd"),
    /**
     * 公文
     */
    GW("gw"),
    /**
     * 用章
     */
    YZ("yz"),
    /**
     * 调课
     */
    TK("tk"),
    /**
     * 代课
     */
    DK("dk");
    private String typeName;
    SystemNoticeDataIdType(String typeName) {
        this.typeName=typeName;
    }


    public String getTypeName(){
        return this.typeName;
    }

}
