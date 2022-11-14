package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Approval
 * @author AutoCreate
 *
 */
public class Approval implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 学校ID
	 */
	private Integer ownerId;
	/**
	 * 类型(单位或者学校)
	 */
	private String ownerType;
	/**
	 * 部门
	 */
	private String department;
	/**
	 * 审批人ID
	 */
	private Integer teacherId;
	/**
	 * 审批结果
	 */
	private String approvalResult;
	/**
	 * 审批说明
	 */
	private String approvalExplain;
	/**
	 * 审批次序(第一个审批的填1,第二个审批的填2....)
	 */
	private String approvalOrder;
	/**
	 * 审批项目的ID(如维修的审批,填写维修记录的ID)
	 */
	private Integer projectId;
	/**
	 * 审批类型(用于区分是哪个模块的审批)
	 */
	private String projectType;
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
	private Boolean isDelete;
	/**
	 *
	 */
	private String pictureuuId;
	/**
	 * 是否删除
	 */
	private String  pictureNames;
	/**
	 *
	 */
	private Integer isHaoCai;


	public String getPictureuuId() {
		return pictureuuId;
	}

	public void setPictureuuId(String pictureuuId) {
		this.pictureuuId = pictureuuId;
	}

	public String getPictureNames() {
		return pictureNames;
	}

	public void setPictureNames(String pictureNames) {
		this.pictureNames = pictureNames;
	}

	public Integer getIsHaoCai() {
		return isHaoCai;
	}

	public void setIsHaoCai(Integer isHaoCai) {
		this.isHaoCai = isHaoCai;
	}

	public Approval() {
		
	}
	
	public Approval(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
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
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置学校ID
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取类型(单位或者学校)
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置类型(单位或者学校)
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取部门
	 * @return java.lang.String
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	 * 设置部门
	 * @param department
	 * @type java.lang.String
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * 获取审批人ID
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置审批人ID
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取审批结果
	 * @return java.lang.String
	 */
	public String getApprovalResult() {
		return this.approvalResult;
	}
	
	/**
	 * 设置审批结果
	 * @param approvalResult
	 * @type java.lang.String
	 */
	public void setApprovalResult(String approvalResult) {
		this.approvalResult = approvalResult;
	}
	
	/**
	 * 获取审批说明
	 * @return java.lang.String
	 */
	public String getApprovalExplain() {
		return this.approvalExplain;
	}
	
	/**
	 * 设置审批说明
	 * @param approvalExplain
	 * @type java.lang.String
	 */
	public void setApprovalExplain(String approvalExplain) {
		this.approvalExplain = approvalExplain;
	}
	
	/**
	 * 获取审批次序(第一个审批的填1,第二个审批的填2....)
	 * @return java.lang.String
	 */
	public String getApprovalOrder() {
		return this.approvalOrder;
	}
	
	/**
	 * 设置审批次序(第一个审批的填1,第二个审批的填2....)
	 * @param approvalOrder
	 * @type java.lang.String
	 */
	public void setApprovalOrder(String approvalOrder) {
		this.approvalOrder = approvalOrder;
	}
	
	/**
	 * 获取审批项目的ID(如维修的审批,填写维修记录的ID)
	 * @return java.lang.String
	 */
	public Integer getProjectId() {
		return this.projectId;
	}
	
	/**
	 * 设置审批项目的ID(如维修的审批,填写维修记录的ID)
	 * @param projectId
	 * @type java.lang.String
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	
	/**
	 * 获取审批类型(用于区分是哪个模块的审批)
	 * @return java.lang.String
	 */
	public String getProjectType() {
		return this.projectType;
	}
	
	/**
	 * 设置审批类型(用于区分是哪个模块的审批)
	 * @param projectType
	 * @type java.lang.String
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
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
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}