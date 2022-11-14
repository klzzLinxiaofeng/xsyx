package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplayLeaveUser
 * @author AutoCreate
 *
 */
public class ApplayLeaveUser implements Model<Integer> {
	
	
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
	 * 代课教师id
	 */
	private Integer daikeId;
	/**
	 * 代课教师姓名
	 */
	private String daikeName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApplayLeaveUser() {
		
	}
	
	public ApplayLeaveUser(Integer id) {
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
	 * 获取代课教师id
	 * @return java.lang.Integer
	 */
	public Integer getDaikeId() {
		return this.daikeId;
	}
	
	/**
	 * 设置代课教师id
	 * @param daikeId
	 * @type java.lang.Integer
	 */
	public void setDaikeId(Integer daikeId) {
		this.daikeId = daikeId;
	}
	
	/**
	 * 获取代课教师姓名
	 * @return java.lang.String
	 */
	public String getDaikeName() {
		return this.daikeName;
	}
	
	/**
	 * 设置代课教师姓名
	 * @param daikeName
	 * @type java.lang.String
	 */
	public void setDaikeName(String daikeName) {
		this.daikeName = daikeName;
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