package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * SummaryReadUser
 * @author AutoCreate
 *
 */
public class SummaryReadUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 纪要id
	 */
	private Integer summaryId;
	/**
	 * 已经读人id
	 */
	private Integer userId;
	/**
	 * 已经读人头像
	 */
	private String userImage;
	/**
	 * 已经读人名字
	 */
	private String userName;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public SummaryReadUser() {
		
	}
	
	public SummaryReadUser(Integer id) {
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
	 * 获取纪要id
	 * @return java.lang.Integer
	 */
	public Integer getSummaryId() {
		return this.summaryId;
	}
	
	/**
	 * 设置纪要id
	 * @param summaryId
	 * @type java.lang.Integer
	 */
	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}
	
	/**
	 * 获取已经读人id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置已经读人id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取已经读人头像
	 * @return java.lang.String
	 */
	public String getUserImage() {
		return this.userImage;
	}
	
	/**
	 * 设置已经读人头像
	 * @param userImage
	 * @type java.lang.String
	 */
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	
	/**
	 * 获取已经读人名字
	 * @return java.lang.String
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * 设置已经读人名字
	 * @param userName
	 * @type java.lang.String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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