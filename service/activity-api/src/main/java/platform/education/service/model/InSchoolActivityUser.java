package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * InSchoolActivityUser
 *
 * @author AutoCreate
 */
public class InSchoolActivityUser implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 参与人
     */
    private Integer userId;
    /**
     * 校内活动ID
     */
    private Integer activityId;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    public InSchoolActivityUser() {

    }

    public InSchoolActivityUser(Integer id) {
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
     * 获取参与人
     *
     * @return java.lang.Integer
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置参与人
     *
     * @param userId
     * @type java.lang.Integer
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取校内活动ID
     *
     * @return java.lang.Integer
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * 设置校内活动ID
     *
     * @param activityId
     * @type java.lang.Integer
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
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