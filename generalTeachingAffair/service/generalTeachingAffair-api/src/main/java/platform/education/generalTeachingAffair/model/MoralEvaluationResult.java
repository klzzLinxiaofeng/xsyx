package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * MoralEvaluationResult
 * @author AutoCreate
 *
 */
public class MoralEvaluationResult implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 评价项目ID
	 */
	private Integer itemId;
	/**
	 * 学生评价ID
	 */
	private Integer evaluationId;
	/**
	 * 评价结果
	 */
	private String result;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	public MoralEvaluationResult() {
		
	}
	
	public MoralEvaluationResult(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取评价项目ID
	 * @return java.lang.Integer
	 */
	public Integer getItemId() {
		return this.itemId;
	}
	
	/**
	 * 设置评价项目ID
	 * @param itemId
	 * @type java.lang.Integer
	 */
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	/**
	 * 获取学生评价ID
	 * @return java.lang.Integer
	 */
	public Integer getEvaluationId() {
		return this.evaluationId;
	}
	
	/**
	 * 设置学生评价ID
	 * @param evaluationId
	 * @type java.lang.Integer
	 */
	public void setEvaluationId(Integer evaluationId) {
		this.evaluationId = evaluationId;
	}
	
	/**
	 * 获取评价结果
	 * @return java.lang.String
	 */
	public String getResult() {
		return this.result;
	}
	
	/**
	 * 设置评价结果
	 * @param result
	 * @type java.lang.String
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * 获取备注
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}