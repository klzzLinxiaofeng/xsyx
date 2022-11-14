package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 菜系表实体类
 */

public class XwHqCanteenCuisine implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜系表id
     */
    private Integer id;
    /**
     * 菜系名称
     */
    private String cuisineName;
    /**
     * 菜系图片UUID
     */
    private String uuid;
    /**
     * 菜系图片真实路径
     */
    private String picUrl;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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

    @Override
    public Integer getKey() {
        return this.id;
    }

    @Override
    public String toString() {
        return "XwHqCanteenCuisine{" +
                "id=" + id +
                ", cuisineName='" + cuisineName + '\'' +
                ", uuid='" + uuid + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", isDelete=" + isDelete +
                '}';
    }
}
