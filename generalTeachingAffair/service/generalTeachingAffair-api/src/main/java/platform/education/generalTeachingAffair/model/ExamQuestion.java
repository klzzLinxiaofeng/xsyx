package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * ExamQuestion
 * @author AutoCreate
 *
 */
public class ExamQuestion implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * examId
	 */
	private Integer examId;
	/**
	 * 题型
	 */
	private String questionType;
	/**
	 * 总应答人数（本班），包含有作答和不作答的
	 */
	private Integer answerCount;
	/**
	 * 不作答人数（本班）
	 */
	private Integer emptyCount;
	/**
	 * 总正答人数（本班）
	 */
	private Integer rightAnswerCount;
	/**
	 * 题目总分（本班所有答此题的总得分）
	 */
	private Double score;
	/**
	 * 平均分（score/answer_count）
	 */
	private Float averageScore;
	/**
	 * 班级排名（在本次测试中）
	 */
	private Integer teamRank;
	/**
	 * 年级排名（在本次测试中）
	 */
	private Integer gradeRank;
	/**
	 * 题目警告系数
	 */
	private Float warningFactor;
	/**
	 * 总答题时间（单位秒、本班）
	 */
	private Integer totalTime;
	/**
	 * 平均答题时间（单位秒、本班）
	 */
	private Integer averageTime;
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
	/**
	 * pa_question表 uuid
	 */
	private String questionUuid;
	/**
	 * 科目
	 */
	private String subjectCode;
	/**
	 * 对应学科知识树的节点 jc_knowledge.id
	 */
	private Integer knowledgeId;
	/**
	 * 难易度
	 */
	private Float difficulity;
	/**
	 * 认知度 1=识计，2=理解，3=应用，4=探究，5=综合
	 */
	private String cognition;
	/**
	 * 题目标准得分（完全答对得分） question.score 
	 */
	private Float fullScore;
	/**
	 * 班级得分率eq.score /（eq.answer_count * eq.full_score）
	 */
	private Float teamScoringRate;
	/**
	 * 年级得分率（sum(eq.score) / (sum(eq.answer_count) * eq.full_score)）
	 */
	private Float gradeScoringRate;
	
	public ExamQuestion() {
		
	}
	
	public ExamQuestion(Integer id) {
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
	 * 获取examId
	 * @return java.lang.Integer
	 */
	public Integer getExamId() {
		return this.examId;
	}
	
	/**
	 * 设置examId
	 * @param examId
	 * @type java.lang.Integer
	 */
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	/**
	 * 获取题型
	 * @return java.lang.String
	 */
	public String getQuestionType() {
		return this.questionType;
	}
	
	/**
	 * 设置题型
	 * @param questionType
	 * @type java.lang.String
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	/**
	 * 获取总应答人数（本班），包含有作答和不作答的
	 * @return java.lang.Integer
	 */
	public Integer getAnswerCount() {
		return this.answerCount;
	}
	
	/**
	 * 设置总应答人数（本班），包含有作答和不作答的
	 * @param answerCount
	 * @type java.lang.Integer
	 */
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	
	/**
	 * 获取不作答人数（本班）
	 * @return java.lang.Integer
	 */
	public Integer getEmptyCount() {
		return this.emptyCount;
	}
	
	/**
	 * 设置不作答人数（本班）
	 * @param emptyCount
	 * @type java.lang.Integer
	 */
	public void setEmptyCount(Integer emptyCount) {
		this.emptyCount = emptyCount;
	}
	
	/**
	 * 获取总正答人数（本班）
	 * @return java.lang.Integer
	 */
	public Integer getRightAnswerCount() {
		return this.rightAnswerCount;
	}
	
	/**
	 * 设置总正答人数（本班）
	 * @param rightAnswerCount
	 * @type java.lang.Integer
	 */
	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}
	
	/**
	 * 获取题目总分（本班所有答此题的总得分）
	 * @return java.lang.Float
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置题目总分（本班所有答此题的总得分）
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取平均分（score/answer_count）
	 * @return java.lang.Float
	 */
	public Float getAverageScore() {
		return this.averageScore;
	}
	
	/**
	 * 设置平均分（score/answer_count）
	 * @param averageScore
	 * @type java.lang.Float
	 */
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
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
	 * 获取年级排名（在本次测试中）
	 * @return java.lang.Integer
	 */
	public Integer getGradeRank() {
		return this.gradeRank;
	}
	
	/**
	 * 设置年级排名（在本次测试中）
	 * @param gradeRank
	 * @type java.lang.Integer
	 */
	public void setGradeRank(Integer gradeRank) {
		this.gradeRank = gradeRank;
	}
	
	/**
	 * 获取题目警告系数
	 * @return java.lang.Float
	 */
	public Float getWarningFactor() {
		return this.warningFactor;
	}
	
	/**
	 * 设置题目警告系数
	 * @param warningFactor
	 * @type java.lang.Float
	 */
	public void setWarningFactor(Float warningFactor) {
		this.warningFactor = warningFactor;
	}
	
	/**
	 * 获取总答题时间（单位秒、本班）
	 * @return java.lang.Integer
	 */
	public Integer getTotalTime() {
		return this.totalTime;
	}
	
	/**
	 * 设置总答题时间（单位秒、本班）
	 * @param totalTime
	 * @type java.lang.Integer
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	
	/**
	 * 获取平均答题时间（单位秒、本班）
	 * @return java.lang.Integer
	 */
	public Integer getAverageTime() {
		return this.averageTime;
	}
	
	/**
	 * 设置平均答题时间（单位秒、本班）
	 * @param averageTime
	 * @type java.lang.Integer
	 */
	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
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
	
	/**
	 * 获取pa_question表 uuid
	 * @return java.lang.String
	 */
	public String getQuestionUuid() {
		return this.questionUuid;
	}
	
	/**
	 * 设置pa_question表 uuid
	 * @param questionUuid
	 * @type java.lang.String
	 */
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
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
	 * 获取对应学科知识树的节点 jc_knowledge.id
	 * @return java.lang.Integer
	 */
	public Integer getKnowledgeId() {
		return this.knowledgeId;
	}
	
	/**
	 * 设置对应学科知识树的节点 jc_knowledge.id
	 * @param knowledgeId
	 * @type java.lang.Integer
	 */
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	
	/**
	 * 获取难易度
	 * @return java.lang.Float
	 */
	public Float getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度
	 * @param difficulity
	 * @type java.lang.Float
	 */
	public void setDifficulity(Float difficulity) {
		this.difficulity = difficulity;
	}
	
	/**
	 * 获取认知度 1=识计，2=理解，3=应用，4=探究，5=综合
	 * @return java.lang.String
	 */
	public String getCognition() {
		return this.cognition;
	}
	
	/**
	 * 设置认知度 1=识计，2=理解，3=应用，4=探究，5=综合
	 * @param cognition
	 * @type java.lang.String
	 */
	public void setCognition(String cognition) {
		this.cognition = cognition;
	}
	
	/**
	 * 获取题目标准得分（完全答对得分） question.score 
	 * @return java.lang.Float
	 */
	public Float getFullScore() {
		return this.fullScore;
	}
	
	/**
	 * 设置题目标准得分（完全答对得分） question.score 
	 * @param fullScore
	 * @type java.lang.Float
	 */
	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}
	
	/**
	 * 获取班级得分率eq.score /（eq.answer_count * eq.full_score）
	 * @return java.lang.Float
	 */
	public Float getTeamScoringRate() {
		return this.teamScoringRate;
	}
	
	/**
	 * 设置班级得分率eq.score /（eq.answer_count * eq.full_score）
	 * @param teamScoringRate
	 * @type java.lang.Float
	 */
	public void setTeamScoringRate(Float teamScoringRate) {
		this.teamScoringRate = teamScoringRate;
	}
	
	/**
	 * 获取年级得分率（sum(eq.score) / (sum(eq.answer_count) * eq.full_score)）
	 * @return java.lang.Float
	 */
	public Float getGradeScoringRate() {
		return this.gradeScoringRate;
	}
	
	/**
	 * 设置年级得分率（sum(eq.score) / (sum(eq.answer_count) * eq.full_score)）
	 * @param gradeScoringRate
	 * @type java.lang.Float
	 */
	public void setGradeScoringRate(Float gradeScoringRate) {
		this.gradeScoringRate = gradeScoringRate;
	}
	
	private Integer pos;

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
}