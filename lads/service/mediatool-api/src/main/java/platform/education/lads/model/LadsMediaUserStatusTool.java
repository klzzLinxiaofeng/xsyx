package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsMediaUserStatusTool
 * @author AutoCreate
 *
 */
public class LadsMediaUserStatusTool implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private Integer userId;
	/**
	 * 学习状态
	 */
	private String status;
	/**
	 * 关联的微课工具id
	 */
	private String mediaTool;
	/**
	 * 学习次数
	 */
	private Integer studyTime;
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
	/**
	 * 记录最后一次的播放时间
	 */
	private Double lastPlayTime;
	
	public LadsMediaUserStatusTool() {
		
	}
	
	public LadsMediaUserStatusTool(Integer id) {
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
	 * 获取学习状态
	 * @return java.lang.String
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置学习状态
	 * @param status
	 * @type java.lang.String
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * 获取关联的微课工具id
	 * @return java.lang.String
	 */
	public String getMediaTool() {
		return this.mediaTool;
	}
	
	/**
	 * 设置关联的微课工具id
	 * @param mediaTool
	 * @type java.lang.String
	 */
	public void setMediaTool(String mediaTool) {
		this.mediaTool = mediaTool;
	}
	
	/**
	 * 获取学习次数
	 * @return java.lang.Integer
	 */
	public Integer getStudyTime() {
		return this.studyTime;
	}
	
	/**
	 * 设置学习次数
	 * @param studyTime
	 * @type java.lang.Integer
	 */
	public void setStudyTime(Integer studyTime) {
		this.studyTime = studyTime;
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
	
	/**
	 * 获取记录最后一次的播放时间
	 * @return java.lang.Double
	 */
	public Double getLastPlayTime() {
		return this.lastPlayTime;
	}
	
	/**
	 * 设置记录最后一次的播放时间
	 * @param lastPlayTime
	 * @type java.lang.Double
	 */
	public void setLastPlayTime(Double lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
	
}