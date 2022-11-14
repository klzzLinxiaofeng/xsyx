package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

import java.io.Serializable;
import java.util.Date;

/**
 * 德育-优秀班级-学校抽检
 * @TableName dy_excellent_team_yrcg
 */
public class DyExcellentTeamYrcg implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 班级名称
     */
    @ExcelColumnImport(index = 0,ignoreValue = {"项目","班别", CellValueContants.BLANK})
    private String teamName;

    /**
     * 学年
     */
    private String xn;

    /**
     * 学期
     */
    private String xq;

    /**
     * 学期月
     */
    private Integer xqy;

    /**
     * 学期周
     */
    private Integer xqz;

    /**
     * 佩戴校卡得分
     */
    @ExcelColumnImport(index = 1,ignoreValue =CellValueContants.BLANK )
    private Integer pdxk;

    /**
     * 总分
     */
    private Integer sumScore;

    /**
     * 仪容整洁得分
     */
    @ExcelColumnImport(index = 2)
    private Integer rszj;

    /**
     * 文明礼貌得分
     */
    @ExcelColumnImport(index = 3)
    private Integer wmlm;

    /**
     * 自觉有序得分
     */
    @ExcelColumnImport(index = 4)
    private Integer zjyx;

    /**
     * 有事可做得分
     */
    @ExcelColumnImport(index = 5)
    private Integer yskz;

    /**
     * 轻言慢步得分
     */
    @ExcelColumnImport(index = 6)
    private Integer qymb;

    /**
     * 文明游戏得分
     */
    @ExcelColumnImport(index = 7)
    private Integer wmyx;

    /**
     * 书包柜、黑板、讲台、地面整洁得分
     */
    @ExcelColumnImport(index = 8)
    private Integer zj;

    /**
     * 个人物品整洁得分
     */
    @ExcelColumnImport(index = 9)
    private Integer grzj;

    /**
     * 门窗及时开关得分
     */
    @ExcelColumnImport(index = 10)
    private Integer mckg;

    /**
     * 眼保健操有序得分
     */
    @ExcelColumnImport(index = 11)
    private Integer ybjc;

    /**
     * 大型活动有序得分
     */
    @ExcelColumnImport(index = 12)
    private Integer dxhd;

    /**
     * 大课间
有序得分
     */
    @ExcelColumnImport(index = 13)
    private Integer dkj;

    /**
     * 校车接送点得分
     */
    @ExcelColumnImport(index = 14)
    private Integer xdjsd;

    /**
     * 自驾接送点得分
     */
    @ExcelColumnImport(index = 15)
    private Integer zjjsd;

    /**
     * 所属日期
     */
    private String ofDate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人姓名
     */
    private String creator;

    /**
     * 创建人user_id
     */
    private Integer creatorId;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 班级名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 班级名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * 学年
     */
    public String getXn() {
        return xn;
    }

    /**
     * 学年
     */
    public void setXn(String xn) {
        this.xn = xn;
    }

    /**
     * 学期
     */
    public String getXq() {
        return xq;
    }

    /**
     * 学期
     */
    public void setXq(String xq) {
        this.xq = xq;
    }

    /**
     * 学期月
     */
    public Integer getXqy() {
        return xqy;
    }

    /**
     * 学期月
     */
    public void setXqy(Integer xqy) {
        this.xqy = xqy;
    }

    /**
     * 学期周
     */
    public Integer getXqz() {
        return xqz;
    }

    /**
     * 学期周
     */
    public void setXqz(Integer xqz) {
        this.xqz = xqz;
    }

    /**
     * 佩戴校卡得分
     */
    public Integer getPdxk() {
        return pdxk;
    }

    /**
     * 佩戴校卡得分
     */
    public void setPdxk(Integer pdxk) {
        this.pdxk = pdxk;
    }

    /**
     * 总分
     */
    public Integer getSumScore() {
        return sumScore;
    }

    /**
     * 总分
     */
    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    /**
     * 仪容整洁得分
     */
    public Integer getRszj() {
        return rszj;
    }

    /**
     * 仪容整洁得分
     */
    public void setRszj(Integer rszj) {
        this.rszj = rszj;
    }

    /**
     * 文明礼貌得分
     */
    public Integer getWmlm() {
        return wmlm;
    }

    /**
     * 文明礼貌得分
     */
    public void setWmlm(Integer wmlm) {
        this.wmlm = wmlm;
    }

    /**
     * 自觉有序得分
     */
    public Integer getZjyx() {
        return zjyx;
    }

    /**
     * 自觉有序得分
     */
    public void setZjyx(Integer zjyx) {
        this.zjyx = zjyx;
    }

    /**
     * 有事可做得分
     */
    public Integer getYskz() {
        return yskz;
    }

    /**
     * 有事可做得分
     */
    public void setYskz(Integer yskz) {
        this.yskz = yskz;
    }

    /**
     * 轻言慢步得分
     */
    public Integer getQymb() {
        return qymb;
    }

    /**
     * 轻言慢步得分
     */
    public void setQymb(Integer qymb) {
        this.qymb = qymb;
    }

    /**
     * 文明游戏得分
     */
    public Integer getWmyx() {
        return wmyx;
    }

    /**
     * 文明游戏得分
     */
    public void setWmyx(Integer wmyx) {
        this.wmyx = wmyx;
    }

    /**
     * 书包柜、黑板、讲台、地面整洁得分
     */
    public Integer getZj() {
        return zj;
    }

    /**
     * 书包柜、黑板、讲台、地面整洁得分
     */
    public void setZj(Integer zj) {
        this.zj = zj;
    }

    /**
     * 个人物品整洁得分
     */
    public Integer getGrzj() {
        return grzj;
    }

    /**
     * 个人物品整洁得分
     */
    public void setGrzj(Integer grzj) {
        this.grzj = grzj;
    }

    /**
     * 门窗及时开关得分
     */
    public Integer getMckg() {
        return mckg;
    }

    /**
     * 门窗及时开关得分
     */
    public void setMckg(Integer mckg) {
        this.mckg = mckg;
    }

    /**
     * 眼保健操有序得分
     */
    public Integer getYbjc() {
        return ybjc;
    }

    /**
     * 眼保健操有序得分
     */
    public void setYbjc(Integer ybjc) {
        this.ybjc = ybjc;
    }

    /**
     * 大型活动有序得分
     */
    public Integer getDxhd() {
        return dxhd;
    }

    /**
     * 大型活动有序得分
     */
    public void setDxhd(Integer dxhd) {
        this.dxhd = dxhd;
    }

    /**
     * 大课间
有序得分
     */
    public Integer getDkj() {
        return dkj;
    }

    /**
     * 大课间
有序得分
     */
    public void setDkj(Integer dkj) {
        this.dkj = dkj;
    }

    /**
     * 校车接送点得分
     */
    public Integer getXdjsd() {
        return xdjsd;
    }

    /**
     * 校车接送点得分
     */
    public void setXdjsd(Integer xdjsd) {
        this.xdjsd = xdjsd;
    }

    /**
     * 自驾接送点得分
     */
    public Integer getZjjsd() {
        return zjjsd;
    }

    /**
     * 自驾接送点得分
     */
    public void setZjjsd(Integer zjjsd) {
        this.zjjsd = zjjsd;
    }

    /**
     * 所属日期
     */
    public String getOfDate() {
        return ofDate;
    }

    /**
     * 所属日期
     */
    public void setOfDate(String ofDate) {
        this.ofDate = ofDate;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人姓名
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建人姓名
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 创建人user_id
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * 创建人user_id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}