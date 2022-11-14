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
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 练习类型
	 */
	private Integer paperType;
	/**
	 * 是否已发布
	 */
	private String publish;
	/**
	 * paperTime
	 */
	private Integer paperTime;
	/**
	 * 是否允许重做
	 */
	private Integer reDo;
	/**
	 * 总分
	 */
	private Double score;
	/**
	 * 是否允许看答案
	 */
	private Integer referAnswer;
	/**
	 * 科目CODE
	 */
	private String subjectCode;
	/**
	 * 版本CODE
	 */
	private String publishCode;
	/**
	 * 级年CODE
	 */
	private String gradeCode;
	/**
	 * 册次CODE
	 */
	private String volumeCode;
	/**
	 * 章CODE
	 */
	private String unitCode;
	/**
	 * 节CODE
	 */
	private String sectionCode;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 难易度
	 */
	private Integer difficulity;
	/**
	 * 关联书的ID
	 */
	private String bookId;
	/**
	 * 新目录保存的关联章id
	 */
	private String bookUnitId;
	/**
	 * 新目录保存的关联节id
	 */
	private String bookSectionId;
	/**
	 * 知识点
	 */
	private String knowledge;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
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
	 * 获取是否已发布
	 * @return java.lang.String
	 */
	public String getPublish() {
		return this.publish;
	}
	
	/**
	 * 设置是否已发布
	 * @param publish
	 * @type java.lang.String
	 */
	public void setPublish(String publish) {
		this.publish = publish;
	}
	
	/**
	 * 获取paperTime
	 * @return java.lang.Integer
	 */
	public Integer getPaperTime() {
		return this.paperTime;
	}
	
	/**
	 * 设置paperTime
	 * @param paperTime
	 * @type java.lang.Integer
	 */
	public void setPaperTime(Integer paperTime) {
		this.paperTime = paperTime;
	}
	
	/**
	 * 获取是否允许重做
	 * @return java.lang.Integer
	 */
	public Integer getReDo() {
		return this.reDo;
	}
	
	/**
	 * 设置是否允许重做
	 * @param reDo
	 * @type java.lang.Integer
	 */
	public void setReDo(Integer reDo) {
		this.reDo = reDo;
	}
	
	/**
	 * 获取总分
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置总分
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取是否允许看答案
	 * @return java.lang.Integer
	 */
	public Integer getReferAnswer() {
		return this.referAnswer;
	}
	
	/**
	 * 设置是否允许看答案
	 * @param referAnswer
	 * @type java.lang.Integer
	 */
	public void setReferAnswer(Integer referAnswer) {
		this.referAnswer = referAnswer;
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
	public String getPublishCode() {
		return this.publishCode;
	}
	
	/**
	 * 设置版本CODE
	 * @param publishCode
	 * @type java.lang.String
	 */
	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
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
	 * 获取章CODE
	 * @return java.lang.String
	 */
	public String getUnitCode() {
		return this.unitCode;
	}
	
	/**
	 * 设置章CODE
	 * @param unitCode
	 * @type java.lang.String
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	
	/**
	 * 获取节CODE
	 * @return java.lang.String
	 */
	public String getSectionCode() {
		return this.sectionCode;
	}
	
	/**
	 * 设置节CODE
	 * @param sectionCode
	 * @type java.lang.String
	 */
	public void setSectionCode(String sectionCode) {
		this.sectionCode = sectionCode;
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
	 * 获取难易度
	 * @return java.lang.Integer
	 */
	public Integer getDifficulity() {
		return this.difficulity;
	}
	
	/**
	 * 设置难易度
	 * @param difficulity
	 * @type java.lang.Integer
	 */
	public void setDifficulity(Integer difficulity) {
		this.difficulity = difficulity;
	}
	
	/**
	 * 获取关联书的ID
	 * @return java.lang.String
	 */
	public String getBookId() {
		return this.bookId;
	}
	
	/**
	 * 设置关联书的ID
	 * @param bookId
	 * @type java.lang.String
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	/**
	 * 获取新目录保存的关联章id
	 * @return java.lang.String
	 */
	public String getBookUnitId() {
		return this.bookUnitId;
	}
	
	/**
	 * 设置新目录保存的关联章id
	 * @param bookUnitId
	 * @type java.lang.String
	 */
	public void setBookUnitId(String bookUnitId) {
		this.bookUnitId = bookUnitId;
	}
	
	/**
	 * 获取新目录保存的关联节id
	 * @return java.lang.String
	 */
	public String getBookSectionId() {
		return this.bookSectionId;
	}
	
	/**
	 * 设置新目录保存的关联节id
	 * @param bookSectionId
	 * @type java.lang.String
	 */
	public void setBookSectionId(String bookSectionId) {
		this.bookSectionId = bookSectionId;
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