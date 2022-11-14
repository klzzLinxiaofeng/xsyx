package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamWorksTeam
 * @author AutoCreate
 *
 */
public class ExamWorksTeam implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 考务总记录 exam_works_id
	 */
	private Integer examWorksId;
	/**
	 * 年级统考记录的ID 如小考值为空 exam_works_grade.id
	 */
	private Integer examWorksGradeId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 考试的年级 grade.id
	 */
	private Integer gradeId;
	/**
	 * 考试的班级 team.id
	 */
	private Integer teamId;
	/**
	 * 需统计的科目总数
	 */
	private Integer statSubjectCount;
	/**
	 * 已完成成绩录入的科目总数
	 */
	private Integer finishedSubjectCount;
	/**
	 * 成绩发布人员 teacher.id
	 */
	private Integer publishTeacherId;
	/**
	 * 成绩发布试卷（如班级成绩统一发布） 默认为空
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
	 * 记录删除标记
	 */
	private Boolean isDeleted;
	
	public ExamWorksTeam() {
		
	}
	
	public ExamWorksTeam(Integer id) {
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
	 * 获取考务总记录 exam_works_id
	 * @return java.lang.Integer
	 */
	public Integer getExamWorksId() {
		return this.examWorksId;
	}
	
	/**
	 * 设置考务总记录 exam_works_id
	 * @param examWorksId
	 * @type java.lang.Integer
	 */
	public void setExamWorksId(Integer examWorksId) {
		this.examWorksId = examWorksId;
	}
	
	/**
	 * 获取年级统考记录的ID 如小考值为空 exam_works_grade.id
	 * @return java.lang.Integer
	 */
	public Integer getExamWorksGradeId() {
		return this.examWorksGradeId;
	}
	
	/**
	 * 设置年级统考记录的ID 如小考值为空 exam_works_grade.id
	 * @param examWorksGradeId
	 * @type java.lang.Integer
	 */
	public void setExamWorksGradeId(Integer examWorksGradeId) {
		this.examWorksGradeId = examWorksGradeId;
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
	 * 获取考试的年级 grade.id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置考试的年级 grade.id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取考试的班级 team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置考试的班级 team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取需统计的科目总数
	 * @return java.lang.Integer
	 */
	public Integer getStatSubjectCount() {
		return this.statSubjectCount;
	}
	
	/**
	 * 设置需统计的科目总数
	 * @param statSubjectCount
	 * @type java.lang.Integer
	 */
	public void setStatSubjectCount(Integer statSubjectCount) {
		this.statSubjectCount = statSubjectCount;
	}
	
	/**
	 * 获取已完成成绩录入的科目总数
	 * @return java.lang.Integer
	 */
	public Integer getFinishedSubjectCount() {
		return this.finishedSubjectCount;
	}
	
	/**
	 * 设置已完成成绩录入的科目总数
	 * @param finishedSubjectCount
	 * @type java.lang.Integer
	 */
	public void setFinishedSubjectCount(Integer finishedSubjectCount) {
		this.finishedSubjectCount = finishedSubjectCount;
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
	 * 获取成绩发布试卷（如班级成绩统一发布） 默认为空
	 * @return java.util.Date
	 */
	public java.util.Date getPublishTime() {
		return this.publishTime;
	}
	
	/**
	 * 设置成绩发布试卷（如班级成绩统一发布） 默认为空
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
	 * 获取记录删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标记
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}