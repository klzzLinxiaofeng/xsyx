package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Comments
 * @author AutoCreate
 *
 */
public class Comments implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 会议id
	 */
	private Integer meetingId;
	/**
	 * 评论内容
	 */
	private String comment;
	/**
	 * 发布人id
	 */
	private Integer createuserId;
	/**
	 * 发布人头像
	 */
	private String createuserImage;
	/**
	 * 发布人名字
	 */
	private String createname;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public Comments() {
		
	}
	
	public Comments(Integer id) {
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
	 * 获取会议id
	 * @return java.lang.Integer
	 */
	public Integer getMeetingId() {
		return this.meetingId;
	}
	
	/**
	 * 设置会议id
	 * @param meetingId
	 * @type java.lang.Integer
	 */
	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}
	
	/**
	 * 获取评论内容
	 * @return java.lang.String
	 */
	public String getComment() {
		return this.comment;
	}
	
	/**
	 * 设置评论内容
	 * @param comment
	 * @type java.lang.String
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	/**
	 * 获取发布人id
	 * @return java.lang.Integer
	 */
	public Integer getCreateuserId() {
		return this.createuserId;
	}
	
	/**
	 * 设置发布人id
	 * @param createuserId
	 * @type java.lang.Integer
	 */
	public void setCreateuserId(Integer createuserId) {
		this.createuserId = createuserId;
	}
	
	/**
	 * 获取发布人头像
	 * @return java.lang.String
	 */
	public String getCreateuserImage() {
		return this.createuserImage;
	}
	
	/**
	 * 设置发布人头像
	 * @param createuserImage
	 * @type java.lang.String
	 */
	public void setCreateuserImage(String createuserImage) {
		this.createuserImage = createuserImage;
	}
	
	/**
	 * 获取发布人名字
	 * @return java.lang.String
	 */
	public String getCreatename() {
		return this.createname;
	}
	
	/**
	 * 设置发布人名字
	 * @param createname
	 * @type java.lang.String
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
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