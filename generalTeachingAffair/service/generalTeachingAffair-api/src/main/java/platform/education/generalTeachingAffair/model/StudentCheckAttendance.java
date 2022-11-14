package platform.education.generalTeachingAffair.model;

import java.util.Date;

import framework.generic.dao.Model;
/**
 * PjStudentCheckAttendance
 * @author AutoCreate
 *
 */
public class StudentCheckAttendance implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 所属学校
	 */
	private Integer schoolId;
	/**
	 * 所在学年ID
	 */
	private Integer schoolYearId;
	
	/**
	 * 所在年级ID
	 */
	private Integer gradeId;
	
	/**
	 * 所在班级ID
	 */
	private Integer teamNumber;
	
	


	/**
	 * 学生ID
	 */
	private Integer studentId;
	/**
	 * 学生姓名
	 */
	private String studentName;
	/**
	 * 考勤类别 ：0==事假 ; 1== 病假 ; 2== 缺课 ; 3== 旷课 ;  4== 迟到 ;  5== 早退
	 */
	private String attendanceType;
	/**
	 * 删除标志
	 */
	private Integer isDeleted;
	/**
	 * 开始时间
	 */
	private Date beginDate;
	
	/**
	 * 结束时间
	 */
	private Date endDate;
	
	
	/**
	 * 天数
	 */
	private Integer dayNumber;
	
	/**
	 * 节数
	 */
	private Integer nodeNumber;
	
	/**
	 * 次数
	 */
	private Integer orderNumber;
	
	/**
	 * 备注
	 */
	private String remark;
	
	

	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	
	
	
	public StudentCheckAttendance() {
		
	}
	
	public StudentCheckAttendance(Integer id) {
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
	 * 获取所属学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所属学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	
	/**
	 * 获取当前学年记录
	 * @return java.lang.Integer
	 */
	public Integer getSchoolYearId() {
		return schoolYearId;
	}

	/**
	 * 设置当前学年记录
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolYearId(Integer schoolYearId) {
		this.schoolYearId = schoolYearId;
	}

	
	
	/**
	 * 获取所在年级ID
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置所在年级ID
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取所在班级ID
	 * @return java.lang.Integer
	 */
	public Integer getTeamNumber() {
		return this.teamNumber;
	}
	
	/**
	 * 设置所在班级ID
	 * @param teamNumber
	 * @type java.lang.Integer
	 */
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	
	/**
	 * 获取学生ID
	 * @return java.lang.Integer
	 */
	public Integer getstudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置学生ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取学生姓名
	 * @return java.lang.String
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
	/**
	 * 设置学生姓名
	 * @param userName
	 * @type java.lang.String
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取考勤类别 ：0==事假 ; 1== 病假 ; 2== 缺课 ; 3== 旷课 ;  4== 迟到 ;  5== 早退
	 * @return java.lang.String
	 */
	public String getAttendanceType() {
		return this.attendanceType;
	}
	
	/**
	 * 设置考勤类别 ：0==事假 ; 1== 病假 ; 2== 缺课 ; 3== 旷课 ;  4== 迟到 ;  5== 早退
	 * @param attendanceType
	 * @type java.lang.String
	 */
	public void setAttendanceType(String attendanceType) {
		this.attendanceType = attendanceType;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Integer
	 */
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type java.lang.Integer
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}
	
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * 设置结束时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}

	public Integer getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(Integer nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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