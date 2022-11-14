package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Device
 * @author AutoCreate
 *
 */
public class Device implements Model<Integer> {
	
	
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
	 * 编号
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 名称
	 */
	private String englishName;
	/**
	 * 产权码
	 */
	private String propertyRight;
	/**
	 * 使用状况码
	 */
	private String serviceCondition;
	/**
	 * 分类
	 */
	private String category;
	/**
	 * 型号
	 */
	private String model;
	/**
	 * 出厂时间
	 */
	private java.util.Date exFactoryDate;
	/**
	 * 购置日期
	 */
	private java.util.Date purchaseDate;
	/**
	 * 生产厂家
	 */
	private String manufacturer;
	/**
	 * 设备来源
	 */
	private String sourceType;
	/**
	 * 单据号
	 */
	private String documentNumber;
	/**
	 * 仪器地点
	 */
	private String place;
	/**
	 * 所在建筑物
	 */
	private Integer blidingId;
	/**
	 * 所在房间
	 */
	private Integer roomId;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 保修期截止日期
	 */
	private java.util.Date warrantyExpDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除状态
	 */
	private Boolean isDelete;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 设备数量
	 */
	private Integer totalNumber;
	
	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Device() {
		
	}
	
	public Device(Integer id) {
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
	 * 获取编号
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * 设置编号
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getEnglishName() {
		return this.englishName;
	}
	
	/**
	 * 设置名称
	 * @param englishName
	 * @type java.lang.String
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	/**
	 * 获取产权码
	 * @return java.lang.String
	 */
	public String getPropertyRight() {
		return this.propertyRight;
	}
	
	/**
	 * 设置产权码
	 * @param propertyRight
	 * @type java.lang.String
	 */
	public void setPropertyRight(String propertyRight) {
		this.propertyRight = propertyRight;
	}
	
	/**
	 * 获取使用状况码
	 * @return java.lang.String
	 */
	public String getServiceCondition() {
		return this.serviceCondition;
	}
	
	/**
	 * 设置使用状况码
	 * @param serviceCondition
	 * @type java.lang.String
	 */
	public void setServiceCondition(String serviceCondition) {
		this.serviceCondition = serviceCondition;
	}
	
	/**
	 * 获取分类
	 * @return java.lang.String
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * 设置分类
	 * @param category
	 * @type java.lang.String
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	
	/**
	 * 获取型号
	 * @return java.lang.String
	 */
	public String getModel() {
		return this.model;
	}
	
	/**
	 * 设置型号
	 * @param model
	 * @type java.lang.String
	 */
	public void setModel(String model) {
		this.model = model;
	}
	
	/**
	 * 获取出厂时间
	 * @return java.util.Date
	 */
	public java.util.Date getExFactoryDate() {
		return this.exFactoryDate;
	}
	
	/**
	 * 设置出厂时间
	 * @param exFactoryDate
	 * @type java.util.Date
	 */
	public void setExFactoryDate(java.util.Date exFactoryDate) {
		this.exFactoryDate = exFactoryDate;
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
	 * 获取生产厂家
	 * @return java.lang.String
	 */
	public String getManufacturer() {
		return this.manufacturer;
	}
	
	/**
	 * 设置生产厂家
	 * @param manufacturer
	 * @type java.lang.String
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	/**
	 * 获取设备来源
	 * @return java.lang.String
	 */
	public String getSourceType() {
		return this.sourceType;
	}
	
	/**
	 * 设置设备来源
	 * @param sourceType
	 * @type java.lang.String
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	
	/**
	 * 获取单据号
	 * @return java.lang.String
	 */
	public String getDocumentNumber() {
		return this.documentNumber;
	}
	
	/**
	 * 设置单据号
	 * @param documentNumber
	 * @type java.lang.String
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	/**
	 * 获取仪器地点
	 * @return java.lang.String
	 */
	public String getPlace() {
		return this.place;
	}
	
	/**
	 * 设置仪器地点
	 * @param place
	 * @type java.lang.String
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * 获取所在建筑物
	 * @return java.lang.Integer
	 */
	public Integer getBlidingId() {
		return this.blidingId;
	}
	
	/**
	 * 设置所在建筑物
	 * @param blidingId
	 * @type java.lang.Integer
	 */
	public void setBlidingId(Integer blidingId) {
		this.blidingId = blidingId;
	}
	
	/**
	 * 获取所在房间
	 * @return java.lang.Integer
	 */
	public Integer getRoomId() {
		return this.roomId;
	}
	
	/**
	 * 设置所在房间
	 * @param roomId
	 * @type java.lang.Integer
	 */
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	/**
	 * 获取价格
	 * @return java.lang.String
	 */
	public String getPrice() {
		return this.price;
	}
	
	/**
	 * 设置价格
	 * @param price
	 * @type java.lang.String
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
	/**
	 * 获取保修期截止日期
	 * @return java.util.Date
	 */
	public java.util.Date getWarrantyExpDate() {
		return this.warrantyExpDate;
	}
	
	/**
	 * 设置保修期截止日期
	 * @param warrantyExpDate
	 * @type java.util.Date
	 */
	public void setWarrantyExpDate(java.util.Date warrantyExpDate) {
		this.warrantyExpDate = warrantyExpDate;
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
	 * 获取删除状态
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除状态
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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