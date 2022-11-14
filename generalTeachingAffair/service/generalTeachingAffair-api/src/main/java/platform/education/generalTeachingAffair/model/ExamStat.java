package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ExamStat
 * @author AutoCreate
 *
 */
public class ExamStat implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的考试
	 */
	private Integer examId;
	/**
	 * 实际应试人数
	 */
	private Integer studentCount;
	/**
	 * 问题总数
	 */
	private Integer questionCount;
	/**
	 * 全班总分
	 */
	private Float totalScore;
	/**
	 * 全班平均分
	 */
	private Float averageScore;
	/**
	 * 全班标准差
	 */
	private Float sdScore;
	/**
	 * 离差
	 */
	private Float madValue;
	/**
	 * 极差
	 */
	private Float movValue;
	/**
	 * 全班总分在年级名次
	 */
	private Integer gradeRank;
	/**
	 * 满分分数
	 */
	private Float fullScore;
	/**
	 * 高分/优秀  分数
	 */
	private Float highScore;
	/**
	 * 低分/良好  分数
	 */
	private Float lowScore;
	/**
	 * 及格分数
	 */
	private Float passScore;
	/**
	 * 高分人数
	 */
	private Integer highCount;
	/**
	 * 低分人数
	 */
	private Integer lowCount;
	/**
	 * 及格人数
	 */
	private Integer passCount;
	/**
	 * 最后统计时间
	 */
	private java.util.Date statTime;
	/**
	 * 数据更改标（默认false）一旦 exam_student 数据发生变化时，此值设为true，直到重新执行统计后才设置为false
	 */
	private Boolean dataChanged;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 全部最高分
	 */
	private Float highestScore;
	/**
	 * 全班最低分
	 */
	private Float lowestScore;
	
	public ExamStat() {
		
	}
	
	public ExamStat(Integer id) {
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
	 * 获取对应的考试
	 * @return java.lang.Integer
	 */
	public Integer getExamId() {
		return this.examId;
	}
	
	/**
	 * 设置对应的考试
	 * @param examId
	 * @type java.lang.Integer
	 */
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	/**
	 * 获取实际应试人数
	 * @return java.lang.Integer
	 */
	public Integer getStudentCount() {
		return this.studentCount;
	}
	
	/**
	 * 设置实际应试人数
	 * @param studentCount
	 * @type java.lang.Integer
	 */
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	
	/**
	 * 获取问题总数
	 * @return java.lang.Integer
	 */
	public Integer getQuestionCount() {
		return this.questionCount;
	}
	
	/**
	 * 设置问题总数
	 * @param questionCount
	 * @type java.lang.Integer
	 */
	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
	
	/**
	 * 获取全班总分
	 * @return java.lang.Float
	 */
	public Float getTotalScore() {
		return this.totalScore;
	}
	
	/**
	 * 设置全班总分
	 * @param totalScore
	 * @type java.lang.Float
	 */
	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
	
	/**
	 * 获取全班平均分
	 * @return java.lang.Float
	 */
	public Float getAverageScore() {
		return this.averageScore;
	}
	
	/**
	 * 设置全班平均分
	 * @param averageScore
	 * @type java.lang.Float
	 */
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}
	
	/**
	 * 获取全班标准差
	 * @return java.lang.Float
	 */
	public Float getSdScore() {
		return this.sdScore;
	}
	
	/**
	 * 设置全班标准差
	 * @param sdScore
	 * @type java.lang.Float
	 */
	public void setSdScore(Float sdScore) {
		this.sdScore = sdScore;
	}
	
	/**
	 * 获取离差
	 * @return java.lang.Float
	 */
	public Float getMadValue() {
		return this.madValue;
	}
	
	/**
	 * 设置离差
	 * @param madValue
	 * @type java.lang.Float
	 */
	public void setMadValue(Float madValue) {
		this.madValue = madValue;
	}
	
	/**
	 * 获取极差
	 * @return java.lang.Float
	 */
	public Float getMovValue() {
		return this.movValue;
	}
	
	/**
	 * 设置极差
	 * @param movValue
	 * @type java.lang.Float
	 */
	public void setMovValue(Float movValue) {
		this.movValue = movValue;
	}
	
	/**
	 * 获取全班总分在年级名次
	 * @return java.lang.Integer
	 */
	public Integer getGradeRank() {
		return this.gradeRank;
	}
	
	/**
	 * 设置全班总分在年级名次
	 * @param gradeRank
	 * @type java.lang.Integer
	 */
	public void setGradeRank(Integer gradeRank) {
		this.gradeRank = gradeRank;
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
	 * 获取高分/优秀  分数
	 * @return java.lang.Float
	 */
	public Float getHighScore() {
		return this.highScore;
	}
	
	/**
	 * 设置高分/优秀  分数
	 * @param highScore
	 * @type java.lang.Float
	 */
	public void setHighScore(Float highScore) {
		this.highScore = highScore;
	}
	
	/**
	 * 获取低分/良好  分数
	 * @return java.lang.Float
	 */
	public Float getLowScore() {
		return this.lowScore;
	}
	
	/**
	 * 设置低分/良好  分数
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
	 * 获取高分人数
	 * @return java.lang.Integer
	 */
	public Integer getHighCount() {
		return this.highCount;
	}
	
	/**
	 * 设置高分人数
	 * @param highCount
	 * @type java.lang.Integer
	 */
	public void setHighCount(Integer highCount) {
		this.highCount = highCount;
	}
	
	/**
	 * 获取低分人数
	 * @return java.lang.Integer
	 */
	public Integer getLowCount() {
		return this.lowCount;
	}
	
	/**
	 * 设置低分人数
	 * @param lowCount
	 * @type java.lang.Integer
	 */
	public void setLowCount(Integer lowCount) {
		this.lowCount = lowCount;
	}
	
	/**
	 * 获取及格人数
	 * @return java.lang.Integer
	 */
	public Integer getPassCount() {
		return this.passCount;
	}
	
	/**
	 * 设置及格人数
	 * @param passCount
	 * @type java.lang.Integer
	 */
	public void setPassCount(Integer passCount) {
		this.passCount = passCount;
	}
	
	/**
	 * 获取最后统计时间
	 * @return java.util.Date
	 */
	public java.util.Date getStatTime() {
		return this.statTime;
	}
	
	/**
	 * 设置最后统计时间
	 * @param statTime
	 * @type java.util.Date
	 */
	public void setStatTime(java.util.Date statTime) {
		this.statTime = statTime;
	}
	
	/**
	 * 获取数据更改标（默认false）一旦 exam_student 数据发生变化时，此值设为true，直到重新执行统计后才设置为false
	 * @return java.lang.Boolean
	 */
	public Boolean getDataChanged() {
		return this.dataChanged;
	}
	
	/**
	 * 设置数据更改标（默认false）一旦 exam_student 数据发生变化时，此值设为true，直到重新执行统计后才设置为false
	 * @param dataChanged
	 * @type java.lang.Boolean
	 */
	public void setDataChanged(Boolean dataChanged) {
		this.dataChanged = dataChanged;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取全部最高分
	 * @return java.lang.Float
	 */
	public Float getHighestScore() {
		return this.highestScore;
	}
	
	/**
	 * 设置全部最高分
	 * @param highestScore
	 * @type java.lang.Float
	 */
	public void setHighestScore(Float highestScore) {
		this.highestScore = highestScore;
	}
	
	/**
	 * 获取全班最低分
	 * @return java.lang.Float
	 */
	public Float getLowestScore() {
		return this.lowestScore;
	}
	
	/**
	 * 设置全班最低分
	 * @param lowestScore
	 * @type java.lang.Float
	 */
	public void setLowestScore(Float lowestScore) {
		this.lowestScore = lowestScore;
	}
	
}