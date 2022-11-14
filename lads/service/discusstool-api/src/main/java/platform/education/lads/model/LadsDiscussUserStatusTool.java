package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsDiscussUserStatusTool
 * @author AutoCreate
 *
 */
public class LadsDiscussUserStatusTool implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 主题
	 */
	private String discuss;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private Integer userId;
	/**
	 * 学习成绩
	 */
	private String score;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	
	public LadsDiscussUserStatusTool() {
		
	}
	
	public LadsDiscussUserStatusTool(Integer id) {
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
	 * 获取主题
	 * @return java.lang.String
	 */
	public String getDiscuss() {
		return this.discuss;
	}
	
	/**
	 * 设置主题
	 * @param discuss
	 * @type java.lang.String
	 */
	public void setDiscuss(String discuss) {
		this.discuss = discuss;
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
	 * 获取学习成绩
	 * @return java.lang.String
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * 设置学习成绩
	 * @param score
	 * @type java.lang.String
	 */
	public void setScore(String score) {
		this.score = score;
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
	
}