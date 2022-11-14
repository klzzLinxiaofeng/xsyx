package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsLearningdesign
 * @author AutoCreate
 *
 */
public class LadsLearningdesign implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 备注
	 */
	private String description;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 第一个活动
	 */
	private String firstActivity;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 断判该课件是否已发布,1为已发布,0或null为未发布
	 */
	private String published;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LadsLearningdesign() {
		
	}
	
	public LadsLearningdesign(Integer id) {
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
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置备注
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.String
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取第一个活动
	 * @return java.lang.String
	 */
	public String getFirstActivity() {
		return this.firstActivity;
	}
	
	/**
	 * 设置第一个活动
	 * @param firstActivity
	 * @type java.lang.String
	 */
	public void setFirstActivity(String firstActivity) {
		this.firstActivity = firstActivity;
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
	 * 获取断判该课件是否已发布,1为已发布,0或null为未发布
	 * @return java.lang.String
	 */
	public String getPublished() {
		return this.published;
	}
	
	/**
	 * 设置断判该课件是否已发布,1为已发布,0或null为未发布
	 * @param published
	 * @type java.lang.String
	 */
	public void setPublished(String published) {
		this.published = published;
	}
	
	/**
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置做关联的uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
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