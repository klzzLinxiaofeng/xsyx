package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * Syllabus
 * 
 * @author AutoCreate
 *
 */
public class Syllabus implements Model<Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 相关学校pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 相关班级
	 */
	private Integer teamId;
	/**
	 * pj_school_year.year
	 */
	private String schoolYear;
	/**
	 * pj_school_term.code
	 */
	private String termCode;
	/**
	 * 课程表每周安排天数
	 */
	private Integer days;
	/**
	 * 每周哪几天上课的安排次数，用星期代码表示，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
	 * {"lesson":"2", "time":"09:00-09:40"}
	 */
	private String daysPlan;
	/**
	 * 上午安排节数
	 */
	private Integer lessonOfMorning;
	/**
	 * 下午安排节数
	 */
	private Integer lessonOfAfternoon;
	/**
	 * 晚上安排节数
	 */
	private Integer lessonOfEvening;
	/**
	 * 每节课的上课时间段
	 */
	private String lessonTimes;
	/**
	 * createDate
	 */
	private java.util.Date createDate;

	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;

	private Boolean isDeleted;

	public Syllabus() {

	}

	public Syllabus(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置主键
	 * 
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取相关学校pj_school.id
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}

	/**
	 * 设置相关学校pj_school.id
	 * 
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @Method getTeamId
	 * @Function 功能描述：获取班级ID
	 * @return
	 * @Date 2015年4月16日
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @Method setTeamId
	 * @Function 功能描述：设置班级ID
	 * @param teamId
	 * @Date 2015年4月16日
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	/**
	 * 获取pj_school_year.year
	 * 
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}

	/**
	 * 设置pj_school_year.year
	 * 
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * 获取pj_school_term.code
	 * 
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}

	/**
	 * 设置pj_school_term.code
	 * 
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	/**
	 * 获取课程表每周安排天数
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getDays() {
		return this.days;
	}

	/**
	 * 设置课程表每周安排天数
	 * 
	 * @param days
	 * @type java.lang.Integer
	 */
	public void setDays(Integer days) {
		this.days = days;
	}

	/**
	 * 获取每周哪几天上课的安排次数，用星期代码表示，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
	 * {"lesson":"2", "time":"09:00-09:40"}
	 * 
	 * @return java.lang.String
	 */
	public String getDaysPlan() {
		return this.daysPlan;
	}

	/**
	 * 设置每周哪几天上课的安排次数，用星期代码表示，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
	 * {"lesson":"2", "time":"09:00-09:40"}
	 * 
	 * @param daysPlan
	 * @type java.lang.String
	 */
	public void setDaysPlan(String daysPlan) {
		this.daysPlan = daysPlan;
	}

	/**
	 * 获取上午安排节数
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getLessonOfMorning() {
		return this.lessonOfMorning;
	}

	/**
	 * 设置上午安排节数
	 * 
	 * @param lessonOfMorning
	 * @type java.lang.Integer
	 */
	public void setLessonOfMorning(Integer lessonOfMorning) {
		this.lessonOfMorning = lessonOfMorning;
	}

	/**
	 * 获取下午安排节数
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getLessonOfAfternoon() {
		return this.lessonOfAfternoon;
	}

	/**
	 * 设置下午安排节数
	 * 
	 * @param lessonOfAfternoon
	 * @type java.lang.Integer
	 */
	public void setLessonOfAfternoon(Integer lessonOfAfternoon) {
		this.lessonOfAfternoon = lessonOfAfternoon;
	}

	/**
	 * 获取晚上安排节数
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getLessonOfEvening() {
		return this.lessonOfEvening;
	}

	/**
	 * 设置晚上安排节数
	 * 
	 * @param lessonOfEvening
	 * @type java.lang.Integer
	 */
	public void setLessonOfEvening(Integer lessonOfEvening) {
		this.lessonOfEvening = lessonOfEvening;
	}

	/**
	 * 获取每节课的上课时间段
	 * 
	 * @return java.lang.String
	 */
	public String getLessonTimes() {
		return this.lessonTimes;
	}

	/**
	 * 设置每节课的上课时间段
	 * 
	 * @param lessonTimes
	 * @type java.lang.String
	 */
	public void setLessonTimes(String lessonTimes) {
		this.lessonTimes = lessonTimes;
	}

	/**
	 * 获取createDate
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置createDate
	 * 
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取modifyDate
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * 设置modifyDate
	 * 
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Syllabus{");
		sb.append("id=").append(id);
		sb.append(", schoolId=").append(schoolId);
		sb.append(", teamId=").append(teamId);
		sb.append(", schoolYear='").append(schoolYear).append('\'');
		sb.append(", termCode='").append(termCode).append('\'');
		sb.append(", days=").append(days);
		sb.append(", daysPlan='").append(daysPlan).append('\'');
		sb.append(", lessonOfMorning=").append(lessonOfMorning);
		sb.append(", lessonOfAfternoon=").append(lessonOfAfternoon);
		sb.append(", lessonOfEvening=").append(lessonOfEvening);
		sb.append(", lessonTimes='").append(lessonTimes).append('\'');
		sb.append(", createDate=").append(createDate);
		sb.append(", modifyDate=").append(modifyDate);
		sb.append(", isDeleted=").append(isDeleted);
		sb.append('}');
		return sb.toString();
	}
}