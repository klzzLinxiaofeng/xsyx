package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * GroupTaskEvaluation
 * @author AutoCreate
 *
 */
public class GroupTaskEvaluation implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 课时
	 */
	private java.util.Date classHour;
	/**
	 * 第几节课
	 */
	private Integer classPeriod;
	/**
	 * 题目序号
	 */
	private Integer questionNum;
	/**
	 * pj_group.id
	 */
	private Integer groupId;
	/**
	 * 评分小组
	 */
	private Integer groupNumber;
	/**
	 * 对应被评分的题目
	 */
	private Integer groupTaskId;
	/**
	 * 被评分小组
	 */
	private Integer evaluateGroupNumber;
	/**
	 * 评分
	 */
	private Float score;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间（申请时间）
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public GroupTaskEvaluation() {
		
	}
	
	public GroupTaskEvaluation(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取pj_school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置pj_school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取课时
	 * @return java.util.Date
	 */
	public java.util.Date getClassHour() {
		return this.classHour;
	}
	
	/**
	 * 设置课时
	 * @param classHour
	 * @type java.util.Date
	 */
	public void setClassHour(java.util.Date classHour) {
		this.classHour = classHour;
	}
	
	/**
	 * 获取第几节课
	 * @return java.lang.Integer
	 */
	public Integer getClassPeriod() {
		return this.classPeriod;
	}
	
	/**
	 * 设置第几节课
	 * @param classPeriod
	 * @type java.lang.Integer
	 */
	public void setClassPeriod(Integer classPeriod) {
		this.classPeriod = classPeriod;
	}
	
	/**
	 * 获取题目序号
	 * @return java.lang.Integer
	 */
	public Integer getQuestionNum() {
		return this.questionNum;
	}
	
	/**
	 * 设置题目序号
	 * @param questionNum
	 * @type java.lang.Integer
	 */
	public void setQuestionNum(Integer questionNum) {
		this.questionNum = questionNum;
	}
	
	/**
	 * 获取pj_group.id
	 * @return java.lang.Integer
	 */
	public Integer getGroupId() {
		return this.groupId;
	}
	
	/**
	 * 设置pj_group.id
	 * @param groupId
	 * @type java.lang.Integer
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	/**
	 * 获取评分小组
	 * @return java.lang.Integer
	 */
	public Integer getGroupNumber() {
		return this.groupNumber;
	}
	
	/**
	 * 设置评分小组
	 * @param groupNumber
	 * @type java.lang.Integer
	 */
	public void setGroupNumber(Integer groupNumber) {
		this.groupNumber = groupNumber;
	}
	
	/**
	 * 获取对应被评分的题目
	 * @return java.lang.Integer
	 */
	public Integer getGroupTaskId() {
		return this.groupTaskId;
	}
	
	/**
	 * 设置对应被评分的题目
	 * @param groupTaskId
	 * @type java.lang.Integer
	 */
	public void setGroupTaskId(Integer groupTaskId) {
		this.groupTaskId = groupTaskId;
	}
	
	/**
	 * 获取被评分小组
	 * @return java.lang.Integer
	 */
	public Integer getEvaluateGroupNumber() {
		return this.evaluateGroupNumber;
	}
	
	/**
	 * 设置被评分小组
	 * @param evaluateGroupNumber
	 * @type java.lang.Integer
	 */
	public void setEvaluateGroupNumber(Integer evaluateGroupNumber) {
		this.evaluateGroupNumber = evaluateGroupNumber;
	}
	
	/**
	 * 获取评分
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置评分
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取创建时间（申请时间）
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间（申请时间）
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