package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaQuestion
 * @author AutoCreate
 *
 */
public class PaQuestion implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资源拥有方式 0=公开 1=单位学校 2=个人
	 */
	private Integer ownerMode;
	/**
	 * 单位所有者（冗余）如果资源属主是单位学校等，说明其拥有者id，这个与资源表属主值相同 公共资源=null 校本资源=学校uuid 个人资源=
	 */
	private String ownerUid;
	public Integer getOwnerMode() {
		return ownerMode;
	}

	public void setOwnerMode(Integer ownerMode) {
		this.ownerMode = ownerMode;
	}

	public String getOwnerUid() {
		return ownerUid;
	}

	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 题目uuid
	 */
	private String uuid;
	/**
	 * 父题目(用于复合题的主题目) 当题目是复合题的子题目时非0，其他情况为0 pa_question.id
	 */
	private Integer parentId;
	/**
	 * 题型
	 */
	private String questionType;
	/**
	 * 难易度 取值0~1
	 */
	private Float difficulity;
	/**
	 * 认知度 1=识记 2=理解 3=应用 4=分析 5=综合 6=评价
	 */
	private String cognition;
	/**
	 * 题目来源类型 1=独立试题 2=试卷试题 3=导学案试题
	 */
	private String sourceType;
	/**
	 * 题目的主客观性 1=objective 客观题  2=subjective 主观题 3=both 0/null=不确定
	 */
	private Integer questionProperty;
	/**
	 * 题目内容
	 */
	private String content;
	/**
	 * 答案区内容
	 */
	private String answer;
	/**
	 * 标准正确答案
	 */
	private String correctAnswer;
	/**
	 * 题目标准解释
	 */
	private String explanation;
	/**
	 * 题目扩展属性
	 */
	private String extraData;
	/**
	 * 题目创建者
	 */
	private Integer userId;
	/**
	 * 试题使用次数（完成次数）
	 */
	private Integer usedCount;
	/**
	 * 试题收藏次数
	 */
	private Integer favCount;
	
	public Integer getFavCount() {
		return favCount;
	}

	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}

	/**
	 * 题目历史答题次数
	 */
	private Integer answerCount;
	/**
	 * 题目历史答对次数
	 */
	private Integer rightAnswerCount;
	/**
	 * 总答题时间（单位秒）
	 */
	private Integer totalTime;
	/**
	 * 有时间记录的答题次数
	 */
	private Integer totalTimeCount;
	/**
	 * averageTime
	 */
	private Float averageTime;
	/**
	 * 记录删除标识
	 */
	private Integer isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public PaQuestion() {
		
	}
	
	public PaQuestion(Integer id) {
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
	 * 获取题目uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置题目uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取父题目(用于复合题的主题目) 当题目是复合题的子题目时非0，其他情况为0 pa_question.id
	 * @return java.lang.Integer
	 */
	public Integer getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置父题目(用于复合题的主题目) 当题目是复合题的子题目时非0，其他情况为0 pa_question.id
	 * @param parentId
	 * @type java.lang.Integer
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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
	 * 获取难易度 取值0~1
	 * @return java.lang.Float
	 */
	public Float getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度 取值0~1
	 * @param difficulity
	 * @type java.lang.Float
	 */
	public void setDifficulity(Float difficulity) {
		this.difficulity = difficulity;
	}
	
	/**
	 * 获取认知度 1=识记 2=理解 3=应用 4=分析 5=综合 6=评价
	 * @return java.lang.String
	 */
	public String getCognition() {
		return this.cognition;
	}
	
	/**
	 * 设置认知度 1=识记 2=理解 3=应用 4=分析 5=综合 6=评价
	 * @param cognition
	 * @type java.lang.String
	 */
	public void setCognition(String cognition) {
		this.cognition = cognition;
	}
	
	/**
	 * 获取题目来源类型 1=独立试题 2=试卷试题 3=导学案试题
	 * @return java.lang.String
	 */
	public String getSourceType() {
		return this.sourceType;
	}
	
	/**
	 * 设置题目来源类型 1=独立试题 2=试卷试题 3=导学案试题
	 * @param sourceType
	 * @type java.lang.String
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * 获取题目的主客观性 1=objective 客观题  2=subjective 主观题 3=both 0/null=不确定
	 * @return java.lang.Integer
	 */
	public Integer getQuestionProperty() {
		return this.questionProperty;
	}
	
	/**
	 * 设置题目的主客观性 1=objective 客观题  2=subjective 主观题 3=both 0/null=不确定
	 * @param questionProperty
	 * @type java.lang.Integer
	 */
	public void setQuestionProperty(Integer questionProperty) {
		this.questionProperty = questionProperty;
	}
	
	/**
	 * 获取题目内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置题目内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取答案区内容
	 * @return java.lang.String
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * 设置答案区内容
	 * @param answer
	 * @type java.lang.String
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * 获取标准正确答案
	 * @return java.lang.String
	 */
	public String getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	/**
	 * 设置标准正确答案
	 * @param correctAnswer
	 * @type java.lang.String
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	/**
	 * 获取题目标准解释
	 * @return java.lang.String
	 */
	public String getExplanation() {
		return this.explanation;
	}
	
	/**
	 * 设置题目标准解释
	 * @param explanation
	 * @type java.lang.String
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	/**
	 * 获取题目扩展属性
	 * @return java.lang.String
	 */
	public String getExtraData() {
		return this.extraData;
	}
	
	/**
	 * 设置题目扩展属性
	 * @param extraData
	 * @type java.lang.String
	 */
	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	
	/**
	 * 获取题目创建者
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置题目创建者
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取试题使用次数（完成次数）
	 * @return java.lang.Integer
	 */
	public Integer getUsedCount() {
		return this.usedCount;
	}
	
	/**
	 * 设置试题使用次数（完成次数）
	 * @param usedCount
	 * @type java.lang.Integer
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	
	/**
	 * 获取题目历史答题次数
	 * @return java.lang.Integer
	 */
	public Integer getAnswerCount() {
		return this.answerCount;
	}
	
	/**
	 * 设置题目历史答题次数
	 * @param answerCount
	 * @type java.lang.Integer
	 */
	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}
	
	/**
	 * 获取题目历史答对次数
	 * @return java.lang.Integer
	 */
	public Integer getRightAnswerCount() {
		return this.rightAnswerCount;
	}
	
	/**
	 * 设置题目历史答对次数
	 * @param rightAnswerCount
	 * @type java.lang.Integer
	 */
	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}
	
	/**
	 * 获取总答题时间（单位秒）
	 * @return java.lang.Integer
	 */
	public Integer getTotalTime() {
		return this.totalTime;
	}
	
	/**
	 * 设置总答题时间（单位秒）
	 * @param totalTime
	 * @type java.lang.Integer
	 */
	public void setTotalTime(Integer totalTime) {
		this.totalTime = totalTime;
	}
	
	/**
	 * 获取有时间记录的答题次数
	 * @return java.lang.Integer
	 */
	public Integer getTotalTimeCount() {
		return this.totalTimeCount;
	}
	
	/**
	 * 设置有时间记录的答题次数
	 * @param totalTimeCount
	 * @type java.lang.Integer
	 */
	public void setTotalTimeCount(Integer totalTimeCount) {
		this.totalTimeCount = totalTimeCount;
	}
	
	/**
	 * 获取averageTime
	 * @return java.lang.Float
	 */
	public Float getAverageTime() {
		return this.averageTime;
	}
	
	/**
	 * 设置averageTime
	 * @param averageTime
	 * @type java.lang.Float
	 */
	public void setAverageTime(Float averageTime) {
		this.averageTime = averageTime;
	}
	
	/**
	 * 获取记录删除标识
	 * @return Integer
	 */
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标识
	 * @param isDeleted
	 * @type Integer
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
	
}