package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * VirtualTeamStudent
 * @author AutoCreate
 *
 */
public class VirtualTeamStudent implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所在年级ID
	 */
	private Integer gradeId;
	/**
	 * 所在班级ID
	 */
	private Integer virtualTeamId;
	/**
	 * 学生ID
	 */
	private Integer studentId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 迁入/迁出状态
	 */
	private Boolean inState;
	/**
	 * 删除标识
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public VirtualTeamStudent() {
		
	}
	
	public VirtualTeamStudent(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在年级ID
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置所在年级ID
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取所在班级ID
	 * @return java.lang.Integer
	 */
	public Integer getVirtualTeamId() {
		return this.virtualTeamId;
	}
	
	/**
	 * 设置所在班级ID
	 * @param virtualTeamId
	 * @type java.lang.Integer
	 */
	public void setVirtualTeamId(Integer virtualTeamId) {
		this.virtualTeamId = virtualTeamId;
	}
	
	/**
	 * 获取学生ID
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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
	 * 获取迁入/迁出状态
	 * @return java.lang.Boolean
	 */
	public Boolean getInState() {
		return this.inState;
	}
	
	/**
	 * 设置迁入/迁出状态
	 * @param inState
	 * @type java.lang.Boolean
	 */
	public void setInState(Boolean inState) {
		this.inState = inState;
	}
	
	/**
	 * 获取删除标识
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标识
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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