package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

import java.io.Serializable;
import java.util.Date;

/**
 * 德育-优秀班级-一日常规
 * @TableName dy_excellent_team_njpj
 */
public class DyExcellentTeamNjpj implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 班级名称
     */
    @ExcelColumnImport(index = 0,ignoreValue = CellValueContants.BLANK)
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
     * 卫生评价
     */
    @ExcelColumnImport(index = 1,ignoreValue = CellValueContants.BLANK)
    private Integer wspj;

    /**
     * 文化建设评价
     */
    @ExcelColumnImport(index = 2)
    private Integer whjspj;

    /**
     * 总分
     */
    private Integer sumScore;


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

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public Integer getXqy() {
        return xqy;
    }

    public void setXqy(Integer xqy) {
        this.xqy = xqy;
    }

    public Integer getXqz() {
        return xqz;
    }

    public void setXqz(Integer xqz) {
        this.xqz = xqz;
    }

    public Integer getWspj() {
        return wspj;
    }

    public void setWspj(Integer wspj) {
        this.wspj = wspj;
    }

    public Integer getWhjspj() {
        return whjspj;
    }

    public void setWhjspj(Integer whjspj) {
        this.whjspj = whjspj;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
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