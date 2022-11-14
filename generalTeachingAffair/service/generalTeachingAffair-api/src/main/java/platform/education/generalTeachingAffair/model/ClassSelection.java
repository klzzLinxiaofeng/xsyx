package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ClassSelection
 * @author AutoCreate
 *
 */
public class ClassSelection implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 学生选课ID
	 */
	private Integer id;
	/**
	 * 所在学校 pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 课程ID pj_public_class.id
	 */
	private Integer publicClassId;
	/**
	 * 所在班级 pj_team.id
	 */
	private Integer teamId;
	/**
	 * 学生ID pj_student.id
	 */
	private Integer studentId;
	/**
	 * 作废标记
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
	
	public ClassSelection() {
		
	}
	
	public ClassSelection(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取学生选课ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置学生选课ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在学校 pj_school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校 pj_school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取课程ID pj_public_class.id
	 * @return java.lang.Integer
	 */
	public Integer getPublicClassId() {
		return this.publicClassId;
	}
	
	/**
	 * 设置课程ID pj_public_class.id
	 * @param publicClassId
	 * @type java.lang.Integer
	 */
	public void setPublicClassId(Integer publicClassId) {
		this.publicClassId = publicClassId;
	}
	
	/**
	 * 获取所在班级 pj_team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置所在班级 pj_team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取学生ID pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取作废标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置作废标记
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