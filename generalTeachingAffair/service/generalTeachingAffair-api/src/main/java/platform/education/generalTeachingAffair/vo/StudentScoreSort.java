package platform.education.generalTeachingAffair.vo;

public class StudentScoreSort implements Comparable<Object>{

	/**
	 * id
	 */
	private Integer id;
	/**
	 * 考试日程安排表主键 pj_exam_team_subject.id
	 */
	private Integer examTeamSubjectId;
	/**
	 * 学生id，关联学生表主键 pj_student.id
	 */
	private Integer studentId;
	/**
	 * 科目代码
	 */
	private String subjectCode;
	/**
	 * 成绩
	 */
	private String score;
	/**
	 * 班级排名
	 */
	private String teamRank;
	/**
	 * 年级排名
	 */
	private String gradeRank;
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
	//判断成绩是否为0
	private boolean isScoreZero = true;
	
	

	public boolean isScoreZero() {
		return isScoreZero;
	}

	public void setScoreZero(boolean isScoreZero) {
		this.isScoreZero = isScoreZero;
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
	 * 获取考试日程安排表主键 pj_exam_team_subject.id
	 * @return java.lang.Integer
	 */
	public Integer getExamTeamSubjectId() {
		return this.examTeamSubjectId;
	}
	
	/**
	 * 设置考试日程安排表主键 pj_exam_team_subject.id
	 * @param examTeamSubjectId
	 * @type java.lang.Integer
	 */
	public void setExamTeamSubjectId(Integer examTeamSubjectId) {
		this.examTeamSubjectId = examTeamSubjectId;
	}
	
	/**
	 * 获取学生id，关联学生表主键 pj_student.id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生id，关联学生表主键 pj_student.id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取科目代码
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目代码
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取成绩
	 * @return java.lang.String
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * 设置成绩
	 * @param score
	 * @type java.lang.String
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * 获取班级排名
	 * @return java.lang.String
	 */
	public String getTeamRank() {
		return this.teamRank;
	}
	
	/**
	 * 设置班级排名
	 * @param teamRank
	 * @type java.lang.String
	 */
	public void setTeamRank(String teamRank) {
		this.teamRank = teamRank;
	}
	
	/**
	 * 获取年级排名
	 * @return java.lang.String
	 */
	public String getGradeRank() {
		return this.gradeRank;
	}
	
	/**
	 * 设置年级排名
	 * @param gradeRank
	 * @type java.lang.String
	 */
	public void setGradeRank(String gradeRank) {
		this.gradeRank = gradeRank;
	}
	
	/**
	 * 获取是否删除，0==不删除，1===删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除，0==不删除，1===删除
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
	 * 获取最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public int compareTo(Object o) {
		 StudentScoreSort  studentScoreSort = (StudentScoreSort)o;
		 return this.subjectCode.compareTo(studentScoreSort.getSubjectCode());
	}
	

}
