package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * PjGroupStudent
 * @author AutoCreate
 *
 */
public class PjGroupStudent implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所属分组方案，group.id
	 */
	private Integer groupId;
	/**
	 * 分组序号，属于哪个组（组1，组2）
	 */
	private Integer groupNumber;
	/**
	 * 组员学生ID，student.id
	 */
	private Integer studentId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 组员序号
	 */
	private Integer studentNumber;
	/**
	 * 是否组长，默认false
	 */
	private Boolean isLeader;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * isDeleted
	 */
	private Boolean isDeleted;
	
	public PjGroupStudent() {
		
	}
	
	public PjGroupStudent(Integer id) {
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
	 * 获取所属分组方案，group.id
	 * @return java.lang.Integer
	 */
	public Integer getGroupId() {
		return this.groupId;
	}
	
	/**
	 * 设置所属分组方案，group.id
	 * @param groupId
	 * @type java.lang.Integer
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取分组序号，属于哪个组（组1，组2）
	 * @return java.lang.Integer
	 */
	public Integer getGroupNumber() {
		return this.groupNumber;
	}
	
	/**
	 * 设置分组序号，属于哪个组（组1，组2）
	 * @param groupNumber
	 * @type java.lang.Integer
	 */
	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	/**
	 * 获取组员学生ID，student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置组员学生ID，student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取组员序号
	 * @return java.lang.Integer
	 */
	public Integer getStudentNumber() {
		return this.studentNumber;
	}
	
	/**
	 * 设置组员序号
	 * @param studentNumber
	 * @type java.lang.Integer
	 */
	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	/**
	 * 获取是否组长，默认false
	 * @return java.lang.Boolean
	 */
	public Boolean getIsLeader() {
		return this.isLeader;
	}
	
	/**
	 * 设置是否组长，默认false
	 * @param isLeader
	 * @type java.lang.Boolean
	 */
	public void setIsLeader(Boolean isLeader) {
		this.isLeader = isLeader;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取isDeleted
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置isDeleted
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}