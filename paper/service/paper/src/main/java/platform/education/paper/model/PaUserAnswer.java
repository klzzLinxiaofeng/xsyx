package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaUserAnswer
 * @author AutoCreate
 *
 */
public class PaUserAnswer implements Model<Integer> {
	
	
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
	 * 标记是否为最终答案
	 */
	private Integer finalAnswer;
	/**
	 * 用户提交的答案内容
	 */
	private String answer;
	/**
	 * 关联的题目
	 */
	private String paperQuestion;
	/**
	 * 个人得分
	 */
	private Double score;
	/**
	 * 是否正确
	 */
	private Integer correct;
	/**
	 * 个人评语
	 */
	private String description;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public PaUserAnswer() {
		
	}
	
	public PaUserAnswer(Integer id) {
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
	 * 获取标记是否为最终答案
	 * @return java.lang.Integer
	 */
	public Integer getFinalAnswer() {
		return this.finalAnswer;
	}
	
	/**
	 * 设置标记是否为最终答案
	 * @param finalAnswer
	 * @type java.lang.Integer
	 */
	public void setFinalAnswer(Integer finalAnswer) {
		this.finalAnswer = finalAnswer;
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
	 * 获取关联的题目
	 * @return java.lang.String
	 */
	public String getPaperQuestion() {
		return this.paperQuestion;
	}
	
	/**
	 * 设置关联的题目
	 * @param paperQuestion
	 * @type java.lang.String
	 */
	public void setPaperQuestion(String paperQuestion) {
		this.paperQuestion = paperQuestion;
	}
	
	/**
	 * 获取个人得分
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置个人得分
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取是否正确
	 * @return java.lang.Integer
	 */
	public Integer getCorrect() {
		return this.correct;
	}
	
	/**
	 * 设置是否正确
	 * @param correct
	 * @type java.lang.Integer
	 */
	public void setCorrect(Integer correct) {
		this.correct = correct;
	}
	
	/**
	 * 获取个人评语
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置个人评语
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
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