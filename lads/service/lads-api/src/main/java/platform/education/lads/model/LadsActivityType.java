package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsActivityType
 * @author AutoCreate
 *
 */
public class LadsActivityType implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 活动类型名称
	 */
	private String activityName;
	/**
	 * 活动类型描述
	 */
	private String activityDescription;
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
	
	public LadsActivityType() {
		
	}
	
	public LadsActivityType(Integer id) {
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
	 * 获取活动类型名称
	 * @return java.lang.String
	 */
	public String getActivityName() {
		return this.activityName;
	}
	
	/**
	 * 设置活动类型名称
	 * @param activityName
	 * @type java.lang.String
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	/**
	 * 获取活动类型描述
	 * @return java.lang.String
	 */
	public String getActivityDescription() {
		return this.activityDescription;
	}
	
	/**
	 * 设置活动类型描述
	 * @param activityDescription
	 * @type java.lang.String
	 */
	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
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