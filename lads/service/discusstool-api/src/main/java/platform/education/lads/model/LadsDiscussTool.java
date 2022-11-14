package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsDiscussTool
 * @author AutoCreate
 *
 */
public class LadsDiscussTool implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 讨论内容
	 */
	private String content;
	/**
	 * 工具类型
	 */
	private String toolLibrary;
	/**
	 * 是否允许学员上传附件
	 */
	private String allowUpload;
	/**
	 * 工具id
	 */
	private String toolId;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date stopTime;
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
	
	public LadsDiscussTool() {
		
	}
	
	public LadsDiscussTool(Integer id) {
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
	 * 获取讨论内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置讨论内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取工具类型
	 * @return java.lang.String
	 */
	public String getToolLibrary() {
		return this.toolLibrary;
	}
	
	/**
	 * 设置工具类型
	 * @param toolLibrary
	 * @type java.lang.String
	 */
	public void setToolLibrary(String toolLibrary) {
		this.toolLibrary = toolLibrary;
	}
	
	/**
	 * 获取是否允许学员上传附件
	 * @return java.lang.String
	 */
	public String getAllowUpload() {
		return this.allowUpload;
	}
	
	/**
	 * 设置是否允许学员上传附件
	 * @param allowUpload
	 * @type java.lang.String
	 */
	public void setAllowUpload(String allowUpload) {
		this.allowUpload = allowUpload;
	}
	
	/**
	 * 获取工具id
	 * @return java.lang.String
	 */
	public String getToolId() {
		return this.toolId;
	}
	
	/**
	 * 设置工具id
	 * @param toolId
	 * @type java.lang.String
	 */
	public void setToolId(String toolId) {
		this.toolId = toolId;
	}
	
	/**
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	/**
	 * 设置开始时间
	 * @param startTime
	 * @type java.util.Date
	 */
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getStopTime() {
		return this.stopTime;
	}
	
	/**
	 * 设置结束时间
	 * @param stopTime
	 * @type java.util.Date
	 */
	public void setStopTime(java.util.Date stopTime) {
		this.stopTime = stopTime;
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