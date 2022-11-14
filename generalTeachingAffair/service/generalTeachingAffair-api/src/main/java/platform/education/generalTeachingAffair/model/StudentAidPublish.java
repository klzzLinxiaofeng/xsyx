package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * StudentAidPublish
 * @author AutoCreate
 *
 */
public class StudentAidPublish implements Model<Integer> {
	
	
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
	 * 学年
	 */
	private String schoolYear;
	/**
	 * 是否已公布
	 */
	private Boolean isPublish;
	/**
	 * 公布次数
	 */
	private Integer count;
	/**
	 * isDeleted
	 */
	private Boolean isDeleted;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	public StudentAidPublish() {
		
	}
	
	public StudentAidPublish(Integer id) {
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
	 * 获取学年
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取是否已公布
	 * @return java.lang.Boolean
	 */
	public Boolean getIsPublish() {
		return this.isPublish;
	}
	
	/**
	 * 设置是否已公布
	 * @param isPublish
	 * @type java.lang.Boolean
	 */
	public void setIsPublish(Boolean isPublish) {
		this.isPublish = isPublish;
	}
	
	/**
	 * 获取公布次数
	 * @return java.lang.Integer
	 */
	public Integer getCount() {
		return this.count;
	}
	
	/**
	 * 设置公布次数
	 * @param count
	 * @type java.lang.Integer
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	
	/**
	 * 获取isDeleted
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置isDeleted
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
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