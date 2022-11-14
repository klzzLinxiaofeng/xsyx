package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

import java.io.Serializable;
import java.util.Date;

/**
 * 德育-优秀班级-学校抽检
 * @TableName dy_excellent_team_dxhd
 */
public class DyExcellentTeamDxhd implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 班级名称
     */
    @ExcelColumnImport(index = 0)
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
     * 主要参与得分
     */
    @ExcelColumnImport(index = 1,ignoreValue = CellValueContants.BLANK)
    private Integer zycy;

    /**
     * 积极配合得分
     */
    @ExcelColumnImport(index = 2)
    private Integer jjph;

    /**
     * 总分
     */
    private Integer sumScore;

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
     * 主要参与得分
     */
    public Integer getZycy() {
        return zycy;
    }

    /**
     * 主要参与得分
     */
    public void setZycy(Integer zycy) {
        this.zycy = zycy;
    }

    /**
     * 积极配合得分
     */
    public Integer getJjph() {
        return jjph;
    }

    /**
     * 积极配合得分
     */
    public void setJjph(Integer jjph) {
        this.jjph = jjph;
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