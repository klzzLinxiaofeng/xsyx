package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * UserFileQuestion
 * @author AutoCreate
 *
 */
public class UserFileQuestion implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户文件ID user_file.id
	 */
	private Integer userFileId;
	/**
	 * user_question.id
	 */
	private Integer userQuestionId;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	//表关联使用。
	private String userQuestionUuid;
	
	public String getUserQuestionUuid() {
		return userQuestionUuid;
	}

	public void setUserQuestionUuid(String userQuestionUuid) {
		this.userQuestionUuid = userQuestionUuid;
	}

	public UserFileQuestion() {
		
	}
	
	public UserFileQuestion(Integer id) {
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
	 * 获取用户文件ID user_file.id
	 * @return java.lang.Integer
	 */
	public Integer getUserFileId() {
		return this.userFileId;
	}
	
	/**
	 * 设置用户文件ID user_file.id
	 * @param userFileId
	 * @type java.lang.Integer
	 */
	public void setUserFileId(Integer userFileId) {
		this.userFileId = userFileId;
	}
	
	/**
	 * 获取user_question.id
	 * @return java.lang.Integer
	 */
	public Integer getUserQuestionId() {
		return this.userQuestionId;
	}
	
	/**
	 * 设置user_question.id
	 * @param userQuestionId
	 * @type java.lang.Integer
	 */
	public void setUserQuestionId(Integer userQuestionId) {
		this.userQuestionId = userQuestionId;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
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
	
}