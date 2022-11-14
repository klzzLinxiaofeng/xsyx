package platform.education.service.model;

import framework.generic.dao.Model;

/**
 * OutSchoolActivity
 *
 * @author AutoCreate
 */
public class OutSchoolActivity implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 申请人
     */
    private Integer userId;
    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 学校ID
     */
    private Integer schoolId;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动地点
     */
    private String location;
    /**
     * 开始时间
     */
    private java.util.Date startTime;
    /**
     * 结束时间
     */
    private java.util.Date endTime;
    /**
     * 附件
     */
    private String accessory;
    /**
     * 活动描述
     */
    private String description;
    /**
     * createDate
     */
    private java.util.Date createDate;

    /**
     * 审批状态（0：待审批，1：已通过，2：待总结，3：已总结、4：已驳回）
     */
    private Integer state;
    /**
     * 活动总结
     */
    private String summary;
    /**
     * 活动总结图片url，多个用英文逗号分割
     */
    private String summaryImgs;

    /**
     * 审批人userId
     */
    private Integer handleUserId;

    private String refuseCause;

    /**
     * modifyDate
     */
    private java.util.Date modifyDate;




    public OutSchoolActivity() {

    }

    public OutSchoolActivity(Integer id) {
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
        return schoolId;
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
     * 获取活动名称
     *
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置活动名称
     *
     * @param name
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取活动地点
     *
     * @return java.lang.String
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 设置活动地点
     *
     * @param location
     * @type java.lang.String
     */
    public void setLocation(String location) {
        this.location = location;
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
     * 获取活动描述
     *
     * @return java.lang.String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置活动描述
     *
     * @param description
     * @type java.lang.String
     */
    public void setDescription(String description) {
        this.description = description;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummaryImgs() {
        return summaryImgs;
    }

    public void setSummaryImgs(String summaryImgs) {
        this.summaryImgs = summaryImgs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Integer handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }
}