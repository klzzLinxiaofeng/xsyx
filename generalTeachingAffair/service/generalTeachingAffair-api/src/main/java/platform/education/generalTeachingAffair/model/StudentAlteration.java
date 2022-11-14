package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentAlteration
 * @author AutoCreate
 *
 */
public class StudentAlteration implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 学生ID
	 */
	private Integer studentId;
	/**
	 * 班级ID
	 */
	private Integer teamId;
	/**
	 * 变动类型
	 */
	private String alterType;
	/**
	 * 变动时间
	 */
	private java.util.Date alterDate;
	/**
	 * 变动操作所在的学年
	 */
	private String schoolYear;
	/**
	 * 变动前（学校、班级等）
	 */
	private String alterFrom;
	/**
	 * 变动后（学校、班级等）
	 */
	private String alterTo;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 删除标志
	 */
	private Boolean isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	public StudentAlteration() {
		
	}
	
	public StudentAlteration(Integer id) {
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
	 * 获取学校ID
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校ID
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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
	 * 获取班级ID
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级ID
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取变动类型
	 * @return java.lang.String
	 */
	public String getAlterType() {
		return this.alterType;
	}
	
	/**
	 * 设置变动类型
	 * @param alterType
	 * @type java.lang.String
	 */
	public void setAlterType(String alterType) {
		this.alterType = alterType;
	}
	
	/**
	 * 获取变动时间
	 * @return java.util.Date
	 */
	public java.util.Date getAlterDate() {
		return this.alterDate;
	}
	
	/**
	 * 设置变动时间
	 * @param alterDate
	 * @type java.util.Date
	 */
	public void setAlterDate(java.util.Date alterDate) {
		this.alterDate = alterDate;
	}
	
	/**
	 * 获取变动操作所在的学年
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置变动操作所在的学年
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取变动前（学校、班级等）
	 * @return java.lang.String
	 */
	public String getAlterFrom() {
		return this.alterFrom;
	}
	
	/**
	 * 设置变动前（学校、班级等）
	 * @param alterFrom
	 * @type java.lang.String
	 */
	public void setAlterFrom(String alterFrom) {
		this.alterFrom = alterFrom;
	}
	
	/**
	 * 获取变动后（学校、班级等）
	 * @return java.lang.String
	 */
	public String getAlterTo() {
		return this.alterTo;
	}
	
	/**
	 * 设置变动后（学校、班级等）
	 * @param alterTo
	 * @type java.lang.String
	 */
	public void setAlterTo(String alterTo) {
		this.alterTo = alterTo;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * 设置备注
	 * @param comment
	 * @type java.lang.String
	 */
	public void setComment(String comment) {
		this.comment = comment;
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