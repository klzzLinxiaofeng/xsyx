package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTaskJudge
 * @author AutoCreate
 *
 */
public class PjAptsTaskJudge implements Model<Integer> {
	
	
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
	 * judgeId
	 */
	private Integer judgeId;
	/**
	 * checkTime
	 */
	private java.util.Date checkTime;
	/**
	 * myScore
	 */
	private Integer myScore;
	/**
	 * description
	 */
	private String description;
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
	
	public PjAptsTaskJudge() {
		
	}
	
	public PjAptsTaskJudge(Integer id) {
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
	 * 获取judgeId
	 * @return java.lang.Integer
	 */
	public Integer getJudgeId() {
		return this.judgeId;
	}
	
	/**
	 * 设置judgeId
	 * @param judgeId
	 * @type java.lang.Integer
	 */
	public void setJudgeId(Integer judgeId) {
		this.judgeId = judgeId;
	}
	
	/**
	 * 获取checkTime
	 * @return java.util.Date
	 */
	public java.util.Date getCheckTime() {
		return this.checkTime;
	}
	
	/**
	 * 设置checkTime
	 * @param checkTime
	 * @type java.util.Date
	 */
	public void setCheckTime(java.util.Date checkTime) {
		this.checkTime = checkTime;
	}
	
	/**
	 * 获取myScore
	 * @return java.lang.Integer
	 */
	public Integer getMyScore() {
		return this.myScore;
	}
	
	/**
	 * 设置myScore
	 * @param myScore
	 * @type java.lang.Integer
	 */
	public void setMyScore(Integer myScore) {
		this.myScore = myScore;
	}
	
	/**
	 * 获取description
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置description
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
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