package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Vehicle
 * @author AutoCreate
 *
 */
public class Vehicle implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * UUID
	 */
	private String uuid;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 房间名称
	 */
	private String name;
	/**
	 * 车牌号码
	 */
	private String plateNumber;
	/**
	 * 汽车类型
	 */
	private String model;
	/**
	 * 车架号
	 */
	private String frameNumber;
	/**
	 * 发动机号
	 */
	private String engineNumber;
	/**
	 * 购置日期
	 */
	private java.util.Date purchaseDate;
	/**
	 * 车辆类别
	 */
	private String vehicleType;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	/**
	 * 使用状况码
	 */
	private String serviceCondition;
	
	public String getServiceCondition() {
		return serviceCondition;
	}

	public void setServiceCondition(String serviceCondition) {
		this.serviceCondition = serviceCondition;
	}

	public Vehicle() {
		
	}
	
	public Vehicle(Integer id) {
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
	 * 获取UUID
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置UUID
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取所在学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取房间名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置房间名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取车牌号码
	 * @return java.lang.String
	 */
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	/**
	 * 设置车牌号码
	 * @param plateNumber
	 * @type java.lang.String
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	/**
	 * 获取汽车类型
	 * @return java.lang.String
	 */
	public String getModel() {
		return this.model;
	}
	
	/**
	 * 设置汽车类型
	 * @param model
	 * @type java.lang.String
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * 获取车架号
	 * @return java.lang.String
	 */
	public String getFrameNumber() {
		return this.frameNumber;
	}
	
	/**
	 * 设置车架号
	 * @param frameNumber
	 * @type java.lang.String
	 */
	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}
	
	/**
	 * 获取发动机号
	 * @return java.lang.String
	 */
	public String getEngineNumber() {
		return this.engineNumber;
	}
	
	/**
	 * 设置发动机号
	 * @param engineNumber
	 * @type java.lang.String
	 */
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	
	/**
	 * 获取购置日期
	 * @return java.util.Date
	 */
	public java.util.Date getPurchaseDate() {
		return this.purchaseDate;
	}
	
	/**
	 * 设置购置日期
	 * @param purchaseDate
	 * @type java.util.Date
	 */
	public void setPurchaseDate(java.util.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	/**
	 * 获取车辆类别
	 * @return java.lang.String
	 */
	public String getVehicleType() {
		return this.vehicleType;
	}
	
	/**
	 * 设置车辆类别
	 * @param vehicleType
	 * @type java.lang.String
	 */
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
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