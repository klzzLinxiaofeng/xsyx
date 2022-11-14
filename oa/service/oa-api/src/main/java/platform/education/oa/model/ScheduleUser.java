package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ScheduleUser
 * @author AutoCreate
 *
 */
public class ScheduleUser implements Model<Integer> {
	
	
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
	 * 接收者id
	 */
	private Integer receiverId;
	/**
	 * 接收者名字
	 */
	private String receiverName;
	/**
	 * 接收者类型
	 */
	private Boolean receiverType;
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
	
	public ScheduleUser() {
		
	}
	
	public ScheduleUser(Integer id) {
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
	 * 获取接收者id
	 * @return java.lang.Integer
	 */
	public Integer getReceiverId() {
		return this.receiverId;
	}
	
	/**
	 * 设置接收者id
	 * @param receiverId
	 * @type java.lang.Integer
	 */
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 获取接收者名字
	 * @return java.lang.String
	 */
	public String getReceiverName() {
		return this.receiverName;
	}
	
	/**
	 * 设置接收者名字
	 * @param receiverName
	 * @type java.lang.String
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * 获取接收者类型
	 * @return java.lang.Boolean
	 */
	public Boolean getReceiverType() {
		return this.receiverType;
	}
	
	/**
	 * 设置接收者类型
	 * @param receiverType
	 * @type java.lang.Boolean
	 */
	public void setReceiverType(Boolean receiverType) {
		this.receiverType = receiverType;
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