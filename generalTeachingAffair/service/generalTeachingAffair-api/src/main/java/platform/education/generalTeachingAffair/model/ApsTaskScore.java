package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsTaskScore
 * @author AutoCreate
 *
 */
public class ApsTaskScore implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的评价任务  aps_task.id
	 */
	private Integer taskId;
	/**
	 * 对应的评价任务评分细则  aps_task_item.id
	 */
	private Integer taskItemId;
	/**
	 * 评价人(这里直接是教师)  teacher.id
	 */
	private Integer judgeId;
	/**
	 * 评价目标对象类型  pj.team=班级  pj.student=学生
	 */
	private String objectType;
	/**
	 * 被评价对象的记录ID   team.id  student.id
	 */
	private Integer objectId;
	/**
	 * 评价对象的上级id  team.id对应grade.id    student.id对应team.id
	 */
	private Integer parentObjectId;
	/**
	 * 考核类型  XY-JY-JSKPLB  01=常规 02=加分  03=减分 09=其他
	 */
	private String checkType;
	/**
	 * 本项评价得分（如果是扣分项用负值）
	 */
	private Float score;
	/**
	 * 考核时间段（例如上课节数）
	 */
	private String checkRange;
	/**
	 * 考核执行的日期（按天考核就必填）
	 */
	private java.util.Date checkDate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApsTaskScore() {
		
	}
	
	public ApsTaskScore(Integer id) {
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
	 * 获取对应的评价任务  aps_task.id
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置对应的评价任务  aps_task.id
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 获取对应的评价任务评分细则  aps_task_item.id
	 * @return java.lang.Integer
	 */
	public Integer getTaskItemId() {
		return this.taskItemId;
	}
	
	/**
	 * 设置对应的评价任务评分细则  aps_task_item.id
	 * @param taskItemId
	 * @type java.lang.Integer
	 */
	public void setTaskItemId(Integer taskItemId) {
		this.taskItemId = taskItemId;
	}
	
	/**
	 * 获取评价人(这里直接是教师)  teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getJudgeId() {
		return this.judgeId;
	}
	
	/**
	 * 设置评价人(这里直接是教师)  teacher.id
	 * @param judgeId
	 * @type java.lang.Integer
	 */
	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
	}
	
	/**
	 * 获取评价目标对象类型  pj.team=班级  pj.student=学生
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置评价目标对象类型  pj.team=班级  pj.student=学生
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取被评价对象的记录ID   team.id  student.id
	 * @return java.lang.Integer
	 */
	public Integer getObjectId() {
		return this.objectId;
	}
	
	/**
	 * 设置被评价对象的记录ID   team.id  student.id
	 * @param objectId
	 * @type java.lang.Integer
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 获取评价对象的上级id  team.id对应grade.id    student.id对应team.id
	 * @return java.lang.Integer
	 */
	public Integer getParentObjectId() {
		return this.parentObjectId;
	}
	
	/**
	 * 设置评价对象的上级id  team.id对应grade.id    student.id对应team.id
	 * @param parentObjectId
	 * @type java.lang.Integer
	 */
	public void setParentObjectId(Integer parentObjectId) {
		this.parentObjectId = parentObjectId;
	}
	
	/**
	 * 获取考核类型  XY-JY-JSKPLB  01=常规 02=加分  03=减分 09=其他
	 * @return java.lang.String
	 */
	public String getCheckType() {
		return this.checkType;
	}
	
	/**
	 * 设置考核类型  XY-JY-JSKPLB  01=常规 02=加分  03=减分 09=其他
	 * @param checkType
	 * @type java.lang.String
	 */
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	
	/**
	 * 获取本项评价得分（如果是扣分项用负值）
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置本项评价得分（如果是扣分项用负值）
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取考核时间段（例如上课节数）
	 * @return java.lang.String
	 */
	public String getCheckRange() {
		return this.checkRange;
	}
	
	/**
	 * 设置考核时间段（例如上课节数）
	 * @param checkRange
	 * @type java.lang.String
	 */
	public void setCheckRange(String checkRange) {
		this.checkRange = checkRange;
	}
	
	/**
	 * 获取考核执行的日期（按天考核就必填）
	 * @return java.util.Date
	 */
	public java.util.Date getCheckDate() {
		return this.checkDate;
	}
	
	/**
	 * 设置考核执行的日期（按天考核就必填）
	 * @param checkDate
	 * @type java.util.Date
	 */
	public void setCheckDate(java.util.Date checkDate) {
		this.checkDate = checkDate;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 获取记录创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置记录创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}