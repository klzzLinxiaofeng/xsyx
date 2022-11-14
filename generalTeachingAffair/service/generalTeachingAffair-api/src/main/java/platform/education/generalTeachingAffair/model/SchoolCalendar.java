package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolCalendar
 * @author AutoCreate
 *
 */
public class SchoolCalendar implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 学期
	 */
	private String schoolTermCode;
	/**
	 * 周次
	 */
	private Integer weekNumber;
	/**
	 * 开始时间
	 */
	private java.util.Date beginDate;
	/**
	 * 结束时间
	 */
	private java.util.Date finishDate;
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
	/**
	 * 校历名字
	 */
	private String name;
	
	public SchoolCalendar() {
		
	}
	
	public SchoolCalendar(Integer id) {
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
	 * 获取学期
	 * @return java.lang.String
	 */
	public String getSchoolTermCode() {
		return this.schoolTermCode;
	}
	
	/**
	 * 设置学期
	 * @param schoolTermCode
	 * @type java.lang.String
	 */
	public void setSchoolTermCode(String schoolTermCode) {
		this.schoolTermCode = schoolTermCode;
	}
	
	/**
	 * 获取周次
	 * @return java.lang.Integer
	 */
	public Integer getWeekNumber() {
		return this.weekNumber;
	}
	
	/**
	 * 设置周次
	 * @param weekNumber
	 * @type java.lang.Integer
	 */
	public void setWeekNumber(Integer weekNumber) {
		this.weekNumber = weekNumber;
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
	
	/**
	 * 获取校历名字
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置校历名字
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}