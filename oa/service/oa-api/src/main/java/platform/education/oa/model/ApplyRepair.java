package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * ApplyRepair
 * @author AutoCreate
 *
 */
public class ApplyRepair implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校ID
	 */
	private Integer shcoolId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 详情
	 */
	private String details;
	/**
	 * 地点
	 */
	private String place;
	/**
	 * 大楼ID
	 */
	private Integer bildingId;
	/**
	 * 房间ID
	 */
	private Integer roomId;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 预约时间
	 */
	private java.util.Date appointmentDate;
	/**
	 * 维修状态
	 */
	private String status;
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
	/**
	 * 申请人ID
	 */
	private Integer proposerId;
	/**
	 * 申请人姓名（冗余）
	 */
	private String proposerName;
	/**
	 * 维修完后评价上传的图片
	 */
	private String wholePicture;
	
	//=====================2016-3-7
	/**
	 *  报修部门
	 */
	private Integer departmentId;
	/**
	 * 报修编号 
	 */
	private String number;

	//=====================2021-1-11

	/**
	 * 报修类型id
	 * @return
	 */
	private Integer typeId;
	/**
	 * 报修类型
	 */
	private String typeName;
	/**
	 * 维修人userid
	 */
	private Integer weixiugong;
	/**
	 * 维修人name
	 */
	private String weixiugongName;
	/**
	 * 审核人userid
	 */
	private Integer shenherenId;
	/**
	 * 审核人name
	 */
	private String shenherenName;
	/**
	 * 图片id
	 */
	private String pictureId;
	/**
	 * 图片id
	 */
	private String pictureUrl;
	/**
	 * 报修类型
	 */
	private Integer isHaocai;
	/**
	 * 审核是否通过
	 */
	private Integer isShenhe;
	/**
	 * 失败理由
	 */
	private String liyou;

	public Integer getIsShenhe() {
		return isShenhe;
	}

	public void setIsShenhe(Integer isShenhe) {
		this.isShenhe = isShenhe;
	}

	public String getLiyou() {
		return liyou;
	}

	public void setLiyou(String liyou) {
		this.liyou = liyou;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getShenherenId() {
		return shenherenId;
	}

	public void setShenherenId(Integer shenherenId) {
		this.shenherenId = shenherenId;
	}

	public String getShenherenName() {
		return shenherenName;
	}

	public void setShenherenName(String shenherenName) {
		this.shenherenName = shenherenName;
	}

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}

	public Integer getWeixiugong() {
		return weixiugong;
	}

	public void setWeixiugong(Integer weixiugong) {
		this.weixiugong = weixiugong;
	}

	public String getWeixiugongName() {
		return weixiugongName;
	}

	public void setWeixiugongName(String weixiugongName) {
		this.weixiugongName = weixiugongName;
	}

	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Integer getIsHaocai() {
		return isHaocai;
	}

	public void setIsHaocai(Integer isHaocai) {
		this.isHaocai = isHaocai;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	//========================
	
	public Integer getBildingId() {
		return bildingId;
	}

	public void setBildingId(Integer bildingId) {
		this.bildingId = bildingId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getWholePicture() {
		return wholePicture;
	}

	public void setWholePicture(String wholePicture) {
		this.wholePicture = wholePicture;
	}

	public Integer getProposerId() {
		return proposerId;
	}

	public void setProposerId(Integer proposerId) {
		this.proposerId = proposerId;
	}

	public String getProposerName() {
		return proposerName;
	}

	public void setProposerName(String proposerName) {
		this.proposerName = proposerName;
	}

	public ApplyRepair() {
		
	}
	
	public ApplyRepair(Integer id) {
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
	 * 获取学校ID
	 * @return java.lang.String
	 */
	public Integer getShcoolId() {
		return this.shcoolId;
	}
	
	/**
	 * 设置学校ID
	 * @param shcoolId
	 * @type java.lang.String
	 */
	public void setShcoolId(Integer shcoolId) {
		this.shcoolId = shcoolId;
	}
	
	/**
	 * 获取标题
	 * @return byte[]
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置标题
	 * @param title
	 * @type byte[]
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * 获取详情
	 * @return byte[]
	 */
	public String getDetails() {
		return this.details;
	}
	
	/**
	 * 设置详情
	 * @param details
	 * @type byte[]
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/**
	 * 获取地点
	 * @return java.lang.String
	 */
	public String getPlace() {
		return this.place;
	}
	
	/**
	 * 设置地点
	 * @param place
	 * @type java.lang.String
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	
	/**
	 * 获取联系人
	 * @return byte[]
	 */
	public String getContact() {
		return this.contact;
	}
	
	/**
	 * 设置联系人
	 * @param contact
	 * @type byte[]
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * 获取联系电话
	 * @return java.lang.String
	 */
	public String getPhone() {
		return this.phone;
	}
	
	/**
	 * 设置联系电话
	 * @param phone
	 * @type java.lang.String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 获取预约时间
	 * @return java.util.Date
	 */
	public java.util.Date getAppointmentDate() {
		return this.appointmentDate;
	}
	
	/**
	 * 设置预约时间
	 * @param appointmentDate
	 * @type java.util.Date
	 */
	public void setAppointmentDate(java.util.Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	/**
	 * 获取维修状态
	 * @return java.lang.String
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * 设置维修状态
	 * @param status
	 * @type java.lang.String
	 */
	public void setStatus(String status) {
		this.status = status;
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