package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaUserWrong
 * @author AutoCreate
 *
 */
public class PaUserWrong implements Model<Integer> {
	
	
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
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private Integer userId;
	/**
	 * 关联题目或paper的id
	 */
	private String paperUuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 题目在试卷表的记录ID
	 */
	private Integer paperQuestionId;
	/**
	 * 在试题表的uuid
	 */
	private String questionUuid;
	/**
	 * 用户提交的答案内容
	 */
	private String answer;
	/**
	 * 是否正确
	 */
	private Boolean isCorrect;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 线上线下作答，默认为false, 即线上
	 */
	private Boolean isOffline;
	/**
	 * 用户作答表id
	 */
	private Integer userQuestionId;
	/**
	 * 用户最后一次重做时间
	 */
	private java.util.Date lastTime;
	/**
	 * 用户最后一次重做的答案
	 */
	private String lastAnswer;
	/**
	 * 本人做对的次数
	 */
	private Integer rightCount;
	/**
	 * 本人做错次数
	 */
	private Integer wrongCount;
	/**
	 * 作答的拍照图片文件
	 */
	private String fileId;
	/**
	 * 作用于表关联
	 */
	private String userQuestionUuid;
	
	public PaUserWrong() {
		
	}
	
	public PaUserWrong(Integer id) {
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
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取关联题目或paper的id
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置关联题目或paper的id
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
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
	 * 获取题目在试卷表的记录ID
	 * @return java.lang.Integer
	 */
	public Integer getPaperQuestionId() {
		return this.paperQuestionId;
	}
	
	/**
	 * 设置题目在试卷表的记录ID
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
	 * 获取用户提交的答案内容
	 * @return java.lang.String
	 */
	public String getAnswer() {
		return this.answer;
	}
	
	/**
	 * 设置用户提交的答案内容
	 * @param answer
	 * @type java.lang.String
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
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
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取线上线下作答，默认为false, 即线上
	 * @return java.lang.Boolean
	 */
	public Boolean getIsOffline() {
		return this.isOffline;
	}
	
	/**
	 * 设置线上线下作答，默认为false, 即线上
	 * @param isOffline
	 * @type java.lang.Boolean
	 */
	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}
	
	/**
	 * 获取用户作答表id
	 * @return java.lang.Integer
	 */
	public Integer getUserQuestionId() {
		return this.userQuestionId;
	}
	
	/**
	 * 设置用户作答表id
	 * @param userQuestionId
	 * @type java.lang.Integer
	 */
	public void setUserQuestionId(Integer userQuestionId) {
		this.userQuestionId = userQuestionId;
	}
	
	/**
	 * 获取用户最后一次重做时间
	 * @return java.util.Date
	 */
	public java.util.Date getLastTime() {
		return this.lastTime;
	}
	
	/**
	 * 设置用户最后一次重做时间
	 * @param lastTime
	 * @type java.util.Date
	 */
	public void setLastTime(java.util.Date lastTime) {
		this.lastTime = lastTime;
	}
	
	/**
	 * 获取用户最后一次重做的答案
	 * @return java.lang.String
	 */
	public String getLastAnswer() {
		return this.lastAnswer;
	}
	
	/**
	 * 设置用户最后一次重做的答案
	 * @param lastAnswer
	 * @type java.lang.String
	 */
	public void setLastAnswer(String lastAnswer) {
		this.lastAnswer = lastAnswer;
	}
	
	/**
	 * 获取本人做对的次数
	 * @return java.lang.Integer
	 */
	public Integer getRightCount() {
		return this.rightCount;
	}
	
	/**
	 * 设置本人做对的次数
	 * @param rightCount
	 * @type java.lang.Integer
	 */
	public void setRightCount(Integer rightCount) {
		this.rightCount = rightCount;
	}
	
	/**
	 * 获取本人做错次数
	 * @return java.lang.Integer
	 */
	public Integer getWrongCount() {
		return this.wrongCount;
	}
	
	/**
	 * 设置本人做错次数
	 * @param wrongCount
	 * @type java.lang.Integer
	 */
	public void setWrongCount(Integer wrongCount) {
		this.wrongCount = wrongCount;
	}
	
	/**
	 * 获取作答的拍照图片文件
	 * @return java.lang.String
	 */
	public String getFileId() {
		return this.fileId;
	}
	
	/**
	 * 设置作答的拍照图片文件
	 * @param fileId
	 * @type java.lang.String
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	/**
	 * 获取作用于表关联
	 * @return java.lang.String
	 */
	public String getUserQuestionUuid() {
		return this.userQuestionUuid;
	}
	
	/**
	 * 设置作用于表关联
	 * @param userQuestionUuid
	 * @type java.lang.String
	 */
	public void setUserQuestionUuid(String userQuestionUuid) {
		this.userQuestionUuid = userQuestionUuid;
	}
	
}