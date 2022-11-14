package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaPaperQuestion
 * @author AutoCreate
 *
 */
public class PaPaperQuestion implements Model<Integer> {
	
	
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
	 * 额外资料的题干内容关联音视频地址 (用于复合题)
	 */
	private String extraContentMediaUrl;
	/**
	 * 答案区内容
	 */
	private String answer;
	/**
	 * 标准正确答案
	 */
	private String correctAnswer;
	/**
	 * 题型
	 */
	private String questionType;
	/**
	 * 题干内容关联音视频地址
	 */
	private String contentMediaUrl;
	/**
	 * 答案内容关联音视频地址
	 */
	private String answerMediaUrl;
	/**
	 * 难易度
	 */
	private Integer difficulity;
	/**
	 * 科目CODE
	 */
	private String subjectCode;
	/**
	 * 版本CODE
	 */
	private String publishCode;
	/**
	 * 级年CODE
	 */
	private String gradeCode;
	/**
	 * 册次CODE
	 */
	private String volumeCode;
	/**
	 * 章CODE
	 */
	private String unitCode;
	/**
	 * 节CODE
	 */
	private String sectionCode;
	/**
	 * 题目标准解释
	 */
	private String explanation;
	/**
	 * 题目顺序
	 */
	private Integer pos;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 是否已发布
	 */
	private String publish;
	/**
	 * 原题目的id
	 */
	private String superQuestionId;
	/**
	 * 该题目关联的练习
	 */
	private String paper;
	/**
	 * 父类题目(用于复合题)
	 */
	private String parentQuestion;
	/**
	 * 题目正确时跳向的分支
	 */
	private String correctBranch;
	/**
	 * 题目错误时跳向的分支
	 */
	private String incorrectBranch;
	/**
	 * 题目完成时跳向的分支
	 */
	private String finishedBranch;
	/**
	 * 题目未完成时跳向的分支
	 */
	private String unfinishedBranch;
	/**
	 * 关联书的ID
	 */
	private String bookId;
	/**
	 * 新目录保存的关联章id
	 */
	private String bookUnitId;
	/**
	 * 新目录保存的关联节id
	 */
	private String bookSectionId;
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
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public PaPaperQuestion() {
		
	}
	
	public PaPaperQuestion(Integer id) {
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
	 * 获取难易度
	 * @return java.lang.Integer
	 */
	public Integer getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度
	 * @param difficulity
	 * @type java.lang.Integer
	 */
	public void setDifficulity(Integer difficulity) {
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
	public String getPublishCode() {
		return this.publishCode;
	}
	
	/**
	 * 设置版本CODE
	 * @param publishCode
	 * @type java.lang.String
	 */
	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
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
	 * 获取章CODE
	 * @return java.lang.String
	 */
	public String getUnitCode() {
		return this.unitCode;
	}
	
	/**
	 * 设置章CODE
	 * @param unitCode
	 * @type java.lang.String
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	/**
	 * 获取节CODE
	 * @return java.lang.String
	 */
	public String getSectionCode() {
		return this.sectionCode;
	}
	
	/**
	 * 设置节CODE
	 * @param sectionCode
	 * @type java.lang.String
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
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
	 * 获取题目顺序
	 * @return java.lang.Integer
	 */
	public Integer getPos() {
		return this.pos;
	}
	
	/**
	 * 设置题目顺序
	 * @param pos
	 * @type java.lang.Integer
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
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
	 * 获取是否已发布
	 * @return java.lang.String
	 */
	public String getPublish() {
		return this.publish;
	}
	
	/**
	 * 设置是否已发布
	 * @param publish
	 * @type java.lang.String
	 */
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	/**
	 * 获取原题目的id
	 * @return java.lang.String
	 */
	public String getSuperQuestionId() {
		return this.superQuestionId;
	}
	
	/**
	 * 设置原题目的id
	 * @param superQuestionId
	 * @type java.lang.String
	 */
	public void setSuperQuestionId(String superQuestionId) {
		this.superQuestionId = superQuestionId;
	}
	
	/**
	 * 获取该题目关联的练习
	 * @return java.lang.String
	 */
	public String getPaper() {
		return this.paper;
	}
	
	/**
	 * 设置该题目关联的练习
	 * @param paper
	 * @type java.lang.String
	 */
	public void setPaper(String paper) {
		this.paper = paper;
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
	 * 获取题目正确时跳向的分支
	 * @return java.lang.String
	 */
	public String getCorrectBranch() {
		return this.correctBranch;
	}
	
	/**
	 * 设置题目正确时跳向的分支
	 * @param correctBranch
	 * @type java.lang.String
	 */
	public void setCorrectBranch(String correctBranch) {
		this.correctBranch = correctBranch;
	}
	
	/**
	 * 获取题目错误时跳向的分支
	 * @return java.lang.String
	 */
	public String getIncorrectBranch() {
		return this.incorrectBranch;
	}
	
	/**
	 * 设置题目错误时跳向的分支
	 * @param incorrectBranch
	 * @type java.lang.String
	 */
	public void setIncorrectBranch(String incorrectBranch) {
		this.incorrectBranch = incorrectBranch;
	}
	
	/**
	 * 获取题目完成时跳向的分支
	 * @return java.lang.String
	 */
	public String getFinishedBranch() {
		return this.finishedBranch;
	}
	
	/**
	 * 设置题目完成时跳向的分支
	 * @param finishedBranch
	 * @type java.lang.String
	 */
	public void setFinishedBranch(String finishedBranch) {
		this.finishedBranch = finishedBranch;
	}
	
	/**
	 * 获取题目未完成时跳向的分支
	 * @return java.lang.String
	 */
	public String getUnfinishedBranch() {
		return this.unfinishedBranch;
	}
	
	/**
	 * 设置题目未完成时跳向的分支
	 * @param unfinishedBranch
	 * @type java.lang.String
	 */
	public void setUnfinishedBranch(String unfinishedBranch) {
		this.unfinishedBranch = unfinishedBranch;
	}
	
	/**
	 * 获取关联书的ID
	 * @return java.lang.String
	 */
	public String getBookId() {
		return this.bookId;
	}
	
	/**
	 * 设置关联书的ID
	 * @param bookId
	 * @type java.lang.String
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	/**
	 * 获取新目录保存的关联章id
	 * @return java.lang.String
	 */
	public String getBookUnitId() {
		return this.bookUnitId;
	}
	
	/**
	 * 设置新目录保存的关联章id
	 * @param bookUnitId
	 * @type java.lang.String
	 */
	public void setBookUnitId(String bookUnitId) {
		this.bookUnitId = bookUnitId;
	}
	
	/**
	 * 获取新目录保存的关联节id
	 * @return java.lang.String
	 */
	public String getBookSectionId() {
		return this.bookSectionId;
	}
	
	/**
	 * 设置新目录保存的关联节id
	 * @param bookSectionId
	 * @type java.lang.String
	 */
	public void setBookSectionId(String bookSectionId) {
		this.bookSectionId = bookSectionId;
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
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置做关联的uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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