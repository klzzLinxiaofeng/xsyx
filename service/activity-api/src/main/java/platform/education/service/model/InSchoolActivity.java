package platform.education.service.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * InSchoolActivity
 *
 * @author AutoCreate
 */
public class InSchoolActivity implements Model<Integer> {


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
     * 申请人用户名
     */
    private String userName;
    /**
     * 活动室ID
     */
    private Integer roomId;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 活动描述
     */
    private String description;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    /**
     * 活动状态（0：待审批，1：通过，2：驳回）
     */
    private Integer state;

    /**
     * 驳回理由
     */
    private String refuseCause;

    /**
     * 参加活动的用户id字符串，多个用,分割
     */
    private String attendUserIds;

    /**
     * 参加活动的用户姓名字符串，多个用,分割
     */
    private String attendUserNames;
    /**
     * 审批用户id
     */
    private Integer handleUserId;

    private String roomName;

    @Override
    public Integer getKey() {
        return id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }

    public String getAttendUserIds() {
        return attendUserIds;
    }

    public void setAttendUserIds(String attendUserIds) {
        this.attendUserIds = attendUserIds;
    }

    public String getAttendUserNames() {
        return attendUserNames;
    }

    public void setAttendUserNames(String attendUserNames) {
        this.attendUserNames = attendUserNames;
    }

    public Integer getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Integer handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}