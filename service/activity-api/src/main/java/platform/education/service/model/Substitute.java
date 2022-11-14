package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * Substitute
 *
 * @author AutoCreate
 */
public class Substitute implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    public static final int STAT100 = 100; // 待审批
    public static final int STAT200 = 200; // 已驳回
    public static final int STAT201 = 201; // 已同意
    // 非审批状态，查询条件使用
    public static final int GREAT_THAN_OR_EQUAL_TO200 = 666; // 已审批

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
     * 申请人
     */
    private Integer userId;
    /**
     * 学校ID
     */
    private Integer schoolId;
    /**
     * 接收者
     */
    private Integer receiver;
    /**
     * 审批状态
     */
    private Integer status;
    /**
     * 开始时间
     */
    private java.util.Date startTime;
    /**
     * 结束时间
     */
    private java.util.Date endTime;
    /**
     * 代课说明
     */
    private String description;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 附件
     */
    private String accessory;
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

    private String userName;

    public Substitute() {

    }

    public Substitute(Integer id) {
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
     * 获取申请人
     *
     * @return java.lang.Integer
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置申请人
     *
     * @param userId
     * @type java.lang.Integer
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取学校ID
     *
     * @return java.lang.Integer
     */
    public Integer getSchoolId() {
        return this.schoolId;
    }

    /**
     * 设置学校ID
     *
     * @param schoolId
     * @type java.lang.Integer
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取接收者
     *
     * @return java.lang.Integer
     */
    public Integer getReceiver() {
        return this.receiver;
    }

    /**
     * 设置接收者
     *
     * @param receiver
     * @type java.lang.Integer
     */
    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
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
     * 获取开始时间
     *
     * @return java.util.Date
     */
    public java.util.Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime
     * @type java.util.Date
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return java.util.Date
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime
     * @type java.util.Date
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取代课说明
     *
     * @return java.lang.String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置代课说明
     *
     * @param description
     * @type java.lang.String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取账号
     *
     * @return java.lang.String
     */
    public String getAccount() {
        return this.account;
    }

    /**
     * 设置账号
     *
     * @param account
     * @type java.lang.String
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取密码
     *
     * @return java.lang.String
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     *
     * @param password
     * @type java.lang.String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取附件
     *
     * @return java.lang.String
     */
    public String getAccessory() {
        return this.accessory;
    }

    /**
     * 设置附件
     *
     * @param accessory
     * @type java.lang.String
     */
    public void setAccessory(String accessory) {
        this.accessory = accessory;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}