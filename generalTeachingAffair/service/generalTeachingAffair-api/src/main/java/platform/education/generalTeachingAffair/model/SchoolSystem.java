package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * SchoolSystem
 * 
 * @author AutoCreate
 *
 */
public class SchoolSystem implements Model<Integer> {

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
	 * 学段代码
	 */
	private String stageCode;
	/**
	 * 年级代码
	 */
	private String gradeCode;
	/**
	 * 校内年级名称
	 */
	private String gradeName;
	/**
	 * 年级在学段中的顺序，如初二是2
	 */
	private Integer gradeNumber;
	/**
	 * 课程表每周安排天数
	 */
	private Integer days;
	/**
	 * 每周哪几天上班的安排次数，用星期代码表示，，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
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
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;

	public SchoolSystem() {

	}

	public SchoolSystem(Integer id) {
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
	 * 获取所在学校
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}

	/**
	 * 设置所在学校
	 * 
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * 获取学段代码
	 * 
	 * @return java.lang.String
	 */
	public String getStageCode() {
		return this.stageCode;
	}

	/**
	 * 设置学段代码
	 * 
	 * @param stageCode
	 * @type java.lang.String
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	/**
	 * 获取年级代码
	 * 
	 * @return java.lang.String
	 */
	public String getGradeCode() {
		return this.gradeCode;
	}

	/**
	 * 设置年级代码
	 * 
	 * @param gradeCode
	 * @type java.lang.String
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	/**
	 * 获取校内年级名称
	 * 
	 * @return java.lang.String
	 */
	public String getGradeName() {
		return this.gradeName;
	}

	/**
	 * 设置校内年级名称
	 * 
	 * @param gradeName
	 * @type java.lang.String
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	/**
	 * 获取年级在学段中的顺序
	 * @return
	 */
	public Integer getGradeNumber() {
		return gradeNumber;
	}

	/**
	 * 设置年级在学段中的顺序
	 * @param gradeNumber
	 */
	public void setGradeNumber(Integer gradeNumber) {
		this.gradeNumber = gradeNumber;
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
	 * 获取每周哪几天上班的安排次数，用星期代码表示，，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
	 * {"lesson":"2", "time":"09:00-09:40"}
	 * 
	 * @return java.lang.String
	 */
	public String getDaysPlan() {
		return this.daysPlan;
	}

	/**
	 * 设置每周哪几天上班的安排次数，用星期代码表示，，中间逗号分隔 {"lesson":"1", "time":"08:00-08:40"},
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
	 * 获取创建时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取修改时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}

	/**
	 * 设置修改时间
	 * 
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("SchoolSystem{");
		sb.append("id=").append(id);
		sb.append(", schoolId=").append(schoolId);
		sb.append(", stageCode='").append(stageCode).append('\'');
		sb.append(", gradeCode='").append(gradeCode).append('\'');
		sb.append(", gradeName='").append(gradeName).append('\'');
		sb.append(", gradeNumber=").append(gradeNumber);
		sb.append(", days=").append(days);
		sb.append(", daysPlan='").append(daysPlan).append('\'');
		sb.append(", lessonOfMorning=").append(lessonOfMorning);
		sb.append(", lessonOfAfternoon=").append(lessonOfAfternoon);
		sb.append(", lessonOfEvening=").append(lessonOfEvening);
		sb.append(", lessonTimes='").append(lessonTimes).append('\'');
		sb.append(", createDate=").append(createDate);
		sb.append(", modifyDate=").append(modifyDate);
		sb.append('}');
		return sb.toString();
	}
}