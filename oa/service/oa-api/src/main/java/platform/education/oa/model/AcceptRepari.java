package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * AcceptRepari
 * @author AutoCreate
 *
 */
public class AcceptRepari implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 维修人ID
	 */
	private Integer accepterId;
	/**
	 * 维修人姓名
	 */
	private String accepterName;
	/**
	 * 维修人联系电话
	 */
	private String phone;
	/**
	 * 维修时间
	 */
	private java.util.Date acceptDate;
	/**
	 * 评价星级
	 */
	private Integer appraise;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 图片
	 */
	private String picture;
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
	 * 维修申请
	 */
	private Integer repariId;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	
	//======2016-3-8=========
	/**
	 * 解决办法 
	 */
	private String solution;
	/**
	 * 解决办法
	 */
	private Integer  isHaoCai;

	public Integer getIsHaoCai() {
		return isHaoCai;
	}

	public void setIsHaoCai(Integer isHaoCai) {
		this.isHaoCai = isHaoCai;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	//======2016-3-8=============
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public AcceptRepari() {
		
	}
	
	public AcceptRepari(Integer id) {
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
	 * 获取维修人ID
	 * @return java.lang.Integer
	 */
	public Integer getAccepterId() {
		return this.accepterId;
	}
	
	/**
	 * 设置维修人ID
	 * @param accepterId
	 * @type java.lang.Integer
	 */
	public void setAccepterId(Integer accepterId) {
		this.accepterId = accepterId;
	}
	
	/**
	 * 获取维修人姓名
	 * @return java.lang.String
	 */
	public String getAccepterName() {
		return this.accepterName;
	}
	
	/**
	 * 设置维修人姓名
	 * @param accepterName
	 * @type java.lang.String
	 */
	public void setAccepterName(String accepterName) {
		this.accepterName = accepterName;
	}
	
	/**
	 * 获取维修人联系电话
	 * @return java.lang.String
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置维修人联系电话
	 * @param phone
	 * @type java.lang.String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取维修时间
	 * @return java.util.Date
	 */
	public java.util.Date getAcceptDate() {
		return this.acceptDate;
	}
	
	/**
	 * 设置维修时间
	 * @param acceptDate
	 * @type java.util.Date
	 */
	public void setAcceptDate(java.util.Date acceptDate) {
		this.acceptDate = acceptDate;
	}
	
	/**
	 * 获取评价星级
	 * @return java.lang.Integer
	 */
	public Integer getAppraise() {
		return this.appraise;
	}
	
	/**
	 * 设置评价星级
	 * @param appraise
	 * @type java.lang.Integer
	 */
	public void setAppraise(Integer appraise) {
		this.appraise = appraise;
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
	 * 获取图片
	 * @return java.lang.String
	 */
	public String getPicture() {
		return this.picture;
	}
	
	/**
	 * 设置图片
	 * @param picture
	 * @type java.lang.String
	 */
	public void setPicture(String picture) {
		this.picture = picture;
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
	
	/**
	 * 获取维修申请
	 * @return java.lang.Integer
	 */
	public Integer getRepariId() {
		return this.repariId;
	}
	
	/**
	 * 设置维修申请
	 * @param repariId
	 * @type java.lang.Integer
	 */
	public void setRepariId(Integer repariId) {
		this.repariId = repariId;
	}
	
}