package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * TeamTeacher
 * @author AutoCreate
 *
 */
public class TeamTeacher implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所在年级
	 */
	private Integer gradeId;
	/**
	 * 所在班级
	 */
	private Integer teamId;
	/**
	 * 所在班级名称
	 */
	private String teamName;
	/**
	 * 教师ID
	 */
	private Integer teacherId;
	/**
	 * 担任科目
	 */
	private String subjectCode;
	

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 教师在在班中的任职类型
	 */
	private Integer type;
	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 担任班级教师所处的学年，与年级的学年相
	 */
	private String schoolYear;
	/**
	 * 加入时间
	 */
	private java.util.Date joinDate;
	/**
	 * 结束时间
	 */
	private java.util.Date finishDate;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 删除标识
	 */
	private boolean isDelete;
	
	/**
	 * 用户ID（2015-12-10）
	 */
	private Integer userId;
	
	/**
	 * 职务，班主任值为【班主任】，任课教师值为【subject教师】（2015-12-10）
	 */
	private String position;

	
	public TeamTeacher() {
		
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public TeamTeacher(Integer id) {
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
	 * 获取所在年级
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置所在年级
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
	 * 获取教师ID
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置教师ID
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取担任科目
	 * @return java.lang.Integer
	 */
	
	public String getSubjectCode() {
		return subjectCode;
	}

	
	/**
	 * 设置担任科目
	 * @param subjectId
	 * @type java.lang.Integer
	 */
	
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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
	 * 获取教师在在班中的任职类型
	 * @return java.lang.Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置教师在在班中的任职类型
	 * @param type
	 * @type java.lang.Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取科目名称
	 * @return java.lang.String
	 */
	public String getSubjectName() {
		return this.subjectName;
	}
	
	/**
	 * 设置科目名称
	 * @param subjectName
	 * @type java.lang.String
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * 获取担任班级教师所处的学年，与年级的学年相
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置担任班级教师所处的学年，与年级的学年相
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取加入时间
	 * @return java.util.Date
	 */
	public java.util.Date getJoinDate() {
		return this.joinDate;
	}
	
	/**
	 * 设置加入时间
	 * @param joinDate
	 * @type java.util.Date
	 */
	public void setJoinDate(java.util.Date joinDate) {
		this.joinDate = joinDate;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置结束时间
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
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
	
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}