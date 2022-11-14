package platform.szxyzxx.oa.vo;

public class WeiXiuDaoChu {
    /*
    * 申请人
    */
    private String shenqingren;
    /*
     * 维修人
     */
    private String weixiuren;
    /*
     * 维修人描述
     */
    private String weixiumiaoshu;
    /*
     * 申请人描述
     */
    private String shenqingmiaoshu;
    /*
     * 主题
     */
    private String title;
    /*
     * 地点
     */
    private String place;
    /*
     * 维修类型
     */
    private String type;
    /*
     * 评价星级
     */
    private Integer xingji;
    /*
     * 评语
     */
    private String pingyu;
    /*
    * 申请时间
    */
    private String createTime;
    /*
     * 申请时间
     */
    private String acceptDate;
    /*
     * 申请时间
     */
    private Integer isHaoCai;
    /*
     * 申请时间
     */
    private String haoCai;

    public String getHaoCai() {
        return haoCai;
    }

    public void setHaoCai(String haoCai) {
        this.haoCai = haoCai;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Integer getIsHaoCai() {
        return isHaoCai;
    }

    public void setIsHaoCai(Integer isHaoCai) {
        this.isHaoCai = isHaoCai;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getWeixiumiaoshu() {
        return weixiumiaoshu;
    }

    public void setWeixiumiaoshu(String weixiumiaoshu) {
        this.weixiumiaoshu = weixiumiaoshu;
    }

    public String getShenqingren() {
        return shenqingren;
    }

    public void setShenqingren(String shenqingren) {
        this.shenqingren = shenqingren;
    }

    public String getWeixiuren() {
        return weixiuren;
    }

    public void setWeixiuren(String weixiuren) {
        this.weixiuren = weixiuren;
    }

    public String getShenqingmiaoshu() {
        return shenqingmiaoshu;
    }

    public void setShenqingmiaoshu(String shenqingmiaoshu) {
        this.shenqingmiaoshu = shenqingmiaoshu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getXingji() {
        return xingji;
    }

    public void setXingji(Integer xingji) {
        this.xingji = xingji;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }
}
