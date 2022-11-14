package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * OutSchoolActivitySummary
 *
 * @author AutoCreate
 */
public class OutSchoolActivitySummary implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 校外活动ID
     */
    private Integer activityId;
    /**
     * 校外活动总结内容
     */
    private String content;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    public OutSchoolActivitySummary() {

    }

    public OutSchoolActivitySummary(Integer id) {
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
     * 获取校外活动ID
     *
     * @return java.lang.Integer
     */
    public Integer getActivityId() {
        return this.activityId;
    }

    /**
     * 设置校外活动ID
     *
     * @param activityId
     * @type java.lang.Integer
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * 获取校外活动总结内容
     *
     * @return java.lang.String
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置校外活动总结内容
     *
     * @param content
     * @type java.lang.String
     */
    public void setContent(String content) {
        this.content = content;
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