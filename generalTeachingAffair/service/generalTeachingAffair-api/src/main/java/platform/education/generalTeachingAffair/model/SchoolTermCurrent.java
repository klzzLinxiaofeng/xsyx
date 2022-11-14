package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolTermCurrent
 * @author AutoCreate
 *
 */
public class SchoolTermCurrent implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 对应的学年记录
	 */
	private Integer schoolYearId;
	/**
	 * 学年名称
	 */
	private String schoolYearName;
	/**
	 * 学年的开始年份
	 */
	private String schoolYear;
	/**
	 * 对应的学期记录
	 */
	private String schoolTermCode;
	/**
	 * 对应的学期名称
	 */
	private String schoolTermName;
	/**
	 * 开始时间
	 */
	private java.util.Date beginDate;
	/**
	 * 结束时间
	 */
	private java.util.Date finishDate;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	

	public SchoolTermCurrent() {
		
	}
	
	public SchoolTermCurrent(Integer id) {
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
	 * 获取对应的学年记录
	 * @return java.lang.Integer
	 */
	public Integer getSchoolYearId() {
		return this.schoolYearId;
	}
	
	/**
	 * 设置对应的学年记录
	 * @param scoolYearId
	 * @type java.lang.Integer
	 */
	public void setSchoolYearId(Integer schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	/**
	 * 获取学年名称
	 * @return java.lang.String
	 */
	public String getSchoolYearName() {
		return this.schoolYearName;
	}
	
	/**
	 * 设置学年名称
	 * @param schoolYearName
	 * @type java.lang.String
	 */
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	
	/**
	 * 获取学年的开始年份
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年的开始年份
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学期记录
	 */
	public String getSchoolTermCode() {
		return schoolTermCode;
	}

	/**
	 * 设置学期记录
	 */
	public void setSchoolTermCode(String schoolTermCode) {
		this.schoolTermCode = schoolTermCode;
	}
	
	/**
	 * 获取学期名称
	 */
	public String getSchoolTermName(){
		return schoolTermName;
	}
	
	/**
	 * 设置学期名称
	 */
	public void setSchoolTermName(String schoolTermName){
		this.schoolTermName = schoolTermName;
	}
	
	/**
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}
	
	/**
	 * 设置开始时间
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
	
}