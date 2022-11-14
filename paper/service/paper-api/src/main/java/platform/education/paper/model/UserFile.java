package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * UserFile
 * @author AutoCreate
 *
 */
public class UserFile implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 对应用户答卷 user_paper.id
	 */
	private Integer userPaperId;
	/**
	 * 对应的用户答题，如果文件跟某道题目相关时，可空
	 */
	private Integer userQuestionId;
	/**
	 * sourceFileUuid
	 */
	private String sourceFileUuid;
	/**
	 * markedFileUuid
	 */
	private String markedFileUuid;
	/**
	 * 记录创建时间（提交时间）
	 */
	private java.util.Date createDate;
	/**
	 * 记录修改时间（批改时间）
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录删除标志
	 */
	private Boolean isDeleted;
	
	private String userQuestionUuid;
	

	public UserFile() {
		
	}
	
	public UserFile(Integer id) {
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
	 * 获取对应用户答卷 user_paper.id
	 * @return java.lang.Integer
	 */
	public Integer getUserPaperId() {
		return this.userPaperId;
	}
	
	/**
	 * 设置对应用户答卷 user_paper.id
	 * @param userPaperId
	 * @type java.lang.Integer
	 */
	public void setUserPaperId(Integer userPaperId) {
		this.userPaperId = userPaperId;
	}
	
	/**
	 * 获取对应的用户答题，如果文件跟某道题目相关时，可空
	 * @return java.lang.Integer
	 */
	public Integer getUserQuestionId() {
		return this.userQuestionId;
	}
	
	/**
	 * 设置对应的用户答题，如果文件跟某道题目相关时，可空
	 * @param userQuestionId
	 * @type java.lang.Integer
	 */
	public void setUserQuestionId(Integer userQuestionId) {
		this.userQuestionId = userQuestionId;
	}
	
	/**
	 * 获取sourceFileUuid
	 * @return java.lang.String
	 */
	public String getSourceFileUuid() {
		return this.sourceFileUuid;
	}
	
	/**
	 * 设置sourceFileUuid
	 * @param sourceFileUuid
	 * @type java.lang.String
	 */
	public void setSourceFileUuid(String sourceFileUuid) {
		this.sourceFileUuid = sourceFileUuid;
	}
	
	/**
	 * 获取markedFileUuid
	 * @return java.lang.String
	 */
	public String getMarkedFileUuid() {
		return this.markedFileUuid;
	}
	
	/**
	 * 设置markedFileUuid
	 * @param markedFileUuid
	 * @type java.lang.String
	 */
	public void setMarkedFileUuid(String markedFileUuid) {
		this.markedFileUuid = markedFileUuid;
	}
	
	/**
	 * 获取记录创建时间（提交时间）
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置记录创建时间（提交时间）
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取记录修改时间（批改时间）
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置记录修改时间（批改时间）
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取记录删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUserQuestionUuid() {
		return userQuestionUuid;
	}

	public void setUserQuestionUuid(String userQuestionUuid) {
		this.userQuestionUuid = userQuestionUuid;
	}
	
}