package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * JobControl
 * @author AutoCreate
 *
 */
public class JobControl implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 适用的App
	 */
	private String appKey;
	/**
	 * 业务名称
	 */
	private String name;
	/**
	 * 目标记录ID
	 */
	private Integer objectId;
	/**
	 * 开关
	 */
	private Boolean interrupteur;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 删除标志
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
	
	public JobControl() {
		
	}
	
	public JobControl(Integer id) {
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
	 * 获取适用的App
	 * @return java.lang.String
	 */
	public String getAppKey() {
		return this.appKey;
	}
	
	/**
	 * 设置适用的App
	 * @param appKey
	 * @type java.lang.String
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	/**
	 * 获取业务名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置业务名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取目标记录ID
	 * @return java.lang.Integer
	 */
	public Integer getObjectId() {
		return this.objectId;
	}
	
	/**
	 * 设置目标记录ID
	 * @param objectId
	 * @type java.lang.Integer
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 获取开关
	 * @return java.lang.Boolean
	 */
	public Boolean getInterrupteur() {
		return this.interrupteur;
	}
	
	/**
	 * 设置开关
	 * @param interrupteur
	 * @type java.lang.Boolean
	 */
	public void setInterrupteur(Boolean interrupteur) {
		this.interrupteur = interrupteur;
	}
	
	/**
	 * 获取状态
	 * @return java.lang.String
	 */
	public String getState() {
		return this.state;
	}
	
	/**
	 * 设置状态
	 * @param state
	 * @type java.lang.String
	 */
	public void setState(String state) {
		this.state = state;
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