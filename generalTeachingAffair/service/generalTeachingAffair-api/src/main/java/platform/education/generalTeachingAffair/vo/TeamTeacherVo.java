package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.TeamTeacher;

/**
 * TeamTeacher
 * @author AutoCreate
 *
 */
public class TeamTeacherVo extends TeamTeacher {
	private static final long serialVersionUID = 1L;
	/**
	 * 担任该科目的教师数目
	 */
	private Integer teacherNumberOfSubject;
	
	/*--提供数据给班班星客户端开始--*/
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户头像路径
	 */
	private String imgUrl;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 职务
	 */
	private String position;
	
	
	/*--提供数据给班班星客户端结束--*/
	
	/**
	 * 班级的名称
	 */
	private String teamName;
	
	/**
	 * 年级名称
	 */
	private String gradeName;
	
	/*--提供数据给班班星客户端结束--*/


	/* --  开放接口用属性 2017-11-14   -- */
	/**
	 * 	（分组后）任职类型的集合（已去重）
	 */
	private String types;

	/**
	 *	（分组后）科目的集合
	 *	subjectCode, subjectName; 逗号间隔，多个以分号隔开
	 */
	private String subjectInfo;

	/**
	 *  修改时间的集合（分号隔开）
	 */
	private String modifyTimes;

	/**
	 * 	班级校内编号 pj_team.code2
	 */
	private String teamCode;

	/**
	 *	通用年级码 pj_grade.uni_grade_code
	 */
	private String gradeCode;

	private Integer teamNumber;

	/* ---------------------*/
	
	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	
	public Integer getTeacherNumberOfSubject() {
		return teacherNumberOfSubject;
	}
	
	public void setTeacherNumberOfSubject(Integer teacherNumberOfSubject) {
		this.teacherNumberOfSubject = teacherNumberOfSubject;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}

	public String getModifyTimes() {
		return modifyTimes;
	}

	public void setModifyTimes(String modifyTimes) {
		this.modifyTimes = modifyTimes;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
}