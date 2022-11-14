package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * Team
 * 
 * @author AutoCreate
 *
 */
public class Team implements Model<Integer> {

	private static final long serialVersionUID = 1L;
	/**
	 * 主健ID
	 */
	private Integer id;
	/**
	 * 所属学校
	 */
	private Integer schoolId;
	/**
	 * 所属班级
	 */
	private Integer gradeId;
	/**
	 * 编号=gradeNumber+teamNumber:由年级编号和顺序组号，分隔符为减号
	 */
	private String code;
	/**
	 * 校内编号，学校唯一而且不随年级的升级而改变
	 * 由学段代码+入学学年+顺级顺序号组成
	 */
	private String code2;
	
	/**
	 * 2016-8-9 新增班级uuid
	 * @return
	 */
	private String uuid;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	/**
	 * 班级名称
	 */
	private String name;
	/**
	 * 标准班级名称
	 */
	private String fullName;

	/**
	 * 班级类别
	 */
	private String teamType;

	/**
	 * 班级属性
	 */
	private String teamProperty;

	private Boolean isDelete;

	/**
	 * 学年的第一部份
	 */
	private String schoolYear;

	/**
	 * 班号：在同一年级中的顺序编号
	 */
	private Integer teamNumber;
	/**
	 * 班级成员数
	 */
	private Integer memberCount;
	/**
	 * 开始时间
	 */
	private java.util.Date beginDate;
	/**
	 * 结束时间
	 */
	private java.util.Date finishDate;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 班主任
	 */
	private String classTeacherName;
	
	/**
	 * 班级学生人数（2015-12-24）
	 */
	private String studentCount;
	
	/**
	 * 班级教师人数（2015-12-24）
	 */
	private String teacherCount;


	private Integer isSendSeewo;

	private Integer IsSendHikdoor;

	private String gradeName;

	public Integer getIsSendHikdoor() {
		return IsSendHikdoor;
	}

	public void setIsSendHikdoor(Integer isSendHikdoor) {
		IsSendHikdoor = isSendHikdoor;
	}

	public String getClassTeacherName() {
		return classTeacherName;
	}

	public void setClassTeacherName(String classTeacherName) {
		this.classTeacherName = classTeacherName;
	}

	public Team() {

	}

	public Team(Integer id) {
		this.id = id;
	}

	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主健ID
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * 设置主健ID
	 * 
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取所属学校
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}

	/**
	 * 设置所属学校
	 * 
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * 获取所属年级
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}

	/**
	 * 设置所属年级
	 * 
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * 获取编号=gradeNumber+teamNumber:由年级编号和顺序组号，分隔符为减号
	 * 
	 * @return java.lang.String
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 设置编号=gradeNumber+teamNumber:由年级编号和顺序组号，分隔符为减号
	 * 
	 * @param code
	 * @type java.lang.String
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取班级名称
	 * 
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * 设置班级名称
	 * 
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取班号：在同一年级中的顺序编号
	 * 
	 * @return java.lang.Integer
	 */
	public Integer getTeamNumber() {
		return this.teamNumber;
	}

	/**
	 * 设置班号：在同一年级中的顺序编号
	 * 
	 * @param teamNumber
	 * @type java.lang.Integer
	 */
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	/**
	 * 获取班级成员数
	 * 
	 * @return Integer
	 */
	public Integer getMemberCount() {
		return this.memberCount;
	}

	/**
	 * 设置班级成员数
	 * 
	 * @param memberCount
	 * @type Integer
	 */
	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	/**
	 * 获取开始时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getBeginDate() {
		return this.beginDate;
	}

	/**
	 * 设置开始时间
	 * 
	 * @param beginDate
	 * @type java.util.Date
	 */
	public void setBeginDate(java.util.Date beginDate) {
		this.beginDate = beginDate;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * 获取结束时间
	 * 
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}

	/**
	 * 设置结束时间
	 * 
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTeamProperty() {
		return teamProperty;
	}

	public void setTeamProperty(String teamProperty) {
		this.teamProperty = teamProperty;
	}

	public String getTeamType() {
		return teamType;
	}

	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}

	public Boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(String studentCount) {
		this.studentCount = studentCount;
	}

	public String getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(String teacherCount) {
		this.teacherCount = teacherCount;
	}

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}

	public Integer getIsSendSeewo() {
		return isSendSeewo;
	}

	public void setIsSendSeewo(Integer isSendSeewo) {
		this.isSendSeewo = isSendSeewo;
	}
}