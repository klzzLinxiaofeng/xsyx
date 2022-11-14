package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjStudentGroup
 * @author AutoCreate
 *
 */
public class PjStudentGroup implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * schoolId
	 */
	private Integer schoolId;
	/**
	 * gradeId
	 */
	private Integer gradeId;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * groupId
	 */
	private Integer groupId;
	/**
	 * studentId
	 */
	private Integer studentId;
	/**
	 * isDeleted
	 */
	private Boolean isDeleted;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public PjStudentGroup() {
		
	}
	
	public PjStudentGroup(Integer id) {
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
	 * 获取schoolId
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置schoolId
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取gradeId
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置gradeId
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取groupId
	 * @return java.lang.Integer
	 */
	public Integer getGroupId() {
		return this.groupId;
	}
	
	/**
	 * 设置groupId
	 * @param groupId
	 * @type java.lang.Integer
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取studentId
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置studentId
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
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
	
}