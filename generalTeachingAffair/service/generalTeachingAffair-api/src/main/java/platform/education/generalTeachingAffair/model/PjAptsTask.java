package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTask
 * @author AutoCreate
 *
 */
public class PjAptsTask implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 学年代码
	 */
	private String schoolYear;
	/**
	 * 学期代码
	 */
	private String termCode;
	/**
	 * startDate
	 */
	private java.util.Date startDate;
	/**
	 * finishDate
	 */
	private java.util.Date finishDate;
	/**
	 * 1=按学年，2=按学期，3=按月，4=按周，5=按日，6=按课
	 */
	private Integer period;
	/**
	 * 1=打分，11=赞，15=评级
	 */
	private Integer scoringType;
	/**
	 * 有效百分比
	 */
	private Double validPercent;
	/**
	 * createUserId
	 */
	private Integer createUserId;
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
	
	private Integer evType;
	
	public Integer getEvType() {
		return evType;
	}

	public void setEvType(Integer evType) {
		this.evType = evType;
	}

	public PjAptsTask() {
		
	}
	
	public PjAptsTask(Integer id) {
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
	 * 获取学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取学年代码
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年代码
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学期代码
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学期代码
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取startDate
	 * @return java.util.Date
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置startDate
	 * @param startDate
	 * @type java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取finishDate
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置finishDate
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 获取1=按学年，2=按学期，3=按月，4=按周，5=按日，6=按课
	 * @return java.lang.Integer
	 */
	public Integer getPeriod() {
		return this.period;
	}
	
	/**
	 * 设置1=按学年，2=按学期，3=按月，4=按周，5=按日，6=按课
	 * @param period
	 * @type java.lang.Integer
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	/**
	 * 获取1=打分，11=赞，15=评级
	 * @return java.lang.Integer
	 */
	public Integer getScoringType() {
		return this.scoringType;
	}
	
	/**
	 * 设置1=打分，11=赞，15=评级
	 * @param scoringType
	 * @type java.lang.Integer
	 */
	public void setScoringType(Integer scoringType) {
		this.scoringType = scoringType;
	}
	
	/**
	 * 获取有效百分比
	 * @return java.lang.Double
	 */
	public Double getValidPercent() {
		return this.validPercent;
	}
	
	/**
	 * 设置有效百分比
	 * @param validPercent
	 * @type java.lang.Double
	 */
	public void setValidPercent(Double validPercent) {
		this.validPercent = validPercent;
	}
	
	/**
	 * 获取createUserId
	 * @return java.lang.Integer
	 */
	public Integer getCreateUserId() {
		return this.createUserId;
	}
	
	/**
	 * 设置createUserId
	 * @param createUserId
	 * @type java.lang.Integer
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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