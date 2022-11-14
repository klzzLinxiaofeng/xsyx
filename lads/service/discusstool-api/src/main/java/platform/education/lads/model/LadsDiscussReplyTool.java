package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsDiscussReplyTool
 * @author AutoCreate
 *
 */
public class LadsDiscussReplyTool implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * content
	 */
	private String content;
	/**
	 * 主题
	 */
	private String discuss;
	/**
	 * 回复别人的回复
	 */
	private String parentReply;
	/**
	 * 上传附件的json，包含文件名，文件路径等信息
	 */
	private String attachment;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private Integer userId;
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
	
	public LadsDiscussReplyTool() {
		
	}
	
	public LadsDiscussReplyTool(Integer id) {
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
	 * 获取content
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置content
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 获取回复别人的回复
	 * @return java.lang.String
	 */
	public String getParentReply() {
		return this.parentReply;
	}
	
	/**
	 * 设置回复别人的回复
	 * @param parentReply
	 * @type java.lang.String
	 */
	public void setParentReply(String parentReply) {
		this.parentReply = parentReply;
	}
	
	/**
	 * 获取上传附件的json，包含文件名，文件路径等信息
	 * @return java.lang.String
	 */
	public String getAttachment() {
		return this.attachment;
	}
	
	/**
	 * 设置上传附件的json，包含文件名，文件路径等信息
	 * @param attachment
	 * @type java.lang.String
	 */
	public void setAttachment(String attachment) {
		this.attachment = attachment;
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