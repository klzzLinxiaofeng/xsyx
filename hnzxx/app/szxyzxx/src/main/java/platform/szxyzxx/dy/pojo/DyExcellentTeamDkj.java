package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 德育-优秀班级-大课间评分
 * @TableName dy_excellent_team_dkj
 */
public class DyExcellentTeamDkj implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 班级名称
     */
    @ExcelColumnImport(index = 0)
    private String teamName;
    @ExcelColumnImport(index = 1,ignoreValue = CellValueContants.BLANK)
    private BigDecimal fzty;
    @ExcelColumnImport(index = 2)
    private BigDecimal jhdl;
    @ExcelColumnImport(index = 3)
    private BigDecimal asdc;
    @ExcelColumnImport(index = 4)
    private BigDecimal yxtc;
    @ExcelColumnImport(index = 5)
    private BigDecimal cdgj;
    @ExcelColumnImport(index = 6)
    private BigDecimal rscjscy;
    @ExcelColumnImport(index = 7)
    private BigDecimal rscdzsm;
    @ExcelColumnImport(index = 8)
    private BigDecimal jslb;
    @ExcelColumnImport(index = 9)
    private BigDecimal xsdd;
    @ExcelColumnImport(index = 10)
    private BigDecimal pcjhdl;
    @ExcelColumnImport(index = 11)
    private BigDecimal tndzsm;
    @ExcelColumnImport(index = 12)
    private BigDecimal tnjhdl;
    @ExcelColumnImport(index = 13)
    private BigDecimal tsjscy;
    @ExcelColumnImport(index = 14)
    private BigDecimal tsxsyx;
    @ExcelColumnImport(index = 15)
    private BigDecimal fscdzsm;
    @ExcelColumnImport(index = 16)
    private BigDecimal fscjhdl;

    @ExcelColumnImport(index = 17)
    private BigDecimal jf;

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
     * 进退场得分
     */
    private BigDecimal jtc;

    /**
     * 科创轻器械特色操得分
     */
    private BigDecimal tsc;

    /**
     * 开放跑操得分
     */
    private BigDecimal kfpc;

    /**
     * 共享体能操得分
     */
    private BigDecimal gxtnc;

    /**
     * 绳梯敏捷活动得分
     */
    private BigDecimal stmjhd;

    /**
     * 放松操
     */
    private BigDecimal fsc;

    /**
     * 总分
     */
    private BigDecimal sumScore;

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

    public BigDecimal getFzty() {
        return fzty;
    }

    public void setFzty(BigDecimal fzty) {
        this.fzty = fzty;
    }

    public BigDecimal getJhdl() {
        return jhdl;
    }

    public void setJhdl(BigDecimal jhdl) {
        this.jhdl = jhdl;
    }

    public BigDecimal getAsdc() {
        return asdc;
    }

    public void setAsdc(BigDecimal asdc) {
        this.asdc = asdc;
    }

    public BigDecimal getYxtc() {
        return yxtc;
    }

    public void setYxtc(BigDecimal yxtc) {
        this.yxtc = yxtc;
    }

    public BigDecimal getCdgj() {
        return cdgj;
    }

    public void setCdgj(BigDecimal cdgj) {
        this.cdgj = cdgj;
    }

    public BigDecimal getRscjscy() {
        return rscjscy;
    }

    public void setRscjscy(BigDecimal rscjscy) {
        this.rscjscy = rscjscy;
    }

    public BigDecimal getRscdzsm() {
        return rscdzsm;
    }

    public void setRscdzsm(BigDecimal rscdzsm) {
        this.rscdzsm = rscdzsm;
    }

    public BigDecimal getJslb() {
        return jslb;
    }

    public void setJslb(BigDecimal jslb) {
        this.jslb = jslb;
    }

    public BigDecimal getXsdd() {
        return xsdd;
    }

    public void setXsdd(BigDecimal xsdd) {
        this.xsdd = xsdd;
    }

    public BigDecimal getPcjhdl() {
        return pcjhdl;
    }

    public void setPcjhdl(BigDecimal pcjhdl) {
        this.pcjhdl = pcjhdl;
    }

    public BigDecimal getTndzsm() {
        return tndzsm;
    }

    public void setTndzsm(BigDecimal tndzsm) {
        this.tndzsm = tndzsm;
    }

    public BigDecimal getTnjhdl() {
        return tnjhdl;
    }

    public void setTnjhdl(BigDecimal tnjhdl) {
        this.tnjhdl = tnjhdl;
    }

    public BigDecimal getTsjscy() {
        return tsjscy;
    }

    public void setTsjscy(BigDecimal tsjscy) {
        this.tsjscy = tsjscy;
    }

    public BigDecimal getTsxsyx() {
        return tsxsyx;
    }

    public void setTsxsyx(BigDecimal tsxsyx) {
        this.tsxsyx = tsxsyx;
    }

    public BigDecimal getFscdzsm() {
        return fscdzsm;
    }

    public void setFscdzsm(BigDecimal fscdzsm) {
        this.fscdzsm = fscdzsm;
    }

    public BigDecimal getFscjhdl() {
        return fscjhdl;
    }

    public void setFscjhdl(BigDecimal fscjhdl) {
        this.fscjhdl = fscjhdl;
    }

    public BigDecimal getJf() {
        return jf;
    }

    public void setJf(BigDecimal jf) {
        this.jf = jf;
    }

    public BigDecimal getJtc() {
        return jtc;
    }

    public void setJtc(BigDecimal jtc) {
        this.jtc = jtc;
    }

    public BigDecimal getTsc() {
        return tsc;
    }

    public void setTsc(BigDecimal tsc) {
        this.tsc = tsc;
    }

    public BigDecimal getKfpc() {
        return kfpc;
    }

    public void setKfpc(BigDecimal kfpc) {
        this.kfpc = kfpc;
    }

    public BigDecimal getGxtnc() {
        return gxtnc;
    }

    public void setGxtnc(BigDecimal gxtnc) {
        this.gxtnc = gxtnc;
    }

    public BigDecimal getStmjhd() {
        return stmjhd;
    }

    public void setStmjhd(BigDecimal stmjhd) {
        this.stmjhd = stmjhd;
    }

    public BigDecimal getFsc() {
        return fsc;
    }

    public void setFsc(BigDecimal fsc) {
        this.fsc = fsc;
    }

    public BigDecimal getSumScore() {
        return sumScore;
    }

    public void setSumScore(BigDecimal sumScore) {
        this.sumScore = sumScore;
    }

    /**
     * 创建人user_id
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
}