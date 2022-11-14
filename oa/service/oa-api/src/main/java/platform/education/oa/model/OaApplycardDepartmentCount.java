package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * OaApplycardDepartmentCount
 * @author AutoCreate
 *
 */
public class OaApplycardDepartmentCount implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 人数
	 */
	private Integer number;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除
	 */
	private Boolean isdelete;
	/**
	 * 状态
	 */
	private String auditStatus;
	
	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public OaApplycardDepartmentCount() {
		
	}
	
	public OaApplycardDepartmentCount(Integer id) {
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
	 * 获取学校ID
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校ID
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
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
	 * 获取人数
	 * @return java.lang.Integer
	 */
	public Integer getNumber() {
		return this.number;
	}
	
	/**
	 * 设置人数
	 * @param number
	 * @type java.lang.Integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
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
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsdelete() {
		return this.isdelete;
	}
	
	/**
	 * 设置是否删除
	 * @param isdelete
	 * @type java.lang.Boolean
	 */
	public void setIsdelete(Boolean isdelete) {
		this.isdelete = isdelete;
	}
	
}