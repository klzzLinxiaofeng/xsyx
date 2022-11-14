package com.xunyunedu.accommodation.pojo;

import java.util.Date;

public class SettlementAmount {
    private Integer id;
    private Integer accommodationId;
    private Double zhufangJine;
    private Double hotShuiJine;
    private Double lenShuiJine;
    private Double dianJine;
    private Integer isDelete;
    private Date createTime;
    private Date modiyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccommodationId() {
        return accommodationId;
    }

    public void setAccommodationId(Integer accommodationId) {
        this.accommodationId = accommodationId;
    }

    public Double getZhufangJine() {
        return zhufangJine;
    }

    public void setZhufangJine(Double zhufangJine) {
        this.zhufangJine = zhufangJine;
    }

    public Double getHotShuiJine() {
        return hotShuiJine;
    }

    public void setHotShuiJine(Double hotShuiJine) {
        this.hotShuiJine = hotShuiJine;
    }

    public Double getLenShuiJine() {
        return lenShuiJine;
    }

    public void setLenShuiJine(Double lenShuiJine) {
        this.lenShuiJine = lenShuiJine;
    }

    public Double getDianJine() {
        return dianJine;
    }

    public void setDianJine(Double dianJine) {
        this.dianJine = dianJine;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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
