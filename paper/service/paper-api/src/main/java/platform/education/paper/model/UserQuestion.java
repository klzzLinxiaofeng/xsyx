package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * UserQuestion
 * @author AutoCreate
 *
 */
public class UserQuestion implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 题目所在试卷ID
	 */
	private String paperUuid;
	/**
	 * 题目在试卷表的记录id
	 */
	private Integer paperQuestionId;
	/**
	 * 在试题表的uuid
	 */
	private String questionUuid;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 班级ID
	 */
	private Integer teamId;
	/**
	 * 用户提交答案内容
	 */
	private String answer;
	/**
	 * 得分
	 */
	private Double score;
	/**
	 * 是否正确
	 */
	private Boolean isCorrect;
	
	private Integer correctInt;
	
	/**
	 * 评语或分析
	 */
	private String remark;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * 题目类型：4.导学案 1.作业 2.考试 3.练习
	 */
	private Integer type;
	/**
	 * 任务id
	 */
	private Integer ownerId;
	/**
	 * 作答的拍照图像文件
	 */
	private String fileId; 
	
	//用户答题时间
	private Integer answerTime;
	
	private Integer objectId;
	
	//关联表使用。
	private String uuid;
	
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public UserQuestion() {
	}
	
	public UserQuestion(Integer id) {
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
	 * 获取题目所在试卷ID
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置题目所在试卷ID
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
	}
	
	/**
	 * 获取题目在试卷表的记录id
	 * @return java.lang.Integer
	 */
	public Integer getPaperQuestionId() {
		return this.paperQuestionId;
	}
	
	/**
	 * 设置题目在试卷表的记录id
	 * @param paperQuestionId
	 * @type java.lang.Integer
	 */
	public void setPaperQuestionId(Integer paperQuestionId) {
		this.paperQuestionId = paperQuestionId;
	}
	
	/**
	 * 获取在试题表的uuid
	 * @return java.lang.String
	 */
	public String getQuestionUuid() {
		return this.questionUuid;
	}
	
	/**
	 * 设置在试题表的uuid
	 * @param questionUuid
	 * @type java.lang.String
	 */
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
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
	 * 获取班级ID
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级ID
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取用户提交答案内容
	 * @return java.lang.String
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * 设置用户提交答案内容
	 * @param answer
	 * @type java.lang.String
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	/**
	 * 获取得分
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置得分
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取是否正确
	 * @return java.lang.Boolean
	 */
	public Boolean getIsCorrect() {
		return this.isCorrect;
	}
	
	/**
	 * 设置是否正确
	 * @param isCorrect
	 * @type java.lang.Boolean
	 */
	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
	
	/**
	 * 获取评语或分析
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置评语或分析
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取题目类型：4.导学案 1.作业 2.考试 3.练习
	 * @return Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置题目类型：4.导学案 1.作业 2.考试 3.练习
	 * @param type
	 * @type Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取任务id
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置任务id
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取作答的拍照图像文件
	 * @return java.lang.String
	 */
	public String getFileId() {
		return this.fileId;
	}
	
	/**
	 * 设置作答的拍照图像文件
	 * @param fileId
	 * @type java.lang.String
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getCorrectInt() {
		return correctInt;
	}

	public void setCorrectInt(Integer correctInt) {
		this.correctInt = correctInt;
	}
	
}