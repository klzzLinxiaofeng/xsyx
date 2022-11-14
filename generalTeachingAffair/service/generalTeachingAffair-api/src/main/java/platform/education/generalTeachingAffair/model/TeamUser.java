package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * TeamUser
 * @author AutoCreate
 *
 */
public class TeamUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 所在班级
	 */
	private Integer teamId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 是否班主任
	 */
	private Boolean isMaster;
	/**
	 * 是否任课教师
	 */
	private Boolean isTeacher;
	/**
	 * 是否学生
	 */
	private Boolean isStudent;
	/**
	 * 是否家长
	 */
	private Boolean isParent;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	private boolean isDeleted;
	
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public TeamUser() {
		
	}
	
	public TeamUser(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在班级
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置所在班级
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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
	 * 获取是否班主任
	 * @return java.lang.Boolean
	 */
	public Boolean getIsMaster() {
		return this.isMaster;
	}
	
	/**
	 * 设置是否班主任
	 * @param isMaster
	 * @type java.lang.Boolean
	 */
	public void setIsMaster(Boolean isMaster) {
		this.isMaster = isMaster;
	}
	
	/**
	 * 获取是否任课教师
	 * @return java.lang.Boolean
	 */
	public Boolean getIsTeacher() {
		return this.isTeacher;
	}
	
	/**
	 * 设置是否任课教师
	 * @param isTeacher
	 * @type java.lang.Boolean
	 */
	public void setIsTeacher(Boolean isTeacher) {
		this.isTeacher = isTeacher;
	}
	
	/**
	 * 获取是否学生
	 * @return java.lang.Boolean
	 */
	public Boolean getIsStudent() {
		return this.isStudent;
	}
	
	/**
	 * 设置是否学生
	 * @param isStudent
	 * @type java.lang.Boolean
	 */
	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}
	
	/**
	 * 获取是否家长
	 * @return java.lang.Boolean
	 */
	public Boolean getIsParent() {
		return this.isParent;
	}
	
	/**
	 * 设置是否家长
	 * @param isParent
	 * @type java.lang.Boolean
	 */
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	/**
	 * 获取姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取性别
	 * @return java.lang.String
	 */
	public String getSex() {
		return this.sex;
	}
	
	/**
	 * 设置性别
	 * @param sex
	 * @type java.lang.String
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}