package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ScheduleRemind
 * @author AutoCreate
 *
 */
public class ScheduleRemind implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 相关日程ID
	 */
	private Integer scheduleId;
	/**
	 * 创建人id
	 */
	private Integer posterId;
	/**
	 * 是否启用提醒功能
	 */
	private Boolean enabled;
	/**
	 * 开始提醒时间
	 */
	private String startTime;
	/**
	 * 每次提醒间隔（分钟）
	 */
	private Integer repeatInterval;
	/**
	 * 提醒次数
	 */
	private Integer repeatTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除，0：还没删除，1：已经删除
	 */
	private Boolean isDeleted;
	
	public ScheduleRemind() {
		
	}
	
	public ScheduleRemind(Integer id) {
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
	 * 获取相关日程ID
	 * @return java.lang.Integer
	 */
	public Integer getScheduleId() {
		return this.scheduleId;
	}
	
	/**
	 * 设置相关日程ID
	 * @param scheduleId
	 * @type java.lang.Integer
	 */
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	/**
	 * 获取创建人id
	 * @return java.lang.Integer
	 */
	public Integer getPosterId() {
		return this.posterId;
	}
	
	/**
	 * 设置创建人id
	 * @param posterId
	 * @type java.lang.Integer
	 */
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	
	/**
	 * 获取是否启用提醒功能
	 * @return java.lang.Boolean
	 */
	public Boolean getEnabled() {
		return this.enabled;
	}
	
	/**
	 * 设置是否启用提醒功能
	 * @param enabled
	 * @type java.lang.Boolean
	 */
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * 获取开始提醒时间
	 * @return java.lang.String
	 */
	public String getStartTime() {
		return this.startTime;
	}
	
	/**
	 * 设置开始提醒时间
	 * @param startTime
	 * @type java.lang.String
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取每次提醒间隔（分钟）
	 * @return java.lang.Integer
	 */
	public Integer getRepeatInterval() {
		return this.repeatInterval;
	}
	
	/**
	 * 设置每次提醒间隔（分钟）
	 * @param repeatInterval
	 * @type java.lang.Integer
	 */
	public void setRepeatInterval(Integer repeatInterval) {
		this.repeatInterval = repeatInterval;
	}
	
	/**
	 * 获取提醒次数
	 * @return java.lang.Integer
	 */
	public Integer getRepeatTime() {
		return this.repeatTime;
	}
	
	/**
	 * 设置提醒次数
	 * @param repeatTime
	 * @type java.lang.Integer
	 */
	public void setRepeatTime(Integer repeatTime) {
		this.repeatTime = repeatTime;
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
	
	/**
	 * 获取是否删除，0：还没删除，1：已经删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除，0：还没删除，1：已经删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}