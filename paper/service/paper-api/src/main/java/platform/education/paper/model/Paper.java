package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * Paper
 * @author AutoCreate
 *
 */
public class Paper implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 做关联的uuid
	 */
	private String paperUuid;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 练习类型
	 */
	private Integer paperType;
	/**
	 * 总分
	 */
	private Float score;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 学段CODE
	 */
	private String stageCode;
	/**
	 * 科目CODE
	 */
	private String subjectCode;
	/**
	 * 版本CODE
	 */
	private String versionCode;
	/**
	 * 级年CODE
	 */
	private String gradeCode;
	/**
	 * 册次CODE
	 */
	private String volumeCode;
	/**
	 * 栏目CODE
	 */
	private String categoryCode;
	/**
	 * 难易度
	 */
	private Double difficulity;
	/**
	 * 知识点
	 */
	private String knowledge;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 题目数量
	 */
	private Integer questionsCount;
	/**
	 * 试卷使用次数（完成次数）
	 */
	private Integer usedCount;
	/**
	 * 文件uuid
	 */
	private String fileUuid;
	/**
	 * 文件md5
	 */
	private String fileMd5;
	/**
	 * 文件大小
	 */
	private Long fileSize;
	
	public Paper() {
		
	}
	
	public Paper(Integer id) {
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
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置做关联的uuid
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
	 * 获取练习类型
	 * @return java.lang.Integer
	 */
	public Integer getPaperType() {
		return this.paperType;
	}
	
	/**
	 * 设置练习类型
	 * @param paperType
	 * @type java.lang.Integer
	 */
	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
	}
	
	/**
	 * 获取总分
	 * @return java.lang.Double
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置总分
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Float score) {
		this.score = score;
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
	 * 获取学段CODE
	 * @return java.lang.String
	 */
	public String getStageCode() {
		return this.stageCode;
	}
	
	/**
	 * 设置学段CODE
	 * @param stageCode
	 * @type java.lang.String
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
	/**
	 * 获取科目CODE
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目CODE
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取版本CODE
	 * @return java.lang.String
	 */
	public String getVersionCode() {
		return this.versionCode;
	}
	
	/**
	 * 设置版本CODE
	 * @param versionCode
	 * @type java.lang.String
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	/**
	 * 获取级年CODE
	 * @return java.lang.String
	 */
	public String getGradeCode() {
		return this.gradeCode;
	}
	
	/**
	 * 设置级年CODE
	 * @param gradeCode
	 * @type java.lang.String
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	/**
	 * 获取册次CODE
	 * @return java.lang.String
	 */
	public String getVolumeCode() {
		return this.volumeCode;
	}
	
	/**
	 * 设置册次CODE
	 * @param volumeCode
	 * @type java.lang.String
	 */
	public void setVolumeCode(String volumeCode) {
		this.volumeCode = volumeCode;
	}
	
	/**
	 * 获取栏目CODE
	 * @return java.lang.String
	 */
	public String getCategoryCode() {
		return this.categoryCode;
	}
	
	/**
	 * 设置栏目CODE
	 * @param categoryCode
	 * @type java.lang.String
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
	/**
	 * 获取难易度
	 * @return java.lang.Double
	 */
	public Double getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度
	 * @param difficulity
	 * @type java.lang.Double
	 */
	public void setDifficulity(Double difficulity) {
		this.difficulity = difficulity;
	}
	
	/**
	 * 获取知识点
	 * @return java.lang.String
	 */
	public String getKnowledge() {
		return this.knowledge;
	}
	
	/**
	 * 设置知识点
	 * @param knowledge
	 * @type java.lang.String
	 */
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
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
	 * 获取题目数量
	 * @return java.lang.Integer
	 */
	public Integer getQuestionsCount() {
		return this.questionsCount;
	}
	
	/**
	 * 设置题目数量
	 * @param questionsCount
	 * @type java.lang.Integer
	 */
	public void setQuestionsCount(Integer questionsCount) {
		this.questionsCount = questionsCount;
	}
	
	/**
	 * 获取试卷使用次数（完成次数）
	 * @return java.lang.Integer
	 */
	public Integer getUsedCount() {
		return this.usedCount;
	}
	
	/**
	 * 设置试卷使用次数（完成次数）
	 * @param usedCount
	 * @type java.lang.Integer
	 */
	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}
	
	/**
	 * 获取文件uuid
	 * @return java.lang.String
	 */
	public String getFileUuid() {
		return this.fileUuid;
	}
	
	/**
	 * 设置文件uuid
	 * @param fileUuid
	 * @type java.lang.String
	 */
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	
	/**
	 * 获取文件md5
	 * @return java.lang.String
	 */
	public String getFileMd5() {
		return this.fileMd5;
	}
	
	/**
	 * 设置文件md5
	 * @param fileMd5
	 * @type java.lang.String
	 */
	public void setFileMd5(String fileMd5) {
		this.fileMd5 = fileMd5;
	}
	
	/**
	 * 获取文件大小
	 * @return java.lang.Long
	 */
	public Long getFileSize() {
		return this.fileSize;
	}
	
	/**
	 * 设置文件大小
	 * @param fileSize
	 * @type java.lang.Long
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
}