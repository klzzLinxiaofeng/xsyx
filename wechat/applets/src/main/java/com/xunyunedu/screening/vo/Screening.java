package com.xunyunedu.screening.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Screening {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 排查人员UserId
     */
    private String paichaUserId;
    /*
     * 排查人员姓名
     */
    private String paichaUserName;
    /*
    * 排查时间
    */
    private String paichaTime;
    /*
     * 排查时间
     */
    private Date paichaTime2;
    /*
     * 区域
     */
    private String screeningArea;
    /*
     * 水电
     */
    private Integer waterElectricity;
    /*
     * 安全隐患
     */
    private Integer trouble;
    /*
     * 建筑质量
     */
    private Integer construction;
    /*
     * 设施设备
     */
    private Integer facilities;
    /*
     * 其他
     */
    private Integer qita;
    /*
     * 其他name
     */
    private String qitaName;
    /*
     * 备注
     */
    private String  beizhu;
    /*
     * 附件uuid
     */
    private String fujianUuid;
    /*
     * 附件url
     */
    private String  fujianUrl;
    /*
     *创建时间
     */
    private Date createTime;

    List<Map<String,Object>> list;
    List<Map<String,Object>> user;

    public List<Map<String, Object>> getUser() {
        return user;
    }

    public void setUser(List<Map<String, Object>> user) {
        this.user = user;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public Date getPaichaTime2() {
        return paichaTime2;
    }

    public void setPaichaTime2(Date paichaTime2) {
        this.paichaTime2 = paichaTime2;
    }

    /*
     *修改时间
     */
    private Date modiyTime;
    /*
    *
    */
    private Integer isDelete;

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaichaUserId() {
        return paichaUserId;
    }

    public void setPaichaUserId(String paichaUserId) {
        this.paichaUserId = paichaUserId;
    }

    public String getPaichaUserName() {
        return paichaUserName;
    }

    public void setPaichaUserName(String paichaUserName) {
        this.paichaUserName = paichaUserName;
    }

    public String getPaichaTime() {
        return paichaTime;
    }

    public void setPaichaTime(String paichaTime) {
        this.paichaTime = paichaTime;
    }

    public String getScreeningArea() {
        return screeningArea;
    }

    public void setScreeningArea(String screeningArea) {
        this.screeningArea = screeningArea;
    }

    public Integer getWaterElectricity() {
        return waterElectricity;
    }

    public void setWaterElectricity(Integer waterElectricity) {
        this.waterElectricity = waterElectricity;
    }

    public Integer getTrouble() {
        return trouble;
    }

    public void setTrouble(Integer trouble) {
        this.trouble = trouble;
    }

    public Integer getConstruction() {
        return construction;
    }

    public void setConstruction(Integer construction) {
        this.construction = construction;
    }

    public Integer getFacilities() {
        return facilities;
    }

    public void setFacilities(Integer facilities) {
        this.facilities = facilities;
    }

    public Integer getQita() {
        return qita;
    }

    public void setQita(Integer qita) {
        this.qita = qita;
    }

    public String getQitaName() {
        return qitaName;
    }

    public void setQitaName(String qitaName) {
        this.qitaName = qitaName;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getFujianUuid() {
        return fujianUuid;
    }

    public void setFujianUuid(String fujianUuid) {
        this.fujianUuid = fujianUuid;
    }

    public String getFujianUrl() {
        return fujianUrl;
    }

    public void setFujianUrl(String fujianUrl) {
        this.fujianUrl = fujianUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }
}
