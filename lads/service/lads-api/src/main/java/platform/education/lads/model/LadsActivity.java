package platform.education.lads.model;

import framework.generic.dao.Model;
/**
 * LadsActivity
 * @author AutoCreate
 *
 */
public class LadsActivity implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 课程
	 */
	private String learningdesign;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 父活动
	 */
	private String parentActivity;
	/**
	 * 备注
	 */
	private String description;
	/**
	 * 活动类型
	 */
	private String activityType;
	/**
	 * 工具库类型
	 */
	private String toolLibrary;
	/**
	 * 工具id
	 */
	private String toolId;
	/**
	 * 分数
	 */
	private String score;
	/**
	 * 修课类型:   选修或必修
	 */
	private String taught;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 结束时间
	 */
	private java.util.Date stopTime;
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
	
	public LadsActivity() {
		
	}
	
	public LadsActivity(Integer id) {
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
	 * 获取父活动
	 * @return java.lang.String
	 */
	public String getParentActivity() {
		return this.parentActivity;
	}
	
	/**
	 * 设置父活动
	 * @param parentActivity
	 * @type java.lang.String
	 */
	public void setParentActivity(String parentActivity) {
		this.parentActivity = parentActivity;
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
	 * 获取活动类型
	 * @return java.lang.String
	 */
	public String getActivityType() {
		return this.activityType;
	}
	
	/**
	 * 设置活动类型
	 * @param activityType
	 * @type java.lang.String
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	/**
	 * 获取工具库类型
	 * @return java.lang.String
	 */
	public String getToolLibrary() {
		return this.toolLibrary;
	}
	
	/**
	 * 设置工具库类型
	 * @param toolLibrary
	 * @type java.lang.String
	 */
	public void setToolLibrary(String toolLibrary) {
		this.toolLibrary = toolLibrary;
	}
	
	/**
	 * 获取工具id
	 * @return java.lang.String
	 */
	public String getToolId() {
		return this.toolId;
	}
	
	/**
	 * 设置工具id
	 * @param toolId
	 * @type java.lang.String
	 */
	public void setToolId(String toolId) {
		this.toolId = toolId;
	}
	
	/**
	 * 获取分数
	 * @return java.lang.String
	 */
	public String getScore() {
		return this.score;
	}
	
	/**
	 * 设置分数
	 * @param score
	 * @type java.lang.String
	 */
	public void setScore(String score) {
		this.score = score;
	}
	
	/**
	 * 获取修课类型:   选修或必修
	 * @return java.lang.String
	 */
	public String getTaught() {
		return this.taught;
	}
	
	/**
	 * 设置修课类型:   选修或必修
	 * @param taught
	 * @type java.lang.String
	 */
	public void setTaught(String taught) {
		this.taught = taught;
	}
	
	/**
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	/**
	 * 设置开始时间
	 * @param startTime
	 * @type java.util.Date
	 */
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getStopTime() {
		return this.stopTime;
	}
	
	/**
	 * 设置结束时间
	 * @param stopTime
	 * @type java.util.Date
	 */
	public void setStopTime(java.util.Date stopTime) {
		this.stopTime = stopTime;
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