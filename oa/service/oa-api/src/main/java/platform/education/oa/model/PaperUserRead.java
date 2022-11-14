package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * OaPaperUserRead
 * @author AutoCreate
 *
 */
public class PaperUserRead implements Model<Integer> {
	
	
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
	 * 公文id
	 */
	private Integer paperId;
	/**
	 * 用户id
	 */
	private Integer userId;
	
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
	 * 删除标志
	 */
	private Integer isDeleted;
	
	public PaperUserRead() {
		
	}
	
	public PaperUserRead(Integer id) {
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
	 * 获取公文id
	 * @return java.lang.Integer
	 */
	public Integer getPaperId() {
		return this.paperId;
	}
	
	/**
	 * 设置公文id
	 * @param paperId
	 * @type java.lang.Integer
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 获取用户id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * 获取删除标志
	 * @return Integer
	 */
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type Integer
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}