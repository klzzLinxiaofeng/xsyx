package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * WarrantyEquipment
 * @author AutoCreate
 *
 */
public class WarrantyEquipment implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 外键维修ID
	 */
	private Integer repairId;
	/**
	 * 过保设配名称
	 */
	private String name;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 数量
	 */
	private Integer number;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	
	public WarrantyEquipment() {
		
	}
	
	public WarrantyEquipment(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取外键维修ID
	 * @return java.lang.Integer
	 */
	public Integer getRepairId() {
		return this.repairId;
	}
	
	/**
	 * 设置外键维修ID
	 * @param repairId
	 * @type java.lang.Integer
	 */
	public void setRepairId(Integer repairId) {
		this.repairId = repairId;
	}
	
	/**
	 * 获取过保设配名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置过保设配名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取单位
	 * @return java.lang.String
	 */
	public String getUnit() {
		return this.unit;
	}
	
	/**
	 * 设置单位
	 * @param unit
	 * @type java.lang.String
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * 获取数量
	 * @return java.lang.Integer
	 */
	public Integer getNumber() {
		return this.number;
	}
	
	/**
	 * 设置数量
	 * @param number
	 * @type java.lang.Integer
	 */
	public void setNumber(Integer number) {
		this.number = number;
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
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标记
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}