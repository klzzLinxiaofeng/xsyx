package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamWorksGrade
 * @author AutoCreate
 *
 */
public class ExamWorksGrade implements Model<Integer> {


	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 考务总记录 exam_works.id
	 */
	private Integer examWorksId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 年级
	 */
	private Integer gradeId;
	/**
	 * 年级统考标识用于班级成绩表（pj_exam）中识别为同一次统考
	 */
	private String jointExamCode;
	/**
	 * 需统计的科目总数
	 */
	private Integer statSubjectCount;
	/**
	 * 参与考试的班级总数
	 */
	private Integer examTeamCount;
	/**
	 * 已完成成绩录入的班级总数
	 */
	private Integer finishedTeamCount;
	/**
	 * 参与考试的学生总数
	 */
	private Integer examStudentCount;
	/**
	 * 本参与统计的总人数
	 */
	private Integer statStudentCount;
	/**
	 * 最后发布时间（近似统计时间） 默认为空
	 */
	private java.util.Date lastPublishTime;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 记录最后修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录删除标识
	 */
	private Boolean isDeleted;

	public ExamWorksGrade() {

	}

	public ExamWorksGrade(Integer id) {
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
	 * 获取年级
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}

	/**
	 * 设置年级
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * 获取年级统考标识用于班级成绩表（pj_exam）中识别为同一次统考
	 * @return java.lang.String
	 */
	public String getJointExamCode() {
		return this.jointExamCode;
	}

	/**
	 * 设置年级统考标识用于班级成绩表（pj_exam）中识别为同一次统考
	 * @param jointExamCode
	 * @type java.lang.String
	 */
	public void setJointExamCode(String jointExamCode) {
		this.jointExamCode = jointExamCode;
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
	 * 获取参与考试的班级总数
	 * @return java.lang.Integer
	 */
	public Integer getExamTeamCount() {
		return this.examTeamCount;
	}

	/**
	 * 设置参与考试的班级总数
	 * @param examTeamCount
	 * @type java.lang.Integer
	 */
	public void setExamTeamCount(Integer examTeamCount) {
		this.examTeamCount = examTeamCount;
	}

	/**
	 * 获取已完成成绩录入的班级总数
	 * @return java.lang.Integer
	 */
	public Integer getFinishedTeamCount() {
		return this.finishedTeamCount;
	}

	/**
	 * 设置已完成成绩录入的班级总数
	 * @param finishedTeamCount
	 * @type java.lang.Integer
	 */
	public void setFinishedTeamCount(Integer finishedTeamCount) {
		this.finishedTeamCount = finishedTeamCount;
	}

	/**
	 * 获取参与考试的学生总数
	 * @return java.lang.Integer
	 */
	public Integer getExamStudentCount() {
		return this.examStudentCount;
	}

	/**
	 * 设置参与考试的学生总数
	 * @param examStudentCount
	 * @type java.lang.Integer
	 */
	public void setExamStudentCount(Integer examStudentCount) {
		this.examStudentCount = examStudentCount;
	}

	/**
	 * 获取本参与统计的总人数
	 * @return java.lang.Integer
	 */
	public Integer getStatStudentCount() {
		return this.statStudentCount;
	}

	/**
	 * 设置本参与统计的总人数
	 * @param statStudentCount
	 * @type java.lang.Integer
	 */
	public void setStatStudentCount(Integer statStudentCount) {
		this.statStudentCount = statStudentCount;
	}

	/**
	 * 获取最后发布时间（近似统计时间） 默认为空
	 * @return java.util.Date
	 */
	public java.util.Date getLastPublishTime() {
		return this.lastPublishTime;
	}

	/**
	 * 设置最后发布时间（近似统计时间） 默认为空
	 * @param lastPublishTime
	 * @type java.util.Date
	 */
	public void setLastPublishTime(java.util.Date lastPublishTime) {
		this.lastPublishTime = lastPublishTime;
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
	 * 获取记录删除标识
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * 设置记录删除标识
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}