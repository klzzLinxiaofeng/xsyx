package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaPaper
 * @author AutoCreate
 *
 */
public class PaPaper implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 试卷uuid
	 */
	private String paperUuid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 1=试卷，2=真题
	 */
	private Integer paperType;
	/**
	 * 总分
	 */
	private Float score;
	/**
	 * 独立题目总数
	 */
	private Integer questionCount;
	/**
	 * 所属资源库（公共库或校本库）res.resource_lib.id
	 */
	private Integer resourceLibId;
	/**
	 * 单位所有者（冗余）如果资源属主是单位学校等，说明其拥有者id，这个与资源表属主值相同 公共资源=null 校本资源=学校uuid 个人资源=
	 */
	private String ownerUid;
	/**
	 * 创建者ID
	 */
	private Integer userId;
	/**
	 * 资源拥有方式 0=公开 1=单位学校 2=个人
	 */
	private Integer ownerMode;
	/**
	 * 试卷扩展属性
	 */
	private String extraData;
	/**
	 * xep文件ID
	 */
	private String xepFileId;
	/**
	 * 试卷使用次数（教学中布置完成次数）
	 */
	private Integer usedCount;
	/**
	 * 收藏次数
	 */
	private Integer favCount;
	/**
	 * 被完成次数
	 */
	private Integer finishedCount;
	/**
	 * 是否删除标识
	 */
	private Boolean isDeleted;
	/**
	 * 试卷JSON数据
	 */
	private String paperData;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	
	private  Float difficulity;
	
	public Float getDifficulity() {
		return difficulity;
	}

	public void setDifficulity(Float difficulity) {
		this.difficulity = difficulity;
	}

	public PaPaper() {
		
	}
	
	public PaPaper(Integer id) {
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
	 * 获取试卷uuid
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置试卷uuid
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
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
	 * 获取1=试卷，2=真题
	 * @return java.lang.Integer
	 */
	public Integer getPaperType() {
		return this.paperType;
	}
	
	/**
	 * 设置1=试卷，2=真题
	 * @param paperType
	 * @type java.lang.Integer
	 */
	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}
	
	/**
	 * 获取总分
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置总分
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取独立题目总数
	 * @return java.lang.Integer
	 */
	public Integer getQuestionCount() {
		return this.questionCount;
	}
	
	/**
	 * 设置独立题目总数
	 * @param questionCount
	 * @type java.lang.Integer
	 */
	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}
	
	/**
	 * 获取所属资源库（公共库或校本库）res.resource_lib.id
	 * @return java.lang.Integer
	 */
	public Integer getResourceLibId() {
		return this.resourceLibId;
	}
	
	/**
	 * 设置所属资源库（公共库或校本库）res.resource_lib.id
	 * @param resourceLibId
	 * @type java.lang.Integer
	 */
	public void setResourceLibId(Integer resourceLibId) {
		this.resourceLibId = resourceLibId;
	}
	
	/**
	 * 获取单位所有者（冗余）如果资源属主是单位学校等，说明其拥有者id，这个与资源表属主值相同 公共资源=null 校本资源=学校uuid 个人资源=
	 * @return java.lang.String
	 */
	public String getOwnerUid() {
		return this.ownerUid;
	}
	
	/**
	 * 设置单位所有者（冗余）如果资源属主是单位学校等，说明其拥有者id，这个与资源表属主值相同 公共资源=null 校本资源=学校uuid 个人资源=
	 * @param ownerUid
	 * @type java.lang.String
	 */
	public void setOwnerUid(String ownerUid) {
		this.ownerUid = ownerUid;
	}
	
	/**
	 * 获取创建者ID
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置创建者ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取资源拥有方式 0=公开 1=单位学校 2=个人
	 * @return java.lang.Integer
	 */
	public Integer getOwnerMode() {
		return this.ownerMode;
	}
	
	/**
	 * 设置资源拥有方式 0=公开 1=单位学校 2=个人
	 * @param ownerMode
	 * @type java.lang.Integer
	 */
	public void setOwnerMode(Integer ownerMode) {
		this.ownerMode = ownerMode;
	}
	
	/**
	 * 获取试卷扩展属性
	 * @return java.lang.String
	 */
	public String getExtraData() {
		return this.extraData;
	}
	
	/**
	 * 设置试卷扩展属性
	 * @param extraData
	 * @type java.lang.String
	 */
	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	
	/**
	 * 获取xep文件ID
	 * @return java.lang.String
	 */
	public String getXepFileId() {
		return this.xepFileId;
	}
	
	/**
	 * 设置xep文件ID
	 * @param xepFileId
	 * @type java.lang.String
	 */
	public void setXepFileId(String xepFileId) {
		this.xepFileId = xepFileId;
	}
	
	/**
	 * 获取试卷使用次数（教学中布置完成次数）
	 * @return java.lang.Integer
	 */
	public Integer getUsedCount() {
		return this.usedCount;
	}
	
	/**
	 * 设置试卷使用次数（教学中布置完成次数）
	 * @param usedCount
	 * @type java.lang.Integer
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	
	/**
	 * 获取收藏次数
	 * @return java.lang.Integer
	 */
	public Integer getFavCount() {
		return this.favCount;
	}
	
	/**
	 * 设置收藏次数
	 * @param favCount
	 * @type java.lang.Integer
	 */
	public void setFavCount(Integer favCount) {
		this.favCount = favCount;
	}
	
	/**
	 * 获取被完成次数
	 * @return java.lang.Integer
	 */
	public Integer getFinishedCount() {
		return this.finishedCount;
	}
	
	/**
	 * 设置被完成次数
	 * @param finishedCount
	 * @type java.lang.Integer
	 */
	public void setFinishedCount(Integer finishedCount) {
		this.finishedCount = finishedCount;
	}
	
	/**
	 * 获取是否删除标识
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除标识
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取试卷JSON数据
	 * @return java.lang.String
	 */
	public String getPaperData() {
		return this.paperData;
	}
	
	/**
	 * 设置试卷JSON数据
	 * @param paperData
	 * @type java.lang.String
	 */
	public void setPaperData(String paperData) {
		this.paperData = paperData;
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