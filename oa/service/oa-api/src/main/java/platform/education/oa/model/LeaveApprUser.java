package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * LeaveApprUser
 * @author AutoCreate
 *
 */
public class LeaveApprUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 关联的请假ID
	 */
	private Integer leaveId;
	/**
	 * 关联的用户ID
	 */
	private Integer userId;
	/**
	 * 审批状态,为0时审批中，为1已审批
	 */
	private Integer apprState=0;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LeaveApprUser() {
		
	}
	
	public LeaveApprUser(Integer id) {
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
	 * 获取关联的请假ID
	 * @return java.lang.Integer
	 */
	public Integer getLeaveId() {
		return this.leaveId;
	}
	
	/**
	 * 设置关联的请假ID
	 * @param leaveId
	 * @type java.lang.Integer
	 */
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}
	
	/**
	 * 获取关联的用户ID
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置关联的用户ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取审批状态
	 * @return java.lang.Integer
	 */
	public Integer getApprState() {
		return this.apprState;
	}
	
	/**
	 * 设置审批状态
	 * @param apprState
	 * @type java.lang.Integer
	 */
	public void setApprState(Integer apprState) {
		this.apprState = apprState;
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