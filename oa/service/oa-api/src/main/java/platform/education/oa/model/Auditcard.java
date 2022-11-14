package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Auditcard
 * @author AutoCreate
 *
 */
public class Auditcard implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 借车或还车ID
	 */
	private Integer returnOrUseId;
	/**
	 * 审批人
	 */
	private Integer auditUser;
	/**
	 * 审批类型
	 */
	private String auditType;
	/**
	 * 审批意见
	 */
	private String auditOpinion;
	/**
	 * 审批日期
	 */
	private java.util.Date auditDate;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	/**
	 * 审批结果
	 */
	private String auditResult;
	
	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public Auditcard() {
		
	}
	
	public Auditcard(Integer id) {
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
	 * 获取借车或还车ID
	 * @return java.lang.Integer
	 */
	public Integer getReturnOrUseId() {
		return this.returnOrUseId;
	}
	
	/**
	 * 设置借车或还车ID
	 * @param returnOrUseId
	 * @type java.lang.Integer
	 */
	public void setReturnOrUseId(Integer returnOrUseId) {
		this.returnOrUseId = returnOrUseId;
	}
	
	/**
	 * 获取审批人
	 * @return java.lang.Integer
	 */
	public Integer getAuditUser() {
		return this.auditUser;
	}
	
	/**
	 * 设置审批人
	 * @param auditUser
	 * @type java.lang.Integer
	 */
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	
	/**
	 * 获取审批类型
	 * @return java.lang.String
	 */
	public String getAuditType() {
		return this.auditType;
	}
	
	/**
	 * 设置审批类型
	 * @param auditType
	 * @type java.lang.String
	 */
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	
	/**
	 * 获取审批意见
	 * @return java.lang.String
	 */
	public String getAuditOpinion() {
		return this.auditOpinion;
	}
	
	/**
	 * 设置审批意见
	 * @param auditOpinion
	 * @type java.lang.String
	 */
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
	
	/**
	 * 获取审批日期
	 * @return java.util.Date
	 */
	public java.util.Date getAuditDate() {
		return this.auditDate;
	}
	
	/**
	 * 设置审批日期
	 * @param auditDate
	 * @type java.util.Date
	 */
	public void setAuditDate(java.util.Date auditDate) {
		this.auditDate = auditDate;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
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
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标记
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}