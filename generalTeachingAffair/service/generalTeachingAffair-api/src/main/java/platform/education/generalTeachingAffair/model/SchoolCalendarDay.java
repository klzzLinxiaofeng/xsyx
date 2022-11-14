package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * SchoolCalendarDay
 * @author AutoCreate
 *
 */
public class SchoolCalendarDay implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应校历ID
	 */
	private Integer schoolCalendarId;
	/**
	 * 周次
	 */
	private Integer weekOrder;
	/**
	 * 具体日期
	 */
	private java.util.Date calendarDay;
	/**
	 * 星期几
	 */
	private String calendarWeek;
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	public SchoolCalendarDay() {
		
	}
	
	public SchoolCalendarDay(Integer id) {
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
	 * 获取对应校历ID
	 * @return java.lang.Integer
	 */
	public Integer getSchoolCalendarId() {
		return this.schoolCalendarId;
	}
	
	/**
	 * 设置对应校历ID
	 * @param schoolCalendarId
	 * @type java.lang.Integer
	 */
	public void setSchoolCalendarId(Integer schoolCalendarId) {
		this.schoolCalendarId = schoolCalendarId;
	}
	
	/**
	 * 获取周次
	 * @return java.lang.Integer
	 */
	public Integer getWeekOrder() {
		return this.weekOrder;
	}
	
	/**
	 * 设置周次
	 * @param weekOrder
	 * @type java.lang.Integer
	 */
	public void setWeekOrder(Integer weekOrder) {
		this.weekOrder = weekOrder;
	}
	
	/**
	 * 获取具体日期
	 * @return java.util.Date
	 */
	public java.util.Date getCalendarDay() {
		return this.calendarDay;
	}
	
	/**
	 * 设置具体日期
	 * @param calendarDay
	 * @type java.util.Date
	 */
	public void setCalendarDay(java.util.Date calendarDay) {
		this.calendarDay = calendarDay;
	}
	
	/**
	 * 获取星期几
	 * @return java.lang.String
	 */
	public String getCalendarWeek() {
		return this.calendarWeek;
	}
	
	/**
	 * 设置星期几
	 * @param calendarWeek
	 * @type java.lang.String
	 */
	public void setCalendarWeek(String calendarWeek) {
		this.calendarWeek = calendarWeek;
	}
	
	/**
	 * 获取类型
	 * @return java.lang.String
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * 设置类型
	 * @param type
	 * @type java.lang.String
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 获取内容
	 * @return java.lang.String
	 */
	public String getContent() {
		return this.content;
	}
	
	/**
	 * 设置内容
	 * @param content
	 * @type java.lang.String
	 */
	public void setContent(String content) {
		this.content = content;
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