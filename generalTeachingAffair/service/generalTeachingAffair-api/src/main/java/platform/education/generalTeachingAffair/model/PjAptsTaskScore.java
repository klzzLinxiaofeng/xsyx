package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTaskScore
 * @author AutoCreate
 *
 */
public class PjAptsTaskScore implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * aptsTaskUserId
	 */
	private Integer aptsTaskUserId;
	/**
	 * summaryId
	 */
	private Integer summaryId;
	/**
	 * aptsTaskJudgeId
	 */
	private Integer aptsTaskJudgeId;
	/**
	 * itemScore
	 */
	private Integer itemScore;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * isDelete
	 */
	private Boolean isDelete;
	
	public PjAptsTaskScore() {
		
	}
	
	public PjAptsTaskScore(Integer id) {
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
	 * 获取aptsTaskUserId
	 * @return java.lang.Integer
	 */
	public Integer getAptsTaskUserId() {
		return this.aptsTaskUserId;
	}
	
	/**
	 * 设置aptsTaskUserId
	 * @param aptsTaskUserId
	 * @type java.lang.Integer
	 */
	public void setAptsTaskUserId(Integer aptsTaskUserId) {
		this.aptsTaskUserId = aptsTaskUserId;
	}
	
	/**
	 * 获取summaryId
	 * @return java.lang.Integer
	 */
	public Integer getSummaryId() {
		return this.summaryId;
	}
	
	/**
	 * 设置summaryId
	 * @param summaryId
	 * @type java.lang.Integer
	 */
	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}
	
	/**
	 * 获取aptsTaskJudgeId
	 * @return java.lang.Integer
	 */
	public Integer getAptsTaskJudgeId() {
		return this.aptsTaskJudgeId;
	}
	
	/**
	 * 设置aptsTaskJudgeId
	 * @param aptsTaskJudgeId
	 * @type java.lang.Integer
	 */
	public void setAptsTaskJudgeId(Integer aptsTaskJudgeId) {
		this.aptsTaskJudgeId = aptsTaskJudgeId;
	}
	
	/**
	 * 获取itemScore
	 * @return java.lang.Integer
	 */
	public Integer getItemScore() {
		return this.itemScore;
	}
	
	/**
	 * 设置itemScore
	 * @param itemScore
	 * @type java.lang.Integer
	 */
	public void setItemScore(Integer itemScore) {
		this.itemScore = itemScore;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取isDelete
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置isDelete
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}