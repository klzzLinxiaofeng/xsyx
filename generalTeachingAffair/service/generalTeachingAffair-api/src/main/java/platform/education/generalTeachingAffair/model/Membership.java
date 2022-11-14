package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * Membership
 * @author AutoCreate
 *
 */
public class Membership implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 父节点（自关联）
	 */
	private Integer parentId;
	/**
	 * 本节点的唯一标识
	 */
	private String uuid;
	/**
	 * 所属APP（借点只定位到产品级）
	 */
	private String appKey;
	/**
	 * 节点属主类型 说明本节点来源类型，如学校、部门等
	 */
	private String ownerType;
	/**
	 * 节点属性的表示ID/UUID
	 */
	private String ownerId;
	/**
	 * 行政区域  单位级别的节点说明其区域位置
	 */
	private String regionCode;
	/**
	 * 同一级的节点的排序
	 */
	private Integer sortOrder;
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
	
	public Membership() {
		
	}
	
	public Membership(Integer id) {
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
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取父节点（自关联）
	 * @return java.lang.Integer
	 */
	public Integer getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置父节点（自关联）
	 * @param parentId
	 * @type java.lang.Integer
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取本节点的唯一标识
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置本节点的唯一标识
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取所属APP（借点只定位到产品级）
	 * @return java.lang.Integer
	 */
	public String getAppKey() {
		return this.appKey;
	}
	
	/**
	 * 设置所属APP（借点只定位到产品级）
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	
	/**
	 * 获取节点属主类型 说明本节点来源类型，如学校、部门等
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置节点属主类型 说明本节点来源类型，如学校、部门等
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取节点属性的表示ID/UUID
	 * @return java.lang.String
	 */
	public String getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置节点属性的表示ID/UUID
	 * @param ownerId
	 * @type java.lang.String
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取行政区域  单位级别的节点说明其区域位置
	 * @return java.lang.String
	 */
	public String getRegionCode() {
		return this.regionCode;
	}
	
	/**
	 * 设置行政区域  单位级别的节点说明其区域位置
	 * @param regionCode
	 * @type java.lang.String
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	/**
	 * 获取同一级的节点的排序
	 * @return java.lang.Integer
	 */
	public Integer getSortOrder() {
		return this.sortOrder;
	}
	
	/**
	 * 设置同一级的节点的排序
	 * @param sortOrder
	 * @type java.lang.Integer
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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