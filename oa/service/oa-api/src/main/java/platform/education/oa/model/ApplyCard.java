package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplyCard
 * @author AutoCreate
 *
 */
public class ApplyCard implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 申请标题
	 */
	private String title;
	/**
	 * 所申请车辆
	 */
	private Integer cardId;
	/**
	 * 申请人
	 */
	private Integer proposer;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 使用开始时间
	 */
	private java.util.Date beginDate;
	/**
	 * 使用结束时间
	 */
	private java.util.Date endDate;
	/**
	 * 发布时间
	 */
	private java.util.Date releaseDate;
	/**
	 * 审批人
	 */
	private Integer auditUser;
	/**
	 * 审批状态
	 */
	private String auditStatus;
	/**
	 * 备注
	 */
	private String remark;
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
	 * 学校ＩＤ
	 */
	private Integer schoolId;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 审批人姓名
	 */
	private String auditName;
	/**
	 * 申请人姓名
	 */
	private String proposerName;
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getProposerName() {
		return proposerName;
	}
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public ApplyCard() {
		
	}
	
	public ApplyCard(Integer id) {
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
	 * 获取申请标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置申请标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取所申请车辆
	 * @return java.lang.Integer
	 */
	public Integer getCardId() {
		return this.cardId;
	}
	
	/**
	 * 设置所申请车辆
	 * @param cardId
	 * @type java.lang.Integer
	 */
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	/**
	 * 获取申请人
	 * @return java.lang.Integer
	 */
	public Integer getProposer() {
		return this.proposer;
	}
	
	/**
	 * 设置申请人
	 * @param proposer
	 * @type java.lang.Integer
	 */
	public void setProposer(Integer proposer) {
		this.proposer = proposer;
	}
	
	/**
	 * 获取联系电话
	 * @return java.lang.String
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置联系电话
	 * @param phone
	 * @type java.lang.String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取使用开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * 设置使用开始时间
	 * @param beginDate
	 * @type java.util.Date
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取使用结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * 设置使用结束时间
	 * @param endDate
	 * @type java.util.Date
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 获取发布时间
	 * @return java.util.Date
	 */
	public java.util.Date getReleaseDate() {
		return this.releaseDate;
	}
	
	/**
	 * 设置发布时间
	 * @param releaseDate
	 * @type java.util.Date
	 */
	public void setReleaseDate(java.util.Date releaseDate) {
		this.releaseDate = releaseDate;
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
	 * 获取审批状态
	 * @return java.lang.String
	 */
	public String getAuditStatus() {
		return this.auditStatus;
	}
	
	/**
	 * 设置审批状态
	 * @param auditStatus
	 * @type java.lang.String
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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