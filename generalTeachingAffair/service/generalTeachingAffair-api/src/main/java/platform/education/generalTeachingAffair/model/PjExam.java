package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjExam
 * @author AutoCreate
 *
 */
public class PjExam implements Model<Integer> {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 学校
	 */
	private Integer schoolId;
	/**
	 * 年级
	 */
	private Integer gradeId;
	/**
	 * 班级
	 */
	private Integer teamId;
	/**
	 * 科目
	 */
	private String subjectCode;
	/**
	 * 测试名称（可空）
	 */
	private String name;
	/**
	 * 测试类型（XY-JY-KSLX）
	 */
	private String examType;
	/**
	 * 测试轮次（默认为1）
	 */
	private Integer examRound;
	/**
	 * 测试时间（必填，默认为创建时间）
	 */
	private java.util.Date examDate;
	/**
	 * 学年
	 */
	private String schoolYear;
	/**
	 * 学校学期代码
	 */
	private String termCode;
	/**
	 * 学期国际代码
	 */
	private String termValue;
	/**
	 * 任课教师
	 */
	private Integer teacherId;
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
	/**
	 * 联考标识如果相同试卷测试在多个班级（exam）中进行则设置为相同值，说明多个exam进行的同一个考试
	 */
	private String jointExamCode;
	/**
	 * paperUuid
	 */
	private String paperUuid;
	
	/**
	 * 试卷总分
	 */
	private Float fullScorce;
	
	
	//*****业余字段
	
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	private Float score;
	
	
	
	public PjExam() {
		
	}
	
	public PjExam(Integer id) {
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
	 * 获取学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校
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
	 * 获取班级
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取科目
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取测试名称（可空）
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置测试名称（可空）
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取测试类型（XY-JY-KSLX）
	 * @return java.lang.String
	 */
	public String getExamType() {
		return this.examType;
	}
	
	/**
	 * 设置测试类型（XY-JY-KSLX）
	 * @param examType
	 * @type java.lang.String
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}
	
	/**
	 * 获取测试轮次（默认为1）
	 * @return java.lang.Integer
	 */
	public Integer getExamRound() {
		return this.examRound;
	}
	
	/**
	 * 设置测试轮次（默认为1）
	 * @param examRound
	 * @type java.lang.Integer
	 */
	public void setExamRound(Integer examRound) {
		this.examRound = examRound;
	}
	
	/**
	 * 获取测试时间（必填，默认为创建时间）
	 * @return java.util.Date
	 */
	public java.util.Date getExamDate() {
		return this.examDate;
	}
	
	/**
	 * 设置测试时间（必填，默认为创建时间）
	 * @param examDate
	 * @type java.util.Date
	 */
	public void setExamDate(java.util.Date examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * 获取学年
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学校学期代码
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学校学期代码
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取学期国际代码
	 * @return java.lang.String
	 */
	public String getTermValue() {
		return this.termValue;
	}
	
	/**
	 * 设置学期国际代码
	 * @param termValue
	 * @type java.lang.String
	 */
	public void setTermValue(String termValue) {
		this.termValue = termValue;
	}
	
	/**
	 * 获取任课教师
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置任课教师
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
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
	
	/**
	 * 获取联考标识如果相同试卷测试在多个班级（exam）中进行则设置为相同值，说明多个exam进行的同一个考试
	 * @return java.lang.String
	 */
	public String getJointExamCode() {
		return this.jointExamCode;
	}
	
	/**
	 * 设置联考标识如果相同试卷测试在多个班级（exam）中进行则设置为相同值，说明多个exam进行的同一个考试
	 * @param jointExamCode
	 * @type java.lang.String
	 */
	public void setJointExamCode(String jointExamCode) {
		this.jointExamCode = jointExamCode;
	}
	
	/**
	 * 获取paperUuid
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置paperUuid
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
	}

	public Float getFullScorce() {
		return fullScorce;
	}

	public void setFullScorce(Float fullScorce) {
		this.fullScorce = fullScorce;
	}
	
}