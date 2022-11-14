package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplayLeaveDepartmentCount
 * @author AutoCreate
 *
 */
public class ApplayLeaveDepartmentCount implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 请假人所属的单位：学校
	 */
	private Integer ownerId;
	/**
	 * 组的类型： 1：学校
	 */
	private String ownerType;
	/**
	 * 部门ID 
	 */
	private Integer departmentId;
	
	/**
	 * 审批状态  0：待审批 1：已审批
	 */
	private String auditStatus;
	
	/**
	 * 请假的总数
	 */
	private Integer count;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApplayLeaveDepartmentCount() {
		
	}
	
	public ApplayLeaveDepartmentCount(Integer id) {
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
	 * 获取请假人所属的单位：学校
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置请假人所属的单位：学校
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取组的类型： 1：学校
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置组的类型： 1：学校
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取部门ID 
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置部门ID 
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取审批状态  0：待审批 1：已审批
	 * @return java.lang.String
	 */
	public String getAuditStatus() {
		return this.auditStatus;
	}
	
	/**
	 * 设置审批状态 0：待审批 1：已审批
	 * @param approveState
	 * @type java.lang.String
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	/**
	 * 获取请假的总数
	 * @return java.lang.Integer
	 */
	public Integer getCount() {
		return this.count;
	}
	
	/**
	 * 设置请假的总数
	 * @param count
	 * @type java.lang.Integer
	 */
	public void setCount(Integer count) {
		this.count = count;
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