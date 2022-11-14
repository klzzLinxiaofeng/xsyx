package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Paper
 * @author AutoCreate
 *
 */
public class Paper implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * appid
	 */
	private Integer appId;
	/**
	 * 公文所属的单位，学校
	 */
	private Integer ownerId;
	/**
	 * 组的类型，1：学校
	 */
	private String ownerType;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发文单位
	 */
	private String author;
	/**
	 * 发布者ID
	 */
	private Integer posterId;
	/**
	 * 发布者姓名
	 */
	private String posterName;
	/**
	 * 公文种类
	 */
	private String documentType;
	/**
	 * 公文紧急等级
	 */
	private String emergencyLevel;
	/**
	 * 公文机密等级
	 */
	private String secretLevel;
	/**
	 * 接收者类型
	 */
	private Integer receiverType;
	/**
	 * 接收者实际人数
	 */
	private Integer receiverCount;
	/**
	 * 已阅人数
	 */
	private Integer readCount;
	

	/**
	 * 接收者名字
	 */
	private String receiver;
	/**
	 * 正文内容
	 */
	private String content;
	/**
	 * 附件文件ID
	 */
	private String attachmentUuid;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 发布日期
	 */
	private String publishDate;
	
	
	
	/**
	 * 摘要
	 */
	private String remark;
	
	
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Paper() {
		
	}
	
	public Paper(Integer id) {
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
	 * 获取UUID
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置UUID
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取appid
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置appid
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取公文所属的单位，学校
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置公文所属的单位，学校
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
	 * 获取标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取发文单位
	 * @return java.lang.String
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * 设置发文单位
	 * @param author
	 * @type java.lang.String
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * 获取公文种类
	 * @return java.lang.String
	 */
	public String getDocumentType() {
		return this.documentType;
	}
	
	/**
	 * 设置公文种类
	 * @param documentType
	 * @type java.lang.String
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	
	/**
	 * 获取公文紧急等级
	 * @return java.lang.String
	 */
	public String getEmergencyLevel() {
		return this.emergencyLevel;
	}
	
	/**
	 * 设置公文紧急等级
	 * @param emergencyLevel
	 * @type java.lang.String
	 */
	public void setEmergencyLevel(String emergencyLevel) {
		this.emergencyLevel = emergencyLevel;
	}
	
	/**
	 * 获取公文机密等级
	 * @return java.lang.String
	 */
	public String getSecretLevel() {
		return this.secretLevel;
	}
	
	/**
	 * 设置公文机密等级
	 * @param secretLevel
	 * @type java.lang.String
	 */
	public void setSecretLevel(String secretLevel) {
		this.secretLevel = secretLevel;
	}
	
	/**
	 * 获取接收者类型
	 * @return Integer
	 */
	public Integer getReceiverType() {
		return this.receiverType;
	}
	
	/**
	 * 设置接收者类型
	 * @param receiverType
	 * @type Integer
	 */
	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}
	
	/**
	 * 获取接收者实际人数
	 * @return Integer
	 */
	public Integer getReceiverCount() {
		return this.receiverCount;
	}
	
	/**
	 * 设置接收者实际人数
	 * @param receiverCount
	 * @type Integer
	 */
	public void setReceiverCount(Integer receiverCount) {
		this.receiverCount = receiverCount;
	}
	
	/**
	 * 获取已阅人数
	 * @return Integer
	 */
	public Integer getReadCount() {
		return this.readCount;
	}
	
	/**
	 * 设置已阅人数
	 * @param readCount
	 * @type Integer
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}
	
	/**
	 * 获取接收者名字
	 * @return java.lang.String
	 */
	public String getReceiver() {
		return this.receiver;
	}
	
	/**
	 * 设置接收者名字
	 * @param receiver
	 * @type java.lang.String
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
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
	 * 获取附件文件ID
	 * @return java.lang.String
	 */
	public String getAttachmentUuid() {
		return this.attachmentUuid;
	}
	
	/**
	 * 设置附件文件ID
	 * @param attachmentUuid
	 * @type java.lang.String
	 */
	public void setAttachmentUuid(String attachmentUuid) {
		this.attachmentUuid = attachmentUuid;
	}
	
	/**
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	
	
	
	
}