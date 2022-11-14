package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * NoticeUser
 * @author AutoCreate
 *
 */
public class NoticeUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 接收者ID
	 */
	private Integer receiverId;
	/**
	 * 关联的公告ID
	 */
	private Integer noticeId;
	/**
	 * 接收者姓名或名称（冗余）
	 */
	private String receiverName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 是否删除，0：还没删除，1：已经删除
	 */
	private Boolean isDeleted;
	
	//===========新版通知公告增加字段开始=================//
		/* 新增字段  ：部门ID
		 * 作用：当用户选择的发布对象是部门的时候存入部门ID
		 */
	/**
	 * 部门Id
	 */
	private Integer departmentId;
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	//===========新版通知公告增加字段结束=================//


	public NoticeUser() {
		
	}
	
	public NoticeUser(Integer id) {
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
	 * 获取接收者ID
	 * @return java.lang.Integer
	 */
	public Integer getReceiverId() {
		return this.receiverId;
	}
	
	/**
	 * 设置接收者ID
	 * @param receiverId
	 * @type java.lang.Integer
	 */
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
	/**
	 * 获取关联的公告ID
	 * @return java.lang.Integer
	 */
	public Integer getNoticeId() {
		return this.noticeId;
	}
	
	/**
	 * 设置关联的公告ID
	 * @param noticeId
	 * @type java.lang.Integer
	 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}
	
	/**
	 * 获取接收者姓名或名称（冗余）
	 * @return java.lang.String
	 */
	public String getReceiverName() {
		return this.receiverName;
	}
	
	/**
	 * 设置接收者姓名或名称（冗余）
	 * @param receiverName
	 * @type java.lang.String
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
	 * 获取是否删除，0：还没删除，1：已经删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除，0：还没删除，1：已经删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}