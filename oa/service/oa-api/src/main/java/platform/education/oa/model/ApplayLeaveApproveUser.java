package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplayLeaveApproveUser
 * @author AutoCreate
 *
 */
public class ApplayLeaveApproveUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 请假条id
	 */
	private Integer leaveId;
	/**
	 * 审批人id
	 */
	private Integer approveId;
	/**
	 * 审批状态： 0、未审批  1、已审批
	 */
	private Integer approveState;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApplayLeaveApproveUser() {
		
	}
	
	public ApplayLeaveApproveUser(Integer id) {
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
	 * 获取请假条id
	 * @return java.lang.Integer
	 */
	public Integer getLeaveId() {
		return this.leaveId;
	}
	
	/**
	 * 设置请假条id
	 * @param leaveId
	 * @type java.lang.Integer
	 */
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
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
	 * 获取审批状态： 0、未审批  1、已审批
	 * @return Integer
	 */
	public Integer getApproveState() {
		return this.approveState;
	}
	
	/**
	 * 设置审批状态： 0、未审批  1、已审批
	 * @param approveState
	 * @type Integer
	 */
	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
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