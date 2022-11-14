package com.xunyunedu.canteen.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 食堂风采&食堂实景&采购源
 *
 * @author: yhc
 * @Date: 2021/1/3 16:39
 * @Description:
 */
public class CanteenPublicityPojo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 图片uuid
     */
    private String uuid;

    private String url;


    /**
     * 0：视频1: 供应商资质2:宣传图片
     */
    private Integer type;

    /**
     * 采购源名称
     */
    private String name;

    /**
     *
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

