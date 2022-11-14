package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplayIndiaDepartmentCount
 * @author AutoCreate
 *
 */
public class ApplayIndiaDepartmentCount implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 公文所属的单位，学校
	 */
	private Integer ownerId;
	/**
	 * 组的类型，1：学校
	 */
	private String ownerType;
	/**
	 * 所属部门id
	 */
	private Integer departmentId;
	/**
	 * 对应文印申请总数
	 */
	private Integer number;
	/**
	 * 文印处理状态
	 */
	private String indiaStatus;
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
	
	public ApplayIndiaDepartmentCount() {
		
	}
	
	public ApplayIndiaDepartmentCount(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * 获取所属部门id
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置所属部门id
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取对应文印申请总数
	 * @return java.lang.Integer
	 */
	public Integer getNumber() {
		return this.number;
	}
	
	/**
	 * 设置对应文印申请总数
	 * @param number
	 * @type java.lang.Integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	/**
	 * 获取文印处理状态
	 * @return java.lang.String
	 */
	public String getIndiaStatus() {
		return this.indiaStatus;
	}
	
	/**
	 * 设置文印处理状态
	 * @param indiaStatus
	 * @type java.lang.String
	 */
	public void setIndiaStatus(String indiaStatus) {
		this.indiaStatus = indiaStatus;
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