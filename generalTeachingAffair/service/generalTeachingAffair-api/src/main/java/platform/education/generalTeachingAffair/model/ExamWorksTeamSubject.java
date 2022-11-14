package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamWorksTeamSubject
 * @author AutoCreate
 *
 */
public class ExamWorksTeamSubject implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 考务总记录 exam_works.id
	 */
	private Integer examWorksId;
	/**
	 * 年级统考记录的ID 如小考值为空
	 */
	private Integer examWorksGradeId;
	/**
	 * 对应考试成绩记录总表记录
	 */
	private Integer examId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 考试的年级
	 */
	private Integer gradeId;
	/**
	 * 考试的班级
	 */
	private Integer teamId;
	/**
	 * 考试科目
	 */
	private String subjectCode;
	/**
	 * 本科任课教师 teacher.id
	 */
	private Integer teacherId;
	/**
	 * 成绩录入人员
	 */
	private Integer postTeacherId;
	/**
	 * 成绩录入时间 默认为空
	 */
	private java.util.Date postTime;
	/**
	 * 成绩发布人员 teacher.id
	 */
	private Integer publishTeacherId;
	/**
	 * 成绩发布试卷 默认为空
	 */
	private java.util.Date publishTime;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 记录最后修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录删除标志
	 */
	private Boolean isDeleted;
	
	public ExamWorksTeamSubject() {
		
	}
	
	public ExamWorksTeamSubject(Integer id) {
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
	 * 获取考务总记录 exam_works.id
	 * @return java.lang.Integer
	 */
	public Integer getExamWorksId() {
		return this.examWorksId;
	}
	
	/**
	 * 设置考务总记录 exam_works.id
	 * @param examWorksId
	 * @type java.lang.Integer
	 */
	public void setExamWorksId(Integer examWorksId) {
		this.examWorksId = examWorksId;
	}
	
	/**
	 * 获取年级统考记录的ID 如小考值为空
	 * @return java.lang.Integer
	 */
	public Integer getExamWorksGradeId() {
		return this.examWorksGradeId;
	}
	
	/**
	 * 设置年级统考记录的ID 如小考值为空
	 * @param examWorksGradeId
	 * @type java.lang.Integer
	 */
	public void setExamWorksGradeId(Integer examWorksGradeId) {
		this.examWorksGradeId = examWorksGradeId;
	}
	
	/**
	 * 获取对应考试成绩记录总表记录
	 * @return java.lang.Integer
	 */
	public Integer getExamId() {
		return this.examId;
	}
	
	/**
	 * 设置对应考试成绩记录总表记录
	 * @param examId
	 * @type java.lang.Integer
	 */
	public void setExamId(Integer examId) {
		this.examId = examId;
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
	 * 获取考试的年级
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置考试的年级
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取考试的班级
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置考试的班级
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取考试科目
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置考试科目
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取本科任课教师 teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置本科任课教师 teacher.id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取成绩录入人员
	 * @return java.lang.Integer
	 */
	public Integer getPostTeacherId() {
		return this.postTeacherId;
	}
	
	/**
	 * 设置成绩录入人员
	 * @param postTeacherId
	 * @type java.lang.Integer
	 */
	public void setPostTeacherId(Integer postTeacherId) {
		this.postTeacherId = postTeacherId;
	}
	
	/**
	 * 获取成绩录入时间 默认为空
	 * @return java.util.Date
	 */
	public java.util.Date getPostTime() {
		return this.postTime;
	}
	
	/**
	 * 设置成绩录入时间 默认为空
	 * @param postTime
	 * @type java.util.Date
	 */
	public void setPostTime(java.util.Date postTime) {
		this.postTime = postTime;
	}
	
	/**
	 * 获取成绩发布人员 teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getPublishTeacherId() {
		return this.publishTeacherId;
	}
	
	/**
	 * 设置成绩发布人员 teacher.id
	 * @param publishTeacherId
	 * @type java.lang.Integer
	 */
	public void setPublishTeacherId(Integer publishTeacherId) {
		this.publishTeacherId = publishTeacherId;
	}
	
	/**
	 * 获取成绩发布试卷 默认为空
	 * @return java.util.Date
	 */
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	/**
	 * 设置成绩发布试卷 默认为空
	 * @param publishTime
	 * @type java.util.Date
	 */
	public void setPublishTime(java.util.Date publishTime) {
		this.publishTime = publishTime;
	}
	
	/**
	 * 获取记录创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置记录创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取记录最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置记录最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取记录删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}