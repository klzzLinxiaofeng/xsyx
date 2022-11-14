package com.xunyunedu.canteen.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜系表
 *
 * @author: yhc
 * @Date: 2020/12/31 15:14
 * @Description:
 */

public class CanteenCuisinPojo implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 菜系名称
     */
    private String cuisineName;

    /**
     * 图片uuid
     */
    private String uuid;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 作废标记
     */
    private Integer isDelete;

    private String url;


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

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

