package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.TeamStudent;

import java.util.List;
/**
 * TeamStudent
 * @author AutoCreate
 *
 */
public class TeamStudentVo extends TeamStudent {
	private static final long serialVersionUID = 1L;
	private String empCode;

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	/**
	 * pj_school_year.name 学年名称
	 */
	private String schoolYearName;
	
	/***
	 * 班级学生人数
	 */
	private String stuNum;

	/**
	 * 所属校车系统 萌童校卫：“0”；国盛：“1”
	 */
//    private Integer systemId;

	private String empCard;

	public String getEmpCard() {
		return empCard;
	}

	public void setEmpCard(String empCard) {
		this.empCard = empCard;
	}

//	public Integer getSystemId() {
//        return systemId;
//    }
//
//    public void setSystemId(Integer systemId) {
//        this.systemId = systemId;
//    }

    public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	/**
	 * pj_grade.name 年级名称
	 */
	private String gradeName;
	
	/**
	 * pj_team.schoolYear 学年的开始年份
	 */
	private String schoolYear;
	
	/**
	 * pj_team.name 班级名称
	 */
	private String teamName;

	/**
	 * pj_team_student.id 班级学生表的主键ID
	 */
	private Integer teamStudentId;
	
	/**
	 * pj_student_health_archive.type;类型
	 */
	private String healthType;
	
	/**
	 * pj_student_health_archive.accessory;附件
	 */
	private String accessory;
	
	private String accessoryName;
	
	/**
	 * pj_student.user_id
	 */
	private Integer userId;
	
	/**
	 * pj_student.user_name
	 */
	private String userName;
	
	/**
	 * pj_student.studnet_number 全国统一学籍号
	 */
	private String studentNumber;
	
	/**
	 * pj_student.sex 性别
	 */
	private String sex;
	
	/**
	 * pj_student.mobile 手机号码
	 */
	private String mobile;
	
	/**
	 * pj_student.study_state 学生在读状态
	 */
	private String studyState;
	
	/**
	 * 用于获得学生对应的家长集
	 */
	private List<Parent> parentList;
	
	private String parentName;
	
	private String parentMobile;

	private String teamCode;

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	/**
	 * 获取学年名称
	 * @return
	 */
	public String getSchoolYearName() {
		return schoolYearName;
	}

	/**
	 * 设置学年名称
	 * @param schoolYearName
	 */
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

	/**
	 * 获取年级名称
	 * @return
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * 设置年级名称
	 * @param gradeName
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * 获取学年的开始年份
	 * @return
	 */
	public String getSchoolYear() {
		return schoolYear;
	}

	/**
	 * 设置学年的开始年份
	 * @param schoolYear
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	/**
	 * 获取班级名称
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * 设置班级名称
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * 获取班级学生表的主键ID
	 * @return
	 */
	public Integer getTeamStudentId() {
		return teamStudentId;
	}

	/**
	 * 设置班级学生表的主键ID
	 * @param teamStudentId
	 */
	public void setTeamStudentId(Integer teamStudentId) {
		this.teamStudentId = teamStudentId;
	}

	/**
	 * 获取健康类型
	 * @return
	 */
	public String getHealthType() {
		return healthType;
	}

	/**
	 * 设置健康类型
	 * @param healthType
	 */
	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
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

	public String getStudyState() {
		return studyState;
	}

	public void setStudyState(String studyState) {
		this.studyState = studyState;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Parent> getParentList() {
		return parentList;
	}

	public void setParentList(List<Parent> parentList) {
		this.parentList = parentList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}
	
}