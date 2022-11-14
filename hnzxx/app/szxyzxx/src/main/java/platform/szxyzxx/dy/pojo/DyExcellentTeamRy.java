package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.io.Serializable;
import java.util.Date;

/**
 * 德育-优秀班级-个人荣誉、班级荣誉
 * @TableName dy_excellent_team_ry
 */
public class DyExcellentTeamRy implements Serializable {
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
     * 国家级得分
     */
    @ExcelColumnImport(index = 1)
    private Integer gjj;

    /**
     * 省级得分
     */
    @ExcelColumnImport(index = 2)
    private Integer shengji;

    /**
     * 市级得分
     */
    @ExcelColumnImport(index = 3)
    private Integer shiji;

    /**
     * 镇级得分
     */
    @ExcelColumnImport(index = 4)
    private Integer zj;

    /**
     * 校级得分
     */
    @ExcelColumnImport(index = 5)
    private Integer xj;

    /**
     * 总分
     */
    private Integer sumScore;

    /**
     * 类型(0：个人荣誉，1：集体荣誉)
     */
    private Integer type;

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
     * 国家级得分
     */
    public Integer getGjj() {
        return gjj;
    }

    /**
     * 国家级得分
     */
    public void setGjj(Integer gjj) {
        this.gjj = gjj;
    }

    /**
     * 省级得分
     */
    public Integer getShengji() {
        return shengji;
    }

    /**
     * 省级得分
     */
    public void setShengji(Integer shengji) {
        this.shengji = shengji;
    }

    /**
     * 市级得分
     */
    public Integer getShiji() {
        return shiji;
    }

    /**
     * 市级得分
     */
    public void setShiji(Integer shiji) {
        this.shiji = shiji;
    }

    /**
     * 镇级得分
     */
    public Integer getZj() {
        return zj;
    }

    /**
     * 镇级得分
     */
    public void setZj(Integer zj) {
        this.zj = zj;
    }

    /**
     * 校级得分
     */
    public Integer getXj() {
        return xj;
    }

    /**
     * 校级得分
     */
    public void setXj(Integer xj) {
        this.xj = xj;
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
     * 类型(0：个人荣誉，1：集体荣誉)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 类型(0：个人荣誉，1：集体荣誉)
     */
    public void setType(Integer type) {
        this.type = type;
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