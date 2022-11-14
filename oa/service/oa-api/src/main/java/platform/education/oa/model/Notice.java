package platform.education.oa.model;

import platform.education.oa.utils.WebUtil;
import framework.generic.dao.Model;
/**
 * Notice
 * @author AutoCreate
 *
 */
public class Notice implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 公告标题
	 */
	private String title;
	/**
	 * 公告描述
	 */
	private String content;
	/**
	 * 公告类型
	 */
	private String type;
	/**
	 * 发布者ID
	 */
	private Integer posterId;
	/**
	 * 所属的单位,学校ID
	 */
	private Integer ownerId;
	/**
	 * 所属的单位或学校类型
	 */
	private String ownerType;
	/**
	 * 发布者用户名
	 */
	private String posterName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * appid
	 */
	private Integer appId;
	
	private Integer receiverType;
	/**
	 * 是否删除，0：还没删除，1：已经删除
	 */
	private Boolean isDeleted;
	
	//===========新版通知公告增加字段开始=================//
	/* 新增字段  ：附件  摘要   封面
	 * 其中原本表中字段  公告类型 ：用作填写  作者   公告描述：用作  正文
	 */
	
	/**
	 * 摘要
	 */
	private String digest;
	/**
	 * 上传的附件
	 */
	private String uploadFile;
	/**
	 * 封面
	 */
	private String cover;
	
	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}
	//===========新版通知公告增加字段结束=================//
	public Notice() {
		
	}
	
	public Notice(Integer id) {
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
	 * 获取公告标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置公告标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取公告描述
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置公告描述
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取公告类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置公告类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
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
	 * 获取所属的单位,学校ID
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置所属的单位,学校ID
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取所属的单位或学校类型
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置所属的单位或学校类型
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取发布者用户名
	 * @return java.lang.String
	 */
	public String getPosterName() {
		return this.posterName;
	}
	
	/**
	 * 设置发布者用户名
	 * @param posterName
	 * @type java.lang.String
	 */
	public void setPosterName(String posterName) {
		this.posterName = posterName;
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
	
	
	
	
	public Integer getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
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
	
	
    public String getContentHtml(){
    		 
    	return WebUtil.HtmltoText(content);
    }
}