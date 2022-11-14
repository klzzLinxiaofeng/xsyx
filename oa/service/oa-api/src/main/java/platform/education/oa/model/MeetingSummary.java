package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * MeetingSummary
 * @author AutoCreate
 *
 */
public class MeetingSummary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 会议ID
	 */
	private Integer meetingId;
	/**
	 * 记录人
	 */
	private Integer userId;
	/**
	 * 记录人名字
	 */
	private String userName;
	/**
	 * 会议说明
	 */
	private String summaryContent;
	/**
	 * 会议出席人
	 */
	private String meetingAttend;
	/**
	 * 会议缺席人
	 */
	private String meetingAbsent;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public MeetingSummary() {
		
	}
	
	public MeetingSummary(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取会议ID
	 * @return java.lang.Integer
	 */
	public Integer getMeetingId() {
		return this.meetingId;
	}
	
	/**
	 * 设置会议ID
	 * @param meetingId
	 * @type java.lang.Integer
	 */
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	
	/**
	 * 获取记录人
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置记录人
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取记录人名字
	 * @return java.lang.Integer
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置记录人名字
	 * @param userName
	 * @type java.lang.Integer
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取会议说明
	 * @return java.lang.String
	 */
	public String getSummaryContent() {
		return this.summaryContent;
	}
	
	/**
	 * 设置会议说明
	 * @param summaryContent
	 * @type java.lang.String
	 */
	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}
	
	/**
	 * 获取会议出席人
	 * @return java.lang.String
	 */
	public String getMeetingAttend() {
		return this.meetingAttend;
	}
	
	/**
	 * 设置会议出席人
	 * @param meetingAttend
	 * @type java.lang.String
	 */
	public void setMeetingAttend(String meetingAttend) {
		this.meetingAttend = meetingAttend;
	}
	
	/**
	 * 获取会议缺席人
	 * @return java.lang.String
	 */
	public String getMeetingAbsent() {
		return this.meetingAbsent;
	}
	
	/**
	 * 设置会议缺席人
	 * @param meetingAbsent
	 * @type java.lang.String
	 */
	public void setMeetingAbsent(String meetingAbsent) {
		this.meetingAbsent = meetingAbsent;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}