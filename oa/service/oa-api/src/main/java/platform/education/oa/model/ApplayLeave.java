package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplayLeave
 * @author AutoCreate
 *
 */
public class ApplayLeave implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * appid
	 */
	private Integer appId;
	/**
	 * 公文所属的单位，学校
	 */
	private Integer ownerId;
	/**
	 * 组的类型 ， 1：学校
	 */
	private String ownerType;
	/**
	 * 申请人id
	 */
	private Integer propserId;
	/**
	 * 申请人姓名
	 */
	private String propserName;
	/**
	 * 申请人所属部门
	 */
	private Integer propserDepartmentId;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 请假类型
	 */
	private Integer leaveType;
	
	/**
	 * 开始时间
	 */
	private String startDate;
	/**
	 * 结束时间
	 */
	private String endDate;
	/**
	 * 详情
	 */
	private String detail;
	
	/**
	 *有无代课教师
	 */
	private Integer isDaike;
	
	

	/**
	 * 审批状态  0：待审批 1：已审批
	 */
	private String auditStatus;
	
	/**
	 * 审批通过状态  0：审批通过 1：审批未通过
	 */
	private String approveState;
	/**
	 * 审批人id
	 */
	private Integer approveId;
	/**
	 * 审批人姓名
	 */
	private String approveName;
	
	/**
	 * 发布日期
	 */
	private java.util.Date publishDate;
	/**
	 * 审批备注
	 */
	private String approveRemark;
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

	/**
	 * 附件uuid
	 */
	private String attachmentUuid;

	public String getAttachmentUuid() {
		return attachmentUuid;
	}

	public void setAttachmentUuid(String attachmentUuid) {
		this.attachmentUuid = attachmentUuid;
	}

	public ApplayLeave() {
		
	}
	
	public ApplayLeave(Integer id) {
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
	 * 获取UUID
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置UUID
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取appid
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置appid
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
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
	 * 获取组的类型 ， 1：学校
	 * @return java.lang.String
	 */
	public String getOwnerType() {
		return this.ownerType;
	}
	
	/**
	 * 设置组的类型 ， 1：学校
	 * @param ownerType
	 * @type java.lang.String
	 */
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	
	/**
	 * 获取申请人id
	 * @return java.lang.Integer
	 */
	public Integer getPropserId() {
		return this.propserId;
	}
	
	/**
	 * 设置申请人id
	 * @param propserId
	 * @type java.lang.Integer
	 */
	public void setPropserId(Integer propserId) {
		this.propserId = propserId;
	}
	
	/**
	 * 获取申请人姓名
	 * @return java.lang.String
	 */
	public String getPropserName() {
		return this.propserName;
	}
	
	/**
	 * 设置申请人姓名
	 * @param propserName
	 * @type java.lang.String
	 */
	public void setPropserName(String propserName) {
		this.propserName = propserName;
	}
	
	/**
	 * 获取申请人所属部门
	 * @return java.lang.Integer
	 */
	public Integer getPropserDepartmentId() {
		return this.propserDepartmentId;
	}
	
	/**
	 * 设置申请人所属部门
	 * @param propserDepartmentId
	 * @type java.lang.Integer
	 */
	public void setPropserDepartmentId(Integer propserDepartmentId) {
		this.propserDepartmentId = propserDepartmentId;
	}
	
	/**
	 * 获取标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	/**
	 * 获取请假类型
	 * @return
	 */
	public Integer getLeaveType() {
		return leaveType;
	}

	/**
	 * 设置请假类型
	 * @param leaveType
	 */
	public void setLeaveType(Integer leaveType) {
		this.leaveType = leaveType;
	}
	
	/**
	 * 获取开始时间
	 * @return String
	 */
	public String getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置开始时间
	 * @param startDate
	 * @type String
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取结束时间
	 * @return String
	 */
	public String getEndDate() {
		return this.endDate;
	}
	
	/**
	 * 设置结束时间
	 * @param endDate
	 * @type java.util.Date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * 获取详情
	 * @return java.lang.String
	 */
	public String getDetail() {
		return this.detail;
	}
	
	/**
	 * 设置详情
	 * @param detail
	 * @type java.lang.String
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	/**
	 * 获取有无教师
	 * @return
	 */
	public Integer getIsDaike() {
		return isDaike;
	}

	/**
	 * 设置有无教师
	 * @param isDaike
	 */
	public void setIsDaike(Integer isDaike) {
		this.isDaike = isDaike;
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
	 * 获取审批通过状态  0：已审批通过 1：已审批未通过  
	 * @return java.lang.String
	 */
	public String getApproveState() {
		return this.approveState;
	}
	
	/**
	 * 设置审批通过状态  0：已审批通过 1：已审批未通过  
	 * @param approveState
	 * @type java.lang.String
	 */
	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}
	
	/**
	 * 获取审批人id
	 * @return java.lang.Integer
	 */
	public Integer getApproveId() {
		return this.approveId;
	}
	
	/**
	 * 设置审批人id
	 * @param approveId
	 * @type java.lang.Integer
	 */
	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}
	
	/**
	 * 获取审批人姓名
	 * @return java.lang.String
	 */
	public String getApproveName() {
		return this.approveName;
	}
	
	/**
	 * 设置审批人姓名
	 * @param approveName
	 * @type java.lang.String
	 */
	public void setApproveName(String approveName) {
		this.approveName = approveName;
	}
	
	
	/**
	 * 获取发布日期
	 * @return java.util.Date
	 */
	public java.util.Date getPublishDate() {
		return this.publishDate;
	}
	
	/**
	 * 设置创建时间
	 * @param publishDate
	 * @type java.util.Date
	 */
	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}
	
	
	/**
	 * 获取审批备注
	 * @return java.lang.String
	 */
	public String getApproveRemark() {
		return this.approveRemark;
	}
	
	/**
	 * 设置审批备注
	 * @param approveRemark
	 * @type java.lang.String
	 */
	public void setApproveRemark(String approveRemark) {
		this.approveRemark = approveRemark;
	}
	
	/**
	 * 获取isDeleted
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置isDeleted
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
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}