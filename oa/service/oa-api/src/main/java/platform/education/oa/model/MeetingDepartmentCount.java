package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * MeetingDepartmentCount
 * @author AutoCreate
 *
 */
public class MeetingDepartmentCount implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 该部门下的会议数
	 */
	private Integer meetingCount;
	/**
	 * 单位ID
	 */
	private Integer ownerId;
	/**
	 * 单位类型
	 */
	private Integer ownerType;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标志
	 */
	private Boolean isDelete;
	
	public MeetingDepartmentCount() {
		
	}
	
	public MeetingDepartmentCount(Integer id) {
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
	 * 获取部门ID
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置部门ID
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取该部门下的会议数
	 * @return java.lang.Integer
	 */
	public Integer getMeetingCount() {
		return this.meetingCount;
	}
	
	/**
	 * 设置该部门下的会议数
	 * @param meetingCount
	 * @type java.lang.Integer
	 */
	public void setMeetingCount(Integer meetingCount) {
		this.meetingCount = meetingCount;
	}
	
	/**
	 * 获取单位ID
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置单位ID
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取单位类型
	 * @return java.lang.Integer
	 */
	public Integer getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置单位类型
	 * @param ownerType
	 * @type java.lang.Integer
	 */
	public void setOwnerType(Integer ownerType) {
		this.ownerType = ownerType;
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
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标志
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}