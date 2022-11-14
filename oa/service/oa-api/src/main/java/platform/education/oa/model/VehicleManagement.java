package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * VehicleManagement
 * @author AutoCreate
 *
 */
public class VehicleManagement implements Model<Integer> {
	
	
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
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 满载人数
	 */
	private Integer fullLoad;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 车名
	 */
	private String cardName;
	/**
	 * 购置日期
	 */
	private java.util.Date purchaseData;
	/**
	 * 使用状况
	 */
	private String serviceCondition;
	/**
	 * 封面
	 */
	private String cover;
	/**
	 * 汽车类型
	 */
	private String model;
	/**
	 * 备注
	 */
	private String remark;
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
	
	public VehicleManagement() {
		
	}
	
	public VehicleManagement(Integer id) {
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
	 * 获取学校ID
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校ID
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取满载人数
	 * @return java.lang.Integer
	 */
	public Integer getFullLoad() {
		return this.fullLoad;
	}
	
	/**
	 * 设置满载人数
	 * @param fullLoad
	 * @type java.lang.Integer
	 */
	public void setFullLoad(Integer fullLoad) {
		this.fullLoad = fullLoad;
	}
	
	/**
	 * 获取车牌号
	 * @return java.lang.String
	 */
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	/**
	 * 设置车牌号
	 * @param plateNumber
	 * @type java.lang.String
	 */
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	/**
	 * 获取车名
	 * @return java.lang.String
	 */
	public String getCardName() {
		return this.cardName;
	}
	
	/**
	 * 设置车名
	 * @param cardName
	 * @type java.lang.String
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	/**
	 * 获取购置日期
	 * @return java.util.Date
	 */
	public java.util.Date getPurchaseData() {
		return this.purchaseData;
	}
	
	/**
	 * 设置购置日期
	 * @param purchaseData
	 * @type java.util.Date
	 */
	public void setPurchaseData(java.util.Date purchaseData) {
		this.purchaseData = purchaseData;
	}
	
	/**
	 * 获取使用状况
	 * @return java.lang.String
	 */
	public String getServiceCondition() {
		return this.serviceCondition;
	}
	
	/**
	 * 设置使用状况
	 * @param serviceCondition
	 * @type java.lang.String
	 */
	public void setServiceCondition(String serviceCondition) {
		this.serviceCondition = serviceCondition;
	}
	
	/**
	 * 获取封面
	 * @return java.lang.String
	 */
	public String getCover() {
		return this.cover;
	}
	
	/**
	 * 设置封面
	 * @param cover
	 * @type java.lang.String
	 */
	public void setCover(String cover) {
		this.cover = cover;
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
	
}