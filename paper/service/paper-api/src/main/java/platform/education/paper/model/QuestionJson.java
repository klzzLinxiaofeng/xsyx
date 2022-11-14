package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * Question
 * @author AutoCreate
 *
 */
public class QuestionJson implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 题目内容
	 */
	private String content;
	/**
	 * 额外资料的题目内容 (用于复合题)
	 */
	private String extraContent;
	/**
	 * 答案区内容
	 */
	private String []answer;
	/**
	 * 标准正确答案
	 */
	private String[] correctAnswer;
	/**
	 * 题型
	 */
	private String questionType;
	/**
	 * 难易度
	 */
	private Double difficulity;
	/**
	 * 科目CODE
	 */
	private String subjectCode;
	/**
	 * 版本CODE
	 */
	private String versionCode;
	/**
	 * 级年CODE
	 */
	private String gradeCode;
	/**
	 * 册次CODE
	 */
	private String volumeCode;
	/**
	 * 栏目CODE
	 */
	private String categoryCode;
	/**
	 * 题目标准解释
	 */
	private String explanation;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 父类题目(用于复合题)
	 */
	private String parentQuestion;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 题目来源
	 */
	private String source;
	/**
	 * 题目顺序(题库里的题目顺序只用于方便显示和复制题目)
	 */
	private Integer pos;
	/**
	 * 组ID
	 */
	private String groupId;
	/**
	 * 组标题
	 */
	private String groupTitle;
	/**
	 * 知识点
	 */
	private String knowledge;
	/**
	 * 额外资料的题干内容关联音视频地址 (用于复合题)
	 */
	private String extraContentMediaUrl;
	/**
	 * 题干内容关联音视频地址
	 */
	private String contentMediaUrl;
	/**
	 * 答案内容关联音视频地址
	 */
	private String answerMediaUrl;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 做关联的uuid
	 */
	private String questionUuid;
	/**
	 * 题目来源类型 1= 独立试题，2=试卷试题，3=导学案试题
	 */
	private Integer sourceType;
	/**
	 * usedCount
	 */
	private Integer usedCount;
	/**
	 * 认知度1：标记，2：理解，3：应用，4：探究，5：综合
	 */
	private String cognition;
	/**
	 * 对应学科知识树的节点 jc_knowledge.id
	 */
	private Integer knowledgeId;
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
	 * 平均答题时间（单位秒）
	 */
	private Float averageTime;
	
	public QuestionJson() {
		
	}
	
	public QuestionJson(Integer id) {
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
	 * 获取额外资料的题目内容 (用于复合题)
	 * @return java.lang.String
	 */
	public String getExtraContent() {
		return this.extraContent;
	}
	
	/**
	 * 设置额外资料的题目内容 (用于复合题)
	 * @param extraContent
	 * @type java.lang.String
	 */
	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}
	
	/**
	 * 获取答案区内容
	 * @return java.lang.String
	 */
	public String[] getAnswer() {
		return this.answer;
	}
	
	/**
	 * 设置答案区内容
	 * @param answer
	 * @type java.lang.String
	 */
	public void setAnswer(String[] answer) {
		this.answer = answer;
	}
	
	/**
	 * 获取标准正确答案
	 * @return java.lang.String
	 */
	public String[] getCorrectAnswer() {
		return this.correctAnswer;
	}
	
	/**
	 * 设置标准正确答案
	 * @param correctAnswer
	 * @type java.lang.String
	 */
	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
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
	 * 获取难易度
	 * @return java.lang.Double
	 */
	public Double getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度
	 * @param difficulity
	 * @type java.lang.Double
	 */
	public void setDifficulity(Double difficulity) {
		this.difficulity = difficulity;
	}
	
	/**
	 * 获取科目CODE
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目CODE
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取版本CODE
	 * @return java.lang.String
	 */
	public String getVersionCode() {
		return this.versionCode;
	}
	
	/**
	 * 设置版本CODE
	 * @param versionCode
	 * @type java.lang.String
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	/**
	 * 获取级年CODE
	 * @return java.lang.String
	 */
	public String getGradeCode() {
		return this.gradeCode;
	}
	
	/**
	 * 设置级年CODE
	 * @param gradeCode
	 * @type java.lang.String
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	/**
	 * 获取册次CODE
	 * @return java.lang.String
	 */
	public String getVolumeCode() {
		return this.volumeCode;
	}
	
	/**
	 * 设置册次CODE
	 * @param volumeCode
	 * @type java.lang.String
	 */
	public void setVolumeCode(String volumeCode) {
		this.volumeCode = volumeCode;
	}
	
	/**
	 * 获取栏目CODE
	 * @return java.lang.String
	 */
	public String getCategoryCode() {
		return this.categoryCode;
	}
	
	/**
	 * 设置栏目CODE
	 * @param categoryCode
	 * @type java.lang.String
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.String
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取父类题目(用于复合题)
	 * @return java.lang.String
	 */
	public String getParentQuestion() {
		return this.parentQuestion;
	}
	
	/**
	 * 设置父类题目(用于复合题)
	 * @param parentQuestion
	 * @type java.lang.String
	 */
	public void setParentQuestion(String parentQuestion) {
		this.parentQuestion = parentQuestion;
	}
	
	/**
	 * 获取分数
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置分数
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取题目来源
	 * @return java.lang.String
	 */
	public String getSource() {
		return this.source;
	}
	
	/**
	 * 设置题目来源
	 * @param source
	 * @type java.lang.String
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * 获取题目顺序(题库里的题目顺序只用于方便显示和复制题目)
	 * @return java.lang.Integer
	 */
	public Integer getPos() {
		return this.pos;
	}
	
	/**
	 * 设置题目顺序(题库里的题目顺序只用于方便显示和复制题目)
	 * @param pos
	 * @type java.lang.Integer
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	/**
	 * 获取组ID
	 * @return java.lang.String
	 */
	public String getGroupId() {
		return this.groupId;
	}
	
	/**
	 * 设置组ID
	 * @param groupId
	 * @type java.lang.String
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取组标题
	 * @return java.lang.String
	 */
	public String getGroupTitle() {
		return this.groupTitle;
	}
	
	/**
	 * 设置组标题
	 * @param groupTitle
	 * @type java.lang.String
	 */
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	
	/**
	 * 获取知识点
	 * @return java.lang.String
	 */
	public String getKnowledge() {
		return this.knowledge;
	}
	
	/**
	 * 设置知识点
	 * @param knowledge
	 * @type java.lang.String
	 */
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	
	/**
	 * 获取额外资料的题干内容关联音视频地址 (用于复合题)
	 * @return java.lang.String
	 */
	public String getExtraContentMediaUrl() {
		return this.extraContentMediaUrl;
	}
	
	/**
	 * 设置额外资料的题干内容关联音视频地址 (用于复合题)
	 * @param extraContentMediaUrl
	 * @type java.lang.String
	 */
	public void setExtraContentMediaUrl(String extraContentMediaUrl) {
		this.extraContentMediaUrl = extraContentMediaUrl;
	}
	
	/**
	 * 获取题干内容关联音视频地址
	 * @return java.lang.String
	 */
	public String getContentMediaUrl() {
		return this.contentMediaUrl;
	}
	
	/**
	 * 设置题干内容关联音视频地址
	 * @param contentMediaUrl
	 * @type java.lang.String
	 */
	public void setContentMediaUrl(String contentMediaUrl) {
		this.contentMediaUrl = contentMediaUrl;
	}
	
	/**
	 * 获取答案内容关联音视频地址
	 * @return java.lang.String
	 */
	public String getAnswerMediaUrl() {
		return this.answerMediaUrl;
	}
	
	/**
	 * 设置答案内容关联音视频地址
	 * @param answerMediaUrl
	 * @type java.lang.String
	 */
	public void setAnswerMediaUrl(String answerMediaUrl) {
		this.answerMediaUrl = answerMediaUrl;
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
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getQuestionUuid() {
		return this.questionUuid;
	}
	
	/**
	 * 设置做关联的uuid
	 * @param questionUuid
	 * @type java.lang.String
	 */
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}
	
	/**
	 * 获取题目来源类型 1= 独立试题，2=试卷试题，3=导学案试题
	 * @return java.lang.Integer
	 */
	public Integer getSourceType() {
		return this.sourceType;
	}
	
	/**
	 * 设置题目来源类型 1= 独立试题，2=试卷试题，3=导学案试题
	 * @param sourceType
	 * @type java.lang.Integer
	 */
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * 获取usedCount
	 * @return java.lang.Integer
	 */
	public Integer getUsedCount() {
		return this.usedCount;
	}
	
	/**
	 * 设置usedCount
	 * @param usedCount
	 * @type java.lang.Integer
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	
	/**
	 * 获取认知度1：标记，2：理解，3：应用，4：探究，5：综合
	 * @return java.lang.String
	 */
	public String getCognition() {
		return this.cognition;
	}
	
	/**
	 * 设置认知度1：标记，2：理解，3：应用，4：探究，5：综合
	 * @param cognition
	 * @type java.lang.String
	 */
	public void setCognition(String cognition) {
		this.cognition = cognition;
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
	 * 获取平均答题时间（单位秒）
	 * @return java.lang.Float
	 */
	public Float getAverageTime() {
		return this.averageTime;
	}
	
	/**
	 * 设置平均答题时间（单位秒）
	 * @param averageTime
	 * @type java.lang.Float
	 */
	public void setAverageTime(Float averageTime) {
		this.averageTime = averageTime;
	}
	
}