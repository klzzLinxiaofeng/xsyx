package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTaskItemSummary
 * @author AutoCreate
 *
 */
public class PjAptsTaskItemSummary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 教师任务id
	 */
	private Integer aptsTaskUserId;
	/**
	 * 任务名称
	 */
	private String taskItemName;
	/**
	 * 有效分数
	 */
	private Integer effectiveScore;
	/**
	 * 总分
	 */
	private Integer score;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除
	 */
	private Boolean isDelete;
	
	private  Integer listOrder;
	
	public Integer getListOrder() {
		return listOrder;
	}

	public void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	public PjAptsTaskItemSummary() {
		
	}
	
	public PjAptsTaskItemSummary(Integer id) {
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
	 * 获取教师任务id
	 * @return java.lang.Integer
	 */
	public Integer getAptsTaskUserId() {
		return this.aptsTaskUserId;
	}
	
	/**
	 * 设置教师任务id
	 * @param aptsTaskUserId
	 * @type java.lang.Integer
	 */
	public void setAptsTaskUserId(Integer aptsTaskUserId) {
		this.aptsTaskUserId = aptsTaskUserId;
	}
	
	/**
	 * 获取任务名称
	 * @return java.lang.String
	 */
	public String getTaskItemName() {
		return this.taskItemName;
	}
	
	/**
	 * 设置任务名称
	 * @param taskItemName
	 * @type java.lang.String
	 */
	public void setTaskItemName(String taskItemName) {
		this.taskItemName = taskItemName;
	}
	
	/**
	 * 获取有效分数
	 * @return java.lang.Integer
	 */
	public Integer getEffectiveScore() {
		return this.effectiveScore;
	}
	
	/**
	 * 设置有效分数
	 * @param effectiveScore
	 * @type java.lang.Integer
	 */
	public void setEffectiveScore(Integer effectiveScore) {
		this.effectiveScore = effectiveScore;
	}
	
	/**
	 * 获取总分
	 * @return java.lang.Integer
	 */
	public Integer getScore() {
		return this.score;
	}
	
	/**
	 * 设置总分
	 * @param score
	 * @type java.lang.Integer
	 */
	public void setScore(Integer score) {
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
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}