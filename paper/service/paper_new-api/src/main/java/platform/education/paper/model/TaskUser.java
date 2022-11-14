package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * TaskUser
 * @author AutoCreate
 *
 */
public class TaskUser implements Model<Integer> {
	
	
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
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 对应的任务ID
	 */
	private Integer taskId;
	/**
	 * 完成标记   1  已完成    2  未完成   
	 */
	private Integer finishedFlag;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 完成日期时间
	 */
	private java.util.Date finishedDate;
	/**
	 * 学生ID
	 */
	private Integer studentId;
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * 删除标志
	 */
	private Boolean isDelete;
	
	public TaskUser() {
		
	}
	
	public TaskUser(Integer id) {
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
	 * 获取对应的任务ID
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置对应的任务ID
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 获取完成标记   1  已完成    2  未完成   
	 * @return java.lang.Integer
	 */
	public Integer getFinishedFlag() {
		return this.finishedFlag;
	}
	
	/**
	 * 设置完成标记   1  已完成    2  未完成   
	 * @param finishedFlag
	 * @type java.lang.Integer
	 */
	public void setFinishedFlag(Integer finishedFlag) {
		this.finishedFlag = finishedFlag;
	}
	
	/**
	 * 获取用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取完成日期时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishedDate() {
		return this.finishedDate;
	}
	
	/**
	 * 设置完成日期时间
	 * @param finishedDate
	 * @type java.util.Date
	 */
	public void setFinishedDate(java.util.Date finishedDate) {
		this.finishedDate = finishedDate;
	}
	
	
	/**
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标志
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}