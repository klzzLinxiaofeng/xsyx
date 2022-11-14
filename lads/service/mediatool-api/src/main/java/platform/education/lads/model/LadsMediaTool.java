package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsMediaTool
 * @author AutoCreate
 *
 */
public class LadsMediaTool implements Model<Integer> {
	
	
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
	 * 工具类型
	 */
	private String toolLibrary;
	/**
	 * 上传资源
	 */
	private String uploadList;
	/**
	 * entityId
	 */
	private String entityId;
	/**
	 * 工具id
	 */
	private String toolId;
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
	
	public LadsMediaTool() {
		
	}
	
	public LadsMediaTool(Integer id) {
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
	 * 获取上传资源
	 * @return java.lang.String
	 */
	public String getUploadList() {
		return this.uploadList;
	}
	
	/**
	 * 设置上传资源
	 * @param uploadList
	 * @type java.lang.String
	 */
	public void setUploadList(String uploadList) {
		this.uploadList = uploadList;
	}
	
	/**
	 * 获取entityId
	 * @return java.lang.String
	 */
	public String getEntityId() {
		return this.entityId;
	}
	
	/**
	 * 设置entityId
	 * @param entityId
	 * @type java.lang.String
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
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