package platform.education.oa.model;

import java.util.Date;

public class OaNotice {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 通知的ＵＵＩＤ
	 */
	private String uuid;
	/**
	 * yh_app_edition.key
	 */
	private String appKey;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 发送者ID
	 */
	private Integer posterId;
	/**
	 * 发送者
	 */
	private String posterName;
	/**
	 * 定时发送的时间
	 */
	private java.util.Date postTime;
	/**
	 * 接收类型
	 */
	private String receiverType;
	/**
	 * 接收者姓名
	 */
	private String receiverName;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 发送的总人数
	 */
	private Integer userCount;
	/**
	 * 阅读人数
	 */
	private Integer readCount;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	
	public OaNotice() {
		
	}
	
	public OaNotice(Integer id) {
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
	 * 获取通知的ＵＵＩＤ
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置通知的ＵＵＩＤ
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取yh_app_edition.key
	 * @return java.lang.String
	 */
	public String getAppKey() {
		return this.appKey;
	}
	
	/**
	 * 设置yh_app_edition.key
	 * @param appKey
	 * @type java.lang.String
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	/**
	 * 获取通知标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置通知标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取发送者ID
	 * @return java.lang.Integer
	 */
	public Integer getPosterId() {
		return this.posterId;
	}
	
	/**
	 * 设置发送者ID
	 * @param posterId
	 * @type java.lang.Integer
	 */
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	
	/**
	 * 获取发送者
	 * @return java.lang.String
	 */
	public String getPosterName() {
		return this.posterName;
	}
	
	/**
	 * 设置发送者
	 * @param posterName
	 * @type java.lang.String
	 */
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	
	/**
	 * 获取定时发送的时间
	 * @return java.util.Date
	 */
	public java.util.Date getPostTime() {
		return this.postTime;
	}
	
	/**
	 * 设置定时发送的时间
	 * @param postTime
	 * @type java.util.Date
	 */
	public void setPostTime(java.util.Date postTime) {
		this.postTime = postTime;
	}
	
	/**
	 * 获取接收类型
	 * @return java.lang.String
	 */
	public String getReceiverType() {
		return this.receiverType;
	}
	
	/**
	 * 设置接收类型
	 * @param receiverType
	 * @type java.lang.String
	 */
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	
	/**
	 * 获取接收者姓名
	 * @return java.lang.String
	 */
	public String getReceiverName() {
		return this.receiverName;
	}
	
	/**
	 * 设置接收者姓名
	 * @param receiverName
	 * @type java.lang.String
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * 获取通知内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置通知内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取发送的总人数
	 * @return java.lang.Integer
	 */
	public Integer getUserCount() {
		return this.userCount;
	}
	
	/**
	 * 设置发送的总人数
	 * @param userCount
	 * @type java.lang.Integer
	 */
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
	/**
	 * 获取阅读人数
	 * @return java.lang.Integer
	 */
	public Integer getReadCount() {
		return this.readCount;
	}
	
	/**
	 * 设置阅读人数
	 * @param readCount
	 * @type java.lang.Integer
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
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
	 
}
