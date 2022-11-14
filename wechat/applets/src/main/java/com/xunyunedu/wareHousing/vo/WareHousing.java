package com.xunyunedu.wareHousing.vo;

import java.util.Date;

public class WareHousing {
    /*
    * 主键
    */
    private Integer id;
    /*
     * 申请人id
     */
    private Integer shenqingren;
    /*
     * 申请人Name
     */
    private String shenqingName;
    /*
     * 审核人id
     */
    private Integer shenheId;
    /*
     * 审核人Name
     */
    private String shenheName;
    /*
     * 负责人id
     */
    private Integer fuzeren;
    /*
     * 负责人Name
     */
    private String fuzerenName;
    /*
     * 物资类型
     */

    private Integer type;
    /*
     * 物资类型Name
     */
    private String typeName;
    /*
     * 物资名称
     */
    private String name;
    /*
     * 备注
     */
    private String  beizhu;
    /*
     * 创建时间
     */
    private String createDate;
    /*
     * 修改时间
     */
    private Date modiyDate;
    /*
     * 驳回理由
     */
    private String liyou;
    /*
     * guihuan驳回理由
     */
    private String guiHuanliyou;
    /*
     * 是否需要归还 0不要，1需要
     */
    private Integer isGuihuan;
    /*
     * 审批状态
     */
    private Integer zhuangtai;
    /*
     * 审批状态name
     */
    private String zhuangTaiName;
    private Integer isDelete;

    public String getZhuangTaiName() {
        return zhuangTaiName;
    }

    public void setZhuangTaiName(String zhuangTaiName) {
        this.zhuangTaiName = zhuangTaiName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuiHuanliyou() {
        return guiHuanliyou;
    }

    public void setGuiHuanliyou(String guiHuanliyou) {
        this.guiHuanliyou = guiHuanliyou;
    }

    public Integer getShenqingren() {
        return shenqingren;
    }

    public void setShenqingren(Integer shenqingren) {
        this.shenqingren = shenqingren;
    }

    public String getShenqingName() {
        return shenqingName;
    }

    public void setShenqingName(String shenqingName) {
        this.shenqingName = shenqingName;
    }

    public Integer getShenheId() {
        return shenheId;
    }

    public void setShenheId(Integer shenheId) {
        this.shenheId = shenheId;
    }

    public String getShenheName() {
        return shenheName;
    }

    public void setShenheName(String shenheName) {
        this.shenheName = shenheName;
    }

    public Integer getFuzeren() {
        return fuzeren;
    }

    public void setFuzeren(Integer fuzeren) {
        this.fuzeren = fuzeren;
    }

    public String getFuzerenName() {
        return fuzerenName;
    }

    public void setFuzerenName(String fuzerenName) {
        this.fuzerenName = fuzerenName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Date getModiyDate() {
        return modiyDate;
    }

    public void setModiyDate(Date modiyDate) {
        this.modiyDate = modiyDate;
    }

    public String getLiyou() {
        return liyou;
    }

    public void setLiyou(String liyou) {
        this.liyou = liyou;
    }

    public Integer getIsGuihuan() {
        return isGuihuan;
    }

    public void setIsGuihuan(Integer isGuihuan) {
        this.isGuihuan = isGuihuan;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
