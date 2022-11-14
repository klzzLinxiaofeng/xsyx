package platform.education.oa.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * ApplayIndia
 * @author AutoCreate
 *
 */
public class ApplayIndia implements Model<Integer> {
	
	
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
	 * 申请人id(用户id)
	 */
	private Integer proposerId;
	/**
	 * 申请人姓名(用户姓名)(冗余字段）
	 */
	private String proposerName;
	/**
	 * 申请人所属部门id
	 */
	private Integer departmentId;
	/**
	 * 处理状态  （0：待处理 1：未处理 2：处理中 3：已处理）
	 */
	private String indiaStatus;
	
	/**
	 * 联系电话
	 */
	private String mobile;
	
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 发布日期
	 */
	private java.util.Date publishDate;


	/**
	 * 申請用章時間
	 */
	private java.util.Date startDate;

	/**
	 * 結束用章時間
	 */
	private java.util.Date endDate;


	/**
	 * 申请说明
	 */
	private String remark;
	/**
	 * 上传的文件id
	 */
	private String uploadId;
	/**
	 * 提货方式   (0：送货   1：自提)
	 */
	private String deliveryMethod;
	
	/**
	 * 已处理时间
	 */
	private java.util.Date treatDate;
	
	/**
	 * 未处理事由
	 */
	private String nonTreatmentCause;
	/**
	 * 预计完成时间
	 */
	private java.util.Date expectedCompletionTime;
	/**
	 * isDeleted
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
	 * 审批人用户ID
	 */
	private Integer approverId;

	public Integer getApproverId() {
		return approverId;
	}

	public void setApproverId(Integer approverId) {
		this.approverId = approverId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public java.util.Date getTreatDate() {
		return treatDate;
	}

	public void setTreatDate(java.util.Date treatDate) {
		this.treatDate = treatDate;
	}

	
	
	public ApplayIndia() {
		
	}
	
	public ApplayIndia(Integer id) {
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
	 * 获取申请人id(教师id)
	 * @return java.lang.Integer
	 */
	public Integer getProposerId() {
		return this.proposerId;
	}
	
	/**
	 * 设置申请人id(教师id)
	 * @param proposerId
	 * @type java.lang.Integer
	 */
	public void setProposerId(Integer proposerId) {
		this.proposerId = proposerId;
	}
	
	/**
	 * 获取申请人姓名(教师姓名)(冗余字段）
	 * @return java.lang.String
	 */
	public String getProposerName() {
		return this.proposerName;
	}
	
	/**
	 * 设置申请人姓名(教师姓名)(冗余字段）
	 * @param proposerName
	 * @type java.lang.String
	 */
	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}
	
	/**
	 * 获取申请人所属部门id
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置申请人所属部门id
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取处理状态  （0：待处理 1：未处理 2：处理中 3：已处理）
	 * @return java.lang.String
	 */
	public String getIndiaStatus() {
		return this.indiaStatus;
	}
	
	/**
	 * 设置处理状态  （0：待处理 1：未处理 2：处理中 3：已处理）
	 * @param indiaStatus
	 * @type java.lang.String
	 */
	public void setIndiaStatus(String indiaStatus) {
		this.indiaStatus = indiaStatus;
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
	 * 获取发布日期
	 * @return java.util.Date
	 */
	public java.util.Date getPublishDate() {
		return this.publishDate;
	}
	
	/**
	 * 设置发布日期
	 * @param publishDate
	 * @type java.util.Date
	 */
	public void setPublishDate(java.util.Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 获取申请说明
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置申请说明
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 获取上传的文件id
	 * @return java.lang.String
	 */
	public String getUploadId() {
		return this.uploadId;
	}
	
	/**
	 * 设置上传的文件id
	 * @param uploadId
	 * @type java.lang.String
	 */
	public void setUploadId(String uploadId) {
		this.uploadId = uploadId;
	}
	
	/**
	 * 获取提货方式   (0：送货   1：自提)
	 * @return java.lang.String
	 */
	public String getDeliveryMethod() {
		return this.deliveryMethod;
	}
	
	/**
	 * 设置提货方式   (0：送货   1：自提)
	 * @param deliveryMethod
	 * @type java.lang.String
	 */
	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}
	
	/**
	 * 获取w未处理事由
	 * @return java.lang.String
	 */
	public String getNonTreatmentCause() {
		return this.nonTreatmentCause;
	}
	
	/**
	 * 设置w未处理事由
	 * @param nonTreatmentCause
	 * @type java.lang.String
	 */
	public void setNonTreatmentCause(String nonTreatmentCause) {
		this.nonTreatmentCause = nonTreatmentCause;
	}
	
	/**
	 * 获取预计完成时间
	 * @return java.util.Date
	 */
	public java.util.Date getExpectedCompletionTime() {
		return this.expectedCompletionTime;
	}
	
	/**
	 * 设置预计完成时间
	 * @param expectedCompletionTime
	 * @type java.util.Date
	 */
	public void setExpectedCompletionTime(java.util.Date expectedCompletionTime) {
		this.expectedCompletionTime = expectedCompletionTime;
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
	
}