package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaTaskFavorites
 * @author AutoCreate
 *
 */
public class PaTaskFavorites implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * taskId
	 */
	private Integer taskId;
	/**
	 * unitId
	 */
	private Integer unitId;
	/**
	 * userId
	 */
	private Integer userId;
	/**
	 * userQuestionId
	 */
	private Integer userQuestionId;
	/**
	 * studentUserId
	 */
	private Integer studentUserId;
	/**
	 * type
	 */
	private Integer type;
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
	
	private Integer teamId;
	
	private Integer examQuestionId;
	
	public Integer getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(Integer examQuestionId) {
		this.examQuestionId = examQuestionId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public PaTaskFavorites() {
		
	}
	
	public PaTaskFavorites(Integer id) {
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
	 * 获取taskId
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置taskId
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 获取unitId
	 * @return java.lang.Integer
	 */
	public Integer getUnitId() {
		return this.unitId;
	}
	
	/**
	 * 设置unitId
	 * @param unitId
	 * @type java.lang.Integer
	 */
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	
	/**
	 * 获取userId
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置userId
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取userQuestionId
	 * @return java.lang.Integer
	 */
	public Integer getUserQuestionId() {
		return this.userQuestionId;
	}
	
	/**
	 * 设置userQuestionId
	 * @param userQuestionId
	 * @type java.lang.Integer
	 */
	public void setUserQuestionId(Integer userQuestionId) {
		this.userQuestionId = userQuestionId;
	}
	
	/**
	 * 获取studentUserId
	 * @return java.lang.Integer
	 */
	public Integer getStudentUserId() {
		return this.studentUserId;
	}
	
	/**
	 * 设置studentUserId
	 * @param studentUserId
	 * @type java.lang.Integer
	 */
	public void setStudentUserId(Integer studentUserId) {
		this.studentUserId = studentUserId;
	}
	
	/**
	 * 获取type
	 * @return java.lang.Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置type
	 * @param type
	 * @type java.lang.Integer
	 */
	public void setType(Integer type) {
		this.type = type;
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
	
}