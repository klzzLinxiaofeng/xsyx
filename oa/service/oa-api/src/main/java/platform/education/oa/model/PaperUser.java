package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * PaperUser
 * @author AutoCreate
 *
 */
public class PaperUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
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
	 * 对应的公文记录
	 */
	private Integer paperId;
	
	/**
	 * 对应的部门
	 */
	private Integer departmentId;
	
	/**
	 * 接收者id
	 */
	private Integer receiverId;
	/**
	 * 接收者名字
	 */
	private String receiverName;
	/**
	 * 是否已阅
	 */
	private Boolean readStatus;
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
	private Boolean isDeleted;
	
	public PaperUser() {
		
	}
	
	public PaperUser(Integer id) {
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
	
	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取对应的公文记录
	 * @return java.lang.Integer
	 */
	public Integer getPaperId() {
		return this.paperId;
	}
	
	/**
	 * 设置对应的公文记录
	 * @param paperId
	 * @type java.lang.Integer
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 获取接收者id
	 * @return java.lang.Integer
	 */
	public Integer getReceiverId() {
		return this.receiverId;
	}
	
	/**
	 * 设置接收者id
	 * @param receiverId
	 * @type java.lang.Integer
	 */
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 获取接收者名字
	 * @return java.lang.String
	 */
	public String getReceiverName() {
		return this.receiverName;
	}
	
	/**
	 * 设置接收者名字
	 * @param receiverName
	 * @type java.lang.String
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	/**
	 * 获取是否已阅
	 * @return java.lang.Boolean
	 */
	public Boolean getReadStatus() {
		return this.readStatus;
	}
	
	/**
	 * 设置是否已阅
	 * @param readStatus
	 * @type java.lang.Boolean
	 */
	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
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
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}