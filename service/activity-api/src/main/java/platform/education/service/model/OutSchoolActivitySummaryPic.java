package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * OutSchoolActivitySummaryPic
 *
 * @author AutoCreate
 */
public class OutSchoolActivitySummaryPic implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 校外活动总结ID
     */
    private Integer summaryId;
    /**
     * 校外活动总结图片
     */
    private String picture;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    public OutSchoolActivitySummaryPic() {

    }

    public OutSchoolActivitySummaryPic(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取id
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取校外活动总结ID
     *
     * @return java.lang.Integer
     */
    public Integer getSummaryId() {
        return this.summaryId;
    }

    /**
     * 设置校外活动总结ID
     *
     * @param summaryId
     * @type java.lang.Integer
     */
    public void setSummaryId(Integer summaryId) {
        this.summaryId = summaryId;
    }

    /**
     * 获取校外活动总结图片
     *
     * @return java.lang.String
     */
    public String getPicture() {
        return this.picture;
    }

    /**
     * 设置校外活动总结图片
     *
     * @param picture
     * @type java.lang.String
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取createDate
     *
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     *
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取modifyDate
     *
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置modifyDate
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}