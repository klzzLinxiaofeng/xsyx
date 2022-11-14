package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ExamTeamSubject
 * @author AutoCreate
 *
 */
public class ExamTeamSubject implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 考试日程安排表主键
	 */
	private Integer id;
	/**
	 * 考试班级id,关联pj_team.id
	 */
	private Integer teamId;
	/**
	 * 考试班级Name,关联pj_team.id
	 */
	private String teamName;
	/**
	 * 考试科目代码 关联pj_subject.code
	 */
	private String subjectCode;
	/**
	 * 考试学年
	 */
	private String schoolYear;
	/**
	 * 考试类型
	 */
	private String examType;
	/**
	 * 考试名称
	 */
	private String examName;
	/**
	 * 考试开始时间
	 */
	private java.util.Date preciseStartDate;
	/**
	 * 考试结束时间
	 */
	private java.util.Date preciseEndDate;
	/**
	 * 是否删除，0==不删除，1===删除
	 */
	private Boolean isDelete;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	private String term;//学期
	/**
	 * 任务类别，0为考试，1为作业
	 */
	private Integer taskType;
	/**
	 * 任务是否是在线作业或者考试，1===是，0===否
	 */
	private Integer taskOnline;
	
	private Integer rateType;//评分类型
	private Boolean taskRate;//是否已经评分
	private Integer schoolId;
	private String code;//考试日程代码
	private String uuid;//考试日程代码
	private Integer examNumber;//题目数
	private  Integer gradeId;
	private Integer isSuoDing;

	public Integer getIsSuoDing() {
		return isSuoDing;
	}

	public void setIsSuoDing(Integer isSuoDing) {
		this.isSuoDing = isSuoDing;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getExamNumber() {
		return examNumber;
	}

	public void setExamNumber(Integer examNumber) {
		this.examNumber = examNumber;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public Boolean getTaskRate() {
		return taskRate;
	}

	public void setTaskRate(Boolean taskRate) {
		this.taskRate = taskRate;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getTaskOnline() {
		return taskOnline;
	}

	public void setTaskOnline(Integer taskOnline) {
		this.taskOnline = taskOnline;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public ExamTeamSubject() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public ExamTeamSubject(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	

	public java.util.Date getPreciseStartDate() {
		return preciseStartDate;
	}

	public void setPreciseStartDate(java.util.Date preciseStartDate) {
		this.preciseStartDate = preciseStartDate;
	}

	public java.util.Date getPreciseEndDate() {
		return preciseEndDate;
	}

	public void setPreciseEndDate(java.util.Date preciseEndDate) {
		this.preciseEndDate = preciseEndDate;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	
}