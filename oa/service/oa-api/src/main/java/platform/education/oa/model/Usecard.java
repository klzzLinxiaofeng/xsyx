package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Usecard
 * @author AutoCreate
 *
 */
public class Usecard implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 用车人员
	 */
	private String cardUser;
	/**
	 * 用途
	 */
	private String application;
	/**
	 * 出车时间
	 */
	private java.util.Date useDate;
	/**
	 * 申请人
	 */
	private String proposer;
	/**
	 * 审批人
	 */
	private String approval;
	/**
	 * 申请状态
	 */
	private String status;
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
	 * 是否删除标志
	 */
	private Boolean isDelete;
	private Integer schoolId;
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Usecard() {
		
	}
	
	public Usecard(Integer id) {
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
	 * 获取车牌号
	 * @return java.lang.String
	 */
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	/**
	 * 设置车牌号
	 * @param plateNumber
	 * @type java.lang.String
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	/**
	 * 获取用车人员
	 * @return java.lang.String
	 */
	public String getCardUser() {
		return this.cardUser;
	}
	
	/**
	 * 设置用车人员
	 * @param cardUser
	 * @type java.lang.String
	 */
	public void setCardUser(String cardUser) {
		this.cardUser = cardUser;
	}
	
	/**
	 * 获取用途
	 * @return java.lang.String
	 */
	public String getApplication() {
		return this.application;
	}
	
	/**
	 * 设置用途
	 * @param application
	 * @type java.lang.String
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	
	/**
	 * 获取出车时间
	 * @return java.util.Date
	 */
	public java.util.Date getUseDate() {
		return this.useDate;
	}
	
	/**
	 * 设置出车时间
	 * @param useDate
	 * @type java.util.Date
	 */
	public void setUseDate(java.util.Date useDate) {
		this.useDate = useDate;
	}
	
	/**
	 * 获取申请人
	 * @return java.lang.String
	 */
	public String getProposer() {
		return this.proposer;
	}
	
	/**
	 * 设置申请人
	 * @param proposer
	 * @type java.lang.String
	 */
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	
	/**
	 * 获取审批人
	 * @return java.lang.String
	 */
	public String getApproval() {
		return this.approval;
	}
	
	/**
	 * 设置审批人
	 * @param approval
	 * @type java.lang.String
	 */
	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	/**
	 * 获取申请状态
	 * @return java.lang.String
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置申请状态
	 * @param status
	 * @type java.lang.String
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * 获取是否删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除标志
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}