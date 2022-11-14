package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Repair
 * @author AutoCreate
 *
 */
public class Repair implements Model<Integer> {
	
	
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
	 * appId
	 */
	private Integer appId;
	/**
	 * 所属的单位、学校
	 */
	private Integer ownerId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 保修人
	 */
	private Integer posterId;
	/**
	 * 保修人姓名
	 */
	private String posterName;
	/**
	 * 保修时间
	 */
	private java.util.Date postTime;
	/**
	 * 保修部门
	 */
	private Integer departmentId;
	/**
	 * 保修部门（冗余）
	 */
	private String departmentName;
	/**
	 * 维修状态
	 */
	private String dealStatus;
	/**
	 * 受理人
	 */
	private String receiverId;
	/**
	 * 受理人姓名
	 */
	private String receiverName;
	/**
	 * 负责人、维修人
	 */
	private String handler;
	/**
	 * 负责人联系电话
	 */
	private String handlerPhone;
	/**
	 * 受理时间
	 */
	private java.util.Date dealTime;
	/**
	 * 维修完成时间
	 */
	private java.util.Date finishTime;
	/**
	 * 维修内容
	 */
	private String content;
	/**
	 * 评价得分
	 */
	private Float score;
	/**
	 * 评价内容
	 */
	private String comment;
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
	private String ownerType;
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwner_type(String ownerType) {
		this.ownerType = ownerType;
	}

	public Repair() {
		
	}
	
	public Repair(Integer id) {
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
	 * 获取appId
	 * @return java.lang.Integer
	 */
	public Integer getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置appId
	 * @param appId
	 * @type java.lang.Integer
	 */
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	
	/**
	 * 获取所属的单位、学校
	 * @return java.lang.String
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置所属的单位、学校
	 * @param ownerUuid
	 * @type java.lang.String
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取保修人
	 * @return java.lang.Integer
	 */
	public Integer getPosterId() {
		return this.posterId;
	}
	
	/**
	 * 设置保修人
	 * @param posterId
	 * @type java.lang.Integer
	 */
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	
	/**
	 * 获取保修人姓名
	 * @return java.lang.String
	 */
	public String getPosterName() {
		return this.posterName;
	}
	
	/**
	 * 设置保修人姓名
	 * @param posterName
	 * @type java.lang.String
	 */
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	
	/**
	 * 获取保修时间
	 * @return java.util.Date
	 */
	public java.util.Date getPostTime() {
		return this.postTime;
	}
	
	/**
	 * 设置保修时间
	 * @param postTime
	 * @type java.util.Date
	 */
	public void setPostTime(java.util.Date postTime) {
		this.postTime = postTime;
	}
	
	/**
	 * 获取保修部门
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置保修部门
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取保修部门（冗余）
	 * @return java.lang.String
	 */
	public String getDepartmentName() {
		return this.departmentName;
	}
	
	/**
	 * 设置保修部门（冗余）
	 * @param departmentName
	 * @type java.lang.String
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	/**
	 * 获取维修状态
	 * @return java.lang.String
	 */
	public String getDealStatus() {
		return this.dealStatus;
	}
	
	/**
	 * 设置维修状态
	 * @param dealStatus
	 * @type java.lang.String
	 */
	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	
	/**
	 * 获取受理人
	 * @return java.lang.String
	 */
	public String getReceiverId() {
		return this.receiverId;
	}
	
	/**
	 * 设置受理人
	 * @param receiverId
	 * @type java.lang.String
	 */
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 获取负责人、维修人
	 * @return java.lang.String
	 */
	public String getHandler() {
		return this.handler;
	}
	
	/**
	 * 设置负责人、维修人
	 * @param handler
	 * @type java.lang.String
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}
	
	/**
	 * 获取负责人联系电话
	 * @return java.lang.String
	 */
	public String getHandlerPhone() {
		return this.handlerPhone;
	}
	
	/**
	 * 设置负责人联系电话
	 * @param handlerPhone
	 * @type java.lang.String
	 */
	public void setHandlerPhone(String handlerPhone) {
		this.handlerPhone = handlerPhone;
	}
	
	/**
	 * 获取受理时间
	 * @return java.util.Date
	 */
	public java.util.Date getDealTime() {
		return this.dealTime;
	}
	
	/**
	 * 设置受理时间
	 * @param dealTime
	 * @type java.util.Date
	 */
	public void setDealTime(java.util.Date dealTime) {
		this.dealTime = dealTime;
	}
	
	/**
	 * 获取维修完成时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishTime() {
		return this.finishTime;
	}
	
	/**
	 * 设置维修完成时间
	 * @param finishTime
	 * @type java.util.Date
	 */
	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}
	
	/**
	 * 获取维修内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置维修内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * 获取评价得分
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置评价得分
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取评价内容
	 * @return java.lang.String
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * 设置评价内容
	 * @param comment
	 * @type java.lang.String
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

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