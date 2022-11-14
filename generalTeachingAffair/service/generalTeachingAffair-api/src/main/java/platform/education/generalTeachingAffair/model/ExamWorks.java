package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamWorks
 * @author AutoCreate
 *
 */
public class ExamWorks implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 学校ID school.id
	 */
	private Integer schoolId;
	/**
	 * gradeId
	 */
	private Integer gradeId;
	/**
	 * 班级ID
	 */
	private Integer teamId;
	/**
	 * 科目CODE subject.code
	 */
	private String subjectCode;
	/**
	 * 学年 schoolYear.year
	 */
	private String schoolYear;
	/**
	 * 学校学期代码 term.code
	 */
	private String termCode;
	/**
	 * 学期国标代码 0=无，1=上，2=下
	 */
	private String termValue;
	/**
	 * 测试名称
	 */
	private String name;
	/**
	 * 是否为统考（大考）
	 */
	private Boolean isJointExam;
	/**
	 * 测试类型前面四种是统考（大考） 后面20是小考 XY-JY-KSLX 01=期中考试 02=期末考试 03=模拟考试 12=月考测试 20=班级测试
	 */
	private String examType;
	/**
	 * 考试轮次，用于月考、模拟考试 指同一学期一类测试的轮次 如第一轮模拟考，三月份月考
	 */
	private Integer examRound;
	/**
	 * 考试开始时间段
	 */
	private java.util.Date examDateBegin;
	/**
	 * 考试结束时间段
	 */
	private java.util.Date examDateEnd;
	/**
	 * 考务创建者 teacher.id
	 */
	private Integer teacherId;
	/**
	 * 是否显示真实排名 默认false
	 */
	private Boolean showRanking;
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
	
	public ExamWorks() {
		
	}
	
	public ExamWorks(Integer id) {
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
	 * 获取uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取学校ID school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校ID school.id
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
	 * 获取科目CODE subject.code
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目CODE subject.code
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取学年 schoolYear.year
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年 schoolYear.year
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学校学期代码 term.code
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学校学期代码 term.code
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取学期国标代码 0=无，1=上，2=下
	 * @return java.lang.String
	 */
	public String getTermValue() {
		return this.termValue;
	}
	
	/**
	 * 设置学期国标代码 0=无，1=上，2=下
	 * @param termValue
	 * @type java.lang.String
	 */
	public void setTermValue(String termValue) {
		this.termValue = termValue;
	}
	
	/**
	 * 获取测试名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置测试名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取是否为统考（大考）
	 * @return java.lang.Boolean
	 */
	public Boolean getIsJointExam() {
		return this.isJointExam;
	}
	
	/**
	 * 设置是否为统考（大考）
	 * @param isJointExam
	 * @type java.lang.Boolean
	 */
	public void setIsJointExam(Boolean isJointExam) {
		this.isJointExam = isJointExam;
	}
	
	/**
	 * 获取测试类型前面四种是统考（大考） 后面20是小考 XY-JY-KSLX 01=期中考试 02=期末考试 03=模拟考试 12=月考测试 20=班级测试
	 * @return java.lang.String
	 */
	public String getExamType() {
		return this.examType;
	}
	
	/**
	 * 设置测试类型前面四种是统考（大考） 后面20是小考 XY-JY-KSLX 01=期中考试 02=期末考试 03=模拟考试 12=月考测试 20=班级测试
	 * @param examType
	 * @type java.lang.String
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	/**
	 * 获取考试轮次，用于月考、模拟考试 指同一学期一类测试的轮次 如第一轮模拟考，三月份月考
	 * @return java.lang.Integer
	 */
	public Integer getExamRound() {
		return this.examRound;
	}
	
	/**
	 * 设置考试轮次，用于月考、模拟考试 指同一学期一类测试的轮次 如第一轮模拟考，三月份月考
	 * @param examRound
	 * @type java.lang.Integer
	 */
	public void setExamRound(Integer examRound) {
		this.examRound = examRound;
	}
	
	/**
	 * 获取考试开始时间段
	 * @return java.util.Date
	 */
	public java.util.Date getExamDateBegin() {
		return this.examDateBegin;
	}
	
	/**
	 * 设置考试开始时间段
	 * @param examDateBegin
	 * @type java.util.Date
	 */
	public void setExamDateBegin(java.util.Date examDateBegin) {
		this.examDateBegin = examDateBegin;
	}
	
	/**
	 * 获取考试结束时间段
	 * @return java.util.Date
	 */
	public java.util.Date getExamDateEnd() {
		return this.examDateEnd;
	}
	
	/**
	 * 设置考试结束时间段
	 * @param examDateEnd
	 * @type java.util.Date
	 */
	public void setExamDateEnd(java.util.Date examDateEnd) {
		this.examDateEnd = examDateEnd;
	}
	
	/**
	 * 获取考务创建者 teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置考务创建者 teacher.id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取是否显示真实排名 默认false
	 * @return java.lang.Boolean
	 */
	public Boolean getShowRanking() {
		return this.showRanking;
	}
	
	/**
	 * 设置是否显示真实排名 默认false
	 * @param showRanking
	 * @type java.lang.Boolean
	 */
	public void setShowRanking(Boolean showRanking) {
		this.showRanking = showRanking;
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