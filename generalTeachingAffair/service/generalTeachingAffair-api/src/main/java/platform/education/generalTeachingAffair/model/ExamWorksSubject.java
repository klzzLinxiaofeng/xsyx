package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamWorksSubject
 * @author AutoCreate
 *
 */
public class ExamWorksSubject implements Model<Integer> {
	
	
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
	 * 使用的学校，如果为0表示系统缺省模板
	 */
	private Integer schoolId;
	/**
	 * 年级id grade.id
	 */
	private Integer gradeId;
	/**
	 * 科目CODE subject.code
	 */
	private String subjectCode;
	/**
	 * 是否纳入综合统计的科目
	 */
	private Boolean statNeeded;
	/**
	 * 满分分数
	 */
	private Float fullScore;
	/**
	 * 高分/优秀 分数
	 */
	private Float highScore;
	/**
	 * 低分/良好 分数
	 */
	private Float lowScore;
	/**
	 * 及格分数
	 */
	private Float passScore;
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
	private Boolean isDelteted;
	
	public ExamWorksSubject() {
		
	}
	
	public ExamWorksSubject(Integer id) {
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
	 * 获取使用的学校，如果为0表示系统缺省模板
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置使用的学校，如果为0表示系统缺省模板
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取年级id grade.id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置年级id grade.id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
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
	 * 获取是否纳入综合统计的科目
	 * @return java.lang.Boolean
	 */
	public Boolean getStatNeeded() {
		return this.statNeeded;
	}
	
	/**
	 * 设置是否纳入综合统计的科目
	 * @param statNeeded
	 * @type java.lang.Boolean
	 */
	public void setStatNeeded(Boolean statNeeded) {
		this.statNeeded = statNeeded;
	}
	
	/**
	 * 获取满分分数
	 * @return java.lang.Float
	 */
	public Float getFullScore() {
		return this.fullScore;
	}
	
	/**
	 * 设置满分分数
	 * @param fullScore
	 * @type java.lang.Float
	 */
	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}
	
	/**
	 * 获取高分/优秀 分数
	 * @return java.lang.Float
	 */
	public Float getHighScore() {
		return this.highScore;
	}
	
	/**
	 * 设置高分/优秀 分数
	 * @param highScore
	 * @type java.lang.Float
	 */
	public void setHighScore(Float highScore) {
		this.highScore = highScore;
	}
	
	/**
	 * 获取低分/良好 分数
	 * @return java.lang.Float
	 */
	public Float getLowScore() {
		return this.lowScore;
	}
	
	/**
	 * 设置低分/良好 分数
	 * @param lowScore
	 * @type java.lang.Float
	 */
	public void setLowScore(Float lowScore) {
		this.lowScore = lowScore;
	}
	
	/**
	 * 获取及格分数
	 * @return java.lang.Float
	 */
	public Float getPassScore() {
		return this.passScore;
	}
	
	/**
	 * 设置及格分数
	 * @param passScore
	 * @type java.lang.Float
	 */
	public void setPassScore(Float passScore) {
		this.passScore = passScore;
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
	public Boolean getIsDelteted() {
		return this.isDelteted;
	}
	
	/**
	 * 设置记录删除标志
	 * @param isDelteted
	 * @type java.lang.Boolean
	 */
	public void setIsDelteted(Boolean isDelteted) {
		this.isDelteted = isDelteted;
	}
	
}