package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsActivityTransition
 * @author AutoCreate
 *
 */
public class LadsActivityTransition implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 前活动
	 */
	private String fromActivity;
	/**
	 * 后活动
	 */
	private String toActivity;
	/**
	 * 课程
	 */
	private String learningdesign;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public LadsActivityTransition() {
		
	}
	
	public LadsActivityTransition(Integer id) {
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
	 * 获取前活动
	 * @return java.lang.String
	 */
	public String getFromActivity() {
		return this.fromActivity;
	}
	
	/**
	 * 设置前活动
	 * @param fromActivity
	 * @type java.lang.String
	 */
	public void setFromActivity(String fromActivity) {
		this.fromActivity = fromActivity;
	}
	
	/**
	 * 获取后活动
	 * @return java.lang.String
	 */
	public String getToActivity() {
		return this.toActivity;
	}
	
	/**
	 * 设置后活动
	 * @param toActivity
	 * @type java.lang.String
	 */
	public void setToActivity(String toActivity) {
		this.toActivity = toActivity;
	}
	
	/**
	 * 获取课程
	 * @return java.lang.String
	 */
	public String getLearningdesign() {
		return this.learningdesign;
	}
	
	/**
	 * 设置课程
	 * @param learningdesign
	 * @type java.lang.String
	 */
	public void setLearningdesign(String learningdesign) {
		this.learningdesign = learningdesign;
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