package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentMilitary
 * @author AutoCreate
 *
 */
public class StudentMilitary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 */
	private Integer teamId;
	/**
	 * 学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 */
	private Integer studentId;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 全国统一学籍号
	 */
	private String studentNumber;
	/**
	 * 班级名称
	 */
	private String teamName;
	/**
	 * 在班中的学号（顺序编号）
	 */
	private Integer number;
	/**
	 * 军训状态
	 */
	private String status;
	/**
	 * 请假天数
	 */
	private String leaveDay;
	/**
	 * 军训成绩
	 */
	private String score;
	/**
	 * 删除标记
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
	
	public StudentMilitary() {
		
	}
	
	public StudentMilitary(Integer id) {
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
	 * 获取所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置所在班级ID,根据班级id可以查找到学校，学年，年纪信息.关联表pj_team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID，根据学生id可以找到学生信息。关联表pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取学生姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置学生姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取全国统一学籍号
	 * @return java.lang.String
	 */
	public String getStudentNumber() {
		return this.studentNumber;
	}
	
	/**
	 * 设置全国统一学籍号
	 * @param studentNumber
	 * @type java.lang.String
	 */
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	/**
	 * 获取班级名称
	 * @return java.lang.String
	 */
	public String getTeamName() {
		return this.teamName;
	}
	
	/**
	 * 设置班级名称
	 * @param teamName
	 * @type java.lang.String
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * 获取在班中的学号（顺序编号）
	 * @return java.lang.Integer
	 */
	public Integer getNumber() {
		return this.number;
	}
	
	/**
	 * 设置在班中的学号（顺序编号）
	 * @param number
	 * @type java.lang.Integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * 获取军训状态
	 * @return java.lang.String
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置军训状态
	 * @param status
	 * @type java.lang.String
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取请假天数
	 * @return java.lang.String
	 */
	public String getLeaveDay() {
		return this.leaveDay;
	}
	
	/**
	 * 设置请假天数
	 * @param leaveDay
	 * @type java.lang.String
	 */
	public void setLeaveDay(String leaveDay) {
		this.leaveDay = leaveDay;
	}
	
	/**
	 * 获取军训成绩
	 * @return java.lang.String
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * 设置军训成绩
	 * @param score
	 * @type java.lang.String
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标记
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