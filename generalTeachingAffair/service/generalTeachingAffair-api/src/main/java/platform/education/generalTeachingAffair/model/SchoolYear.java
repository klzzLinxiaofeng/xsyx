package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolYear
 * @author AutoCreate
 *
 */
public class SchoolYear implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主健ID
	 */
	private Integer id;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 学年的年份：用于取代ID做表关联，如果2013-2014则记录2013
	 */
	private String year;
	/**
	 * 开班时间
	 */
	private java.util.Date beginDate;
	/**
	 * 结束时间
	 */
	private java.util.Date finishDate;
	/**
	 * 删除标记
	 */
	private Boolean isDelete;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public SchoolYear() {
		
	}
	
	public SchoolYear(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主健ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主健ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取姓名
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置姓名
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取学年的年份：用于取代ID做表关联，如果2013-2014则记录2013
	 * @return java.lang.String
	 */
	public String getYear() {
		return this.year;
	}
	
	/**
	 * 设置学年的年份：用于取代ID做表关联，如果2013-2014则记录2013
	 * @param year
	 * @type java.lang.String
	 */
	public void setYear(String year) {
		this.year = year;
	}
	
	/**
	 * 获取开班时间
	 * @return java.util.Date
	 */
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * 设置开班时间
	 * @param beginDate
	 * @type java.util.Date
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置结束时间
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 获取删除标记
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置删除标记
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
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
	
}