package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * LeaveAppr
 * @author AutoCreate
 *
 */
public class LeaveAppr implements Model<Integer> {
	
	
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
	 * 审批人ID
	 */
	private Integer approvationId;
	/**
	 * 审批人
	 */
	private String approvationName;
	/**
	 * 审批意见
	 */
	private String approvation;
	/**
	 * 状态
	 */
	private Integer leavestate;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LeaveAppr() {
		
	}
	
	public LeaveAppr(Integer id) {
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
	 * 获取审批人ID
	 * @return java.lang.Integer
	 */
	public Integer getApprovationId() {
		return this.approvationId;
	}
	
	/**
	 * 设置审批人ID
	 * @param approvationId
	 * @type java.lang.Integer
	 */
	public void setApprovationId(Integer approvationId) {
		this.approvationId = approvationId;
	}
	
	/**
	 * 获取审批人
	 * @return java.lang.String
	 */
	public String getApprovationName() {
		return this.approvationName;
	}
	
	/**
	 * 设置审批人
	 * @param approvationName
	 * @type java.lang.String
	 */
	public void setApprovationName(String approvationName) {
		this.approvationName = approvationName;
	}
	
	/**
	 * 获取审批意见
	 * @return java.lang.String
	 */
	public String getApprovation() {
		return this.approvation;
	}
	
	/**
	 * 设置审批意见
	 * @param approvation
	 * @type java.lang.String
	 */
	public void setApprovation(String approvation) {
		this.approvation = approvation;
	}
	
	/**
	 * 获取状态
	 * @return java.lang.Integer
	 */
	public Integer getLeavestate() {
		return this.leavestate;
	}
	
	/**
	 * 设置状态
	 * @param leavestate
	 * @type java.lang.Integer
	 */
	public void setLeavestate(Integer leavestate) {
		this.leavestate = leavestate;
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