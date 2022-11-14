package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * InSchoolActivityApproval
 *
 * @author AutoCreate
 */
public class InSchoolActivityApproval implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    public static final int STAT100 = 100; // 待审批
    public static final int STAT200 = 200; // 已驳回
    public static final int STAT201 = 201; // 已通过
    public static final String FEEDBACK = "该场地已被占用";

    public int getSTAT100() {
        return STAT100;
    }

    public int getSTAT200() {
        return STAT200;
    }

    public int getSTAT201() {
        return STAT201;
    }

    /**
     * id
     */
    private Integer id;
    /**
     * 审批人
     */
    private Integer userId;
    /**
     * 校内活动ID
     */
    private Integer activityId;
    /**
     * 审批状态
     */
    private Integer status;
    /**
     * 反馈
     */
    private String feedback;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    public InSchoolActivityApproval() {

    }

    public InSchoolActivityApproval(Integer id) {
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
     * 获取审批人
     *
     * @return java.lang.Integer
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置审批人
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
        return this.activityId;
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
     * 获取审批状态
     *
     * @return Integer
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置审批状态
     *
     * @param status
     * @type Integer
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取反馈
     *
     * @return java.lang.String
     */
    public String getFeedback() {
        return this.feedback;
    }

    /**
     * 设置反馈
     *
     * @param feedback
     * @type java.lang.String
     */
    public void setFeedback(String feedback) {
        this.feedback = feedback;
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