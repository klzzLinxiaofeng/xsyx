package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * SyllabusLesson
 * 
 * @author AutoCreate
 *
 */
public class SyllabusLesson implements Model<Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * pj_syllabus.id
	 */
	private Integer syllabusId;
	/**
	 * 节次，第几节
	 */
	private Integer lesson;
	/**
	 * 星期几 XY-JY-XQ 0=星期日 1=星期一
	 */
	private String dayOfWeek;
	/**
	 * pj_teacher.id
	 */
	private Integer teacherId;
	/**
	 * pj_subject.code
	 */
	private String subjectCode;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * 科目名称   后期添加
	 */
	private String subjectName;
	
	private boolean isDeleted;

	private java.util.Date startDate;

	private java.util.Date endDate;

	/**
	 * 是否是默认课程 0 是 1 否
	 */
	private Integer defaultFlag;

	/**
	 * 是否是调课后的课程 0 否 1 是
	 */
	private Integer adjustFlag;
	
	public boolean getIsDeleted() {
		return isDeleted;
	}

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public Integer getAdjustFlag() {
		return adjustFlag;
	}

	public void setAdjustFlag(Integer adjustFlag) {
		this.adjustFlag = adjustFlag;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public SyllabusLesson() {

	}

	public SyllabusLesson(Integer id) {
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
	 * 获取pj_syllabus.id
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getSyllabusId() {
		return this.syllabusId;
	}

	/**
	 * 设置pj_syllabus.id
	 * 
	 * @param syllabusId
	 * @type java.lang.Integer
	 */
	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
	}

	/**
	 * 获取星期几 XY-JY-XQ 0=星期日 1=星期一
	 * 
	 * @return java.lang.String
	 */
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}

	/**
	 * 设置星期几 XY-JY-XQ 0=星期日 1=星期一
	 * 
	 * @param dayOfWeek
	 * @type java.lang.String
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * 获取pj_teacher.id
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}

	/**
	 * 设置pj_teacher.id
	 * 
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * 获取pj_subject
	 * 
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}

	/**
	 * 设置pj_subject
	 * 
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean deleted) {
		isDeleted = deleted;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("SyllabusLesson{");
		sb.append("id=").append(id);
		sb.append(", syllabusId=").append(syllabusId);
		sb.append(", lesson=").append(lesson);
		sb.append(", dayOfWeek='").append(dayOfWeek).append('\'');
		sb.append(", teacherId=").append(teacherId);
		sb.append(", subjectCode='").append(subjectCode).append('\'');
		sb.append(", createDate=").append(createDate);
		sb.append(", modifyDate=").append(modifyDate);
		sb.append(", subjectName='").append(subjectName).append('\'');
		sb.append(", isDeleted=").append(isDeleted);
		sb.append(", startDate=").append(startDate);
		sb.append(", endDate=").append(endDate);
		sb.append(", defaultFlag=").append(defaultFlag);
		sb.append(", adjustFlag=").append(adjustFlag);
		sb.append('}');
		return sb.toString();
	}

}