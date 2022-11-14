package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * MeetingUser
 * @author AutoCreate
 *
 */
public class MeetingUser implements Model<Integer> {
	
	
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
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 参会状态,0：通知，1：参会，2：不参加
	 */
	private Integer isCanhui;
	/**
	 * 老师名字
	 */
	private String userName;
	/**
	 * 参会意见
	 */
	private String canhuiInfo;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	//=======新版公告添加的部门ID
	private Integer departmentId;
	private String departmentName;
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	//=======新版公告添加的部门ID
	
	public MeetingUser() {
		
	}
	
	public MeetingUser(Integer id) {
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
	 * 获取用户ID
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取参会状态,0：通知，1：参会，2：不参加
	 * @return java.lang.Integer
	 */
	public Integer getIsCanhui() {
		return this.isCanhui;
	}
	
	/**
	 * 设置参会状态,0：通知，1：参会，2：不参加
	 * @param isCanhui
	 * @type java.lang.Integer
	 */
	public void setIsCanhui(Integer isCanhui) {
		this.isCanhui = isCanhui;
	}
	
	/**
	 * 获取老师名字
	 * @return java.lang.String
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置老师名字
	 * @param userName
	 * @type java.lang.String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * 获取参会意见
	 * @return java.lang.String
	 */
	public String getCanhuiInfo() {
		return this.canhuiInfo;
	}
	
	/**
	 * 设置参会意见
	 * @param canhuiInfo
	 * @type java.lang.String
	 */
	public void setCanhuiInfo(String canhuiInfo) {
		this.canhuiInfo = canhuiInfo;
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