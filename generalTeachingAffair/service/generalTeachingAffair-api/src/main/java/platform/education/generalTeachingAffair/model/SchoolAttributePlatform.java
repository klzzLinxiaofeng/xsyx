package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolAttributePlatform
 * @author AutoCreate
 *
 */
public class SchoolAttributePlatform implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 归类名称
	 */
	private String name;
	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标记
	 */
	private Boolean isDeleted;
	
	public SchoolAttributePlatform() {
		
	}
	
	public SchoolAttributePlatform(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取归类名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置归类名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getMemo() {
		return this.memo;
	}
	
	/**
	 * 设置备注
	 * @param memo
	 * @type java.lang.String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
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
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标记
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}