package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjExamStudent
 * @author AutoCreate
 *
 */
public class ExamStudent implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * pj_exam.id
	 */
	private Integer examId;
	/**
	 * 学生ID
	 */
	private Integer studentId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * schoolNumber
	 */
	private String schoolNumber;
	/**
	 * 学生班内学号（顺序编号）
	 */
	private Integer number;
	/**
	 * 姓名（如果同班有同名用别名）
	 */
	private String name;
	/**
	 * 准考证号
	 */
	private String examCardNum;
	/**
	 * (最后一次)参加考试性质(JY-GB-KSXZ)
	 */
	private String testType;
	/**
	 * 成绩（最终成绩）成绩未录入设置为-1
	 */
	private Float score;
	/**
	 * 评级
	 */
	private String degree;
	/**
	 * 班级排名（在本次测试中）
	 */
	private Integer teamRank;
	/**
	 * 年级排名
	 */
	private Integer gradeRank;
	/**
	 * 原始成绩（如果有补考记录其原始成绩）
	 */
	private String sourceScore;
	/**
	 * 平均分
	 */
	private Float averageScore;
	/**
	 * 总应答题数
	 */
	private Integer answerCount;
	/**
	 * 总正答题数
	 */
	private Integer rightAnswerCount;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	/**
	 * 学生警告系数
	 */
	private Float warningFactor;
	/**
	 * 班级名次变化
	 */
	private Integer teamRankChange;
	/**
	 * 年级排名变化情况
	 */
	private Integer gradeRankChange;
	/**
	 * sumTime
	 */
	private Integer sumTime;
	
	
	
	public ExamStudent() {
		
	}
	
	public ExamStudent(Integer id) {
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
	 * 获取pj_exam.id
	 * @return java.lang.Integer
	 */
	public Integer getExamId() {
		return this.examId;
	}
	
	/**
	 * 设置pj_exam.id
	 * @param examId
	 * @type java.lang.Integer
	 */
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	/**
	 * 获取学生ID
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取用户ID
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取schoolNumber
	 * @return java.lang.String
	 */
	public String getSchoolNumber() {
		return this.schoolNumber;
	}
	
	/**
	 * 设置schoolNumber
	 * @param schoolNumber
	 * @type java.lang.String
	 */
	public void setSchoolNumber(String schoolNumber) {
		this.schoolNumber = schoolNumber;
	}
	
	/**
	 * 获取学生班内学号（顺序编号）
	 * @return java.lang.Integer
	 */
	public Integer getNumber() {
		return this.number;
	}
	
	/**
	 * 设置学生班内学号（顺序编号）
	 * @param number
	 * @type java.lang.Integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * 获取姓名（如果同班有同名用别名）
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置姓名（如果同班有同名用别名）
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取准考证号
	 * @return java.lang.String
	 */
	public String getExamCardNum() {
		return this.examCardNum;
	}
	
	/**
	 * 设置准考证号
	 * @param examCardNum
	 * @type java.lang.String
	 */
	public void setExamCardNum(String examCardNum) {
		this.examCardNum = examCardNum;
	}
	
	/**
	 * 获取(最后一次)参加考试性质(JY-GB-KSXZ)
	 * @return java.lang.String
	 */
	public String getTestType() {
		return this.testType;
	}
	
	/**
	 * 设置(最后一次)参加考试性质(JY-GB-KSXZ)
	 * @param testType
	 * @type java.lang.String
	 */
	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	/**
	 * 获取成绩（最终成绩）成绩未录入设置为-1
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置成绩（最终成绩）成绩未录入设置为-1
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取评级
	 * @return java.lang.String
	 */
	public String getDegree() {
		return this.degree;
	}
	
	/**
	 * 设置评级
	 * @param degree
	 * @type java.lang.String
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	/**
	 * 获取班级排名（在本次测试中）
	 * @return java.lang.Integer
	 */
	public Integer getTeamRank() {
		return this.teamRank;
	}
	
	/**
	 * 设置班级排名（在本次测试中）
	 * @param teamRank
	 * @type java.lang.Integer
	 */
	public void setTeamRank(Integer teamRank) {
		this.teamRank = teamRank;
	}
	
	/**
	 * 获取年级排名
	 * @return java.lang.Integer
	 */
	public Integer getGradeRank() {
		return this.gradeRank;
	}
	
	/**
	 * 设置年级排名
	 * @param gradeRank
	 * @type java.lang.Integer
	 */
	public void setGradeRank(Integer gradeRank) {
		this.gradeRank = gradeRank;
	}
	
	/**
	 * 获取原始成绩（如果有补考记录其原始成绩）
	 * @return java.lang.String
	 */
	public String getSourceScore() {
		return this.sourceScore;
	}
	
	/**
	 * 设置原始成绩（如果有补考记录其原始成绩）
	 * @param sourceScore
	 * @type java.lang.String
	 */
	public void setSourceScore(String sourceScore) {
		this.sourceScore = sourceScore;
	}
	
	/**
	 * 获取平均分
	 * @return java.lang.Float
	 */
	public Float getAverageScore() {
		return this.averageScore;
	}
	
	/**
	 * 设置平均分
	 * @param averageScore
	 * @type java.lang.Float
	 */
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}
	
	/**
	 * 获取总应答题数
	 * @return Integer
	 */
	public Integer getAnswerCount() {
		return this.answerCount;
	}
	
	/**
	 * 设置总应答题数
	 * @param answerCount
	 * @type Integer
	 */
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	
	/**
	 * 获取总正答题数
	 * @return Integer
	 */
	public Integer getRightAnswerCount() {
		return this.rightAnswerCount;
	}
	
	/**
	 * 设置总正答题数
	 * @param rightAnswerCount
	 * @type Integer
	 */
	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
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
	 * 获取学生警告系数
	 * @return java.lang.Float
	 */
	public Float getWarningFactor() {
		return this.warningFactor;
	}
	
	/**
	 * 设置学生警告系数
	 * @param warningFactor
	 * @type java.lang.Float
	 */
	public void setWarningFactor(Float warningFactor) {
		this.warningFactor = warningFactor;
	}

	public Integer getTeamRankChange() {
		return teamRankChange;
	}

	public void setTeamRankChange(Integer teamRankChange) {
		this.teamRankChange = teamRankChange;
	}

	public Integer getGradeRankChange() {
		return gradeRankChange;
	}

	public void setGradeRankChange(Integer gradeRankChange) {
		this.gradeRankChange = gradeRankChange;
	}

	
	/**
	 * 获取sumTime
	 * @return java.lang.Integer
	 */
	public Integer getSumTime() {
		return this.sumTime;
	}
	
	/**
	 * 设置sumTime
	 * @param sumTime
	 * @type java.lang.Integer
	 */
	public void setSumTime(Integer sumTime) {
		this.sumTime = sumTime;
	}
	
}