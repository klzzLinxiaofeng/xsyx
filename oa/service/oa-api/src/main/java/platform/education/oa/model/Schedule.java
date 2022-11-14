package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Schedule
 * @author AutoCreate
 *
 */
public class Schedule implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * app.id
	 */
	private Integer appId;
	/**
	 * 所属的单位，学校
	 */
	private Integer ownerId;
	/**
	 * 组的类型，1：学校
	 */
	private String ownerType;
	/**
	 * 发布者ID
	 */
	private Integer posterId;
	/**
	 * 发布者姓名
	 */
	private String posterName;
	/**
	 * 日程安排开始时间
	 */
	private String planStartTime;
	/**
	 * 日程安排结束时间
	 */
	private String planFinishTime;
	/**
	 * 共享对象范围
	 */
	private String shareTo;
	/**
	 * 优先等级,0=普通，1=重要
	 */
	private Integer rank;
	/**
	 * 正文内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除，0：还没删除，1：已经删除
	 */
	private Boolean isDeleted;
	
	public Schedule() {
		
	}
	
	public Schedule(Integer id) {
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
	 * 获取uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取app.id
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置app.id
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取所属的单位，学校
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置所属的单位，学校
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取组的类型，1：学校
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置组的类型，1：学校
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取发布者ID
	 * @return java.lang.Integer
	 */
	public Integer getPosterId() {
		return this.posterId;
	}
	
	/**
	 * 设置发布者ID
	 * @param posterId
	 * @type java.lang.Integer
	 */
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	
	/**
	 * 获取发布者姓名
	 * @return java.lang.String
	 */
	public String getPosterName() {
		return this.posterName;
	}
	
	/**
	 * 设置发布者姓名
	 * @param posterName
	 * @type java.lang.String
	 */
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	
	/**
	 * 获取日程安排开始时间
	 * @return java.lang.String
	 */
	public String getPlanStartTime() {
		return this.planStartTime;
	}
	
	/**
	 * 设置日程安排开始时间
	 * @param planStartTime
	 * @type java.lang.String
	 */
	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}
	
	/**
	 * 获取日程安排结束时间
	 * @return java.lang.String
	 */
	public String getPlanFinishTime() {
		return this.planFinishTime;
	}
	
	/**
	 * 设置日程安排结束时间
	 * @param planFinishTime
	 * @type java.lang.String
	 */
	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	
	/**
	 * 获取共享对象范围
	 * @return java.lang.String
	 */
	public String getShareTo() {
		return this.shareTo;
	}
	
	/**
	 * 设置共享对象范围
	 * @param shareTo
	 * @type java.lang.String
	 */
	public void setShareTo(String shareTo) {
		this.shareTo = shareTo;
	}
	
	/**
	 * 获取优先等级,0=普通，1=重要
	 * @return java.lang.Integer
	 */
	public Integer getRank() {
		return this.rank;
	}
	
	/**
	 * 设置优先等级,0=普通，1=重要
	 * @param rank
	 * @type java.lang.Integer
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	/**
	 * 获取正文内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置正文内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 获取是否删除，0：还没删除，1：已经删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除，0：还没删除，1：已经删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}