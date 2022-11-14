package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsAppTool
 * @author AutoCreate
 *
 */
public class LadsAppTool implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * appId
	 */
	private String appId;
	/**
	 * toolIds
	 */
	private String toolIds;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LadsAppTool() {
		
	}
	
	public LadsAppTool(Integer id) {
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
	 * 获取appId
	 * @return java.lang.String
	 */
	public String getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置appId
	 * @param appId
	 * @type java.lang.String
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取toolIds
	 * @return java.lang.String
	 */
	public String getToolIds() {
		return this.toolIds;
	}
	
	/**
	 * 设置toolIds
	 * @param toolIds
	 * @type java.lang.String
	 */
	public void setToolIds(String toolIds) {
		this.toolIds = toolIds;
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