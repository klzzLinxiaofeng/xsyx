package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Student;
/**
 * Student
 * @author AutoCreate
 *
 */
public class StudentVo extends Student {
	
	private static final long serialVersionUID = 1L;
	
	private String manNum;
	
	private String womanNum;
	
	private String sumNum;
	
	private String userState;//用户状态
	
	private String parentMobile;//家长电话
	
	/* 提供给班班星客户端  开始 */
	/**
	 * 用户头像路径
	 */
	private String imgUrl;
	/* 提供给班班星客户端  结束 */
	
	/*学生统计 2015-11-05*/
	private String unNationalMinority; //非少数民族
	
	private String nationalMinority; //少数民族
	
	private String totalNational; //总人数
	/*学生统计*/
	
	private Float score;
	
	//班内学号
	private Integer number;
	
	private String schoolName;

	/* ----- 开放平台接口用 2017-11-20 ----- */
	/**
	 * 邮箱地址 pj_person.email
	 */
	private String email;
	/**
	 * 班级校内编码	pj_team.code2
	 */
	private String teamCode;

	private Integer gradeId;

	private String gradeName;
	/**
	 * 通用年级码	pj_grade.uni_grade_code
	 */
	private String uniGradeCode;
	/* --------------- */

	private Integer teamNumber;

	private Integer teamStudentId;


	public Integer getTeamStudentId() {
		return teamStudentId;
	}

	public void setTeamStudentId(Integer teamStudentId) {
		this.teamStudentId = teamStudentId;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getManNum() {
		return manNum;
	}

	public void setManNum(String manNum) {
		this.manNum = manNum;
	}

	public String getWomanNum() {
		return womanNum;
	}

	public void setWomanNum(String womanNum) {
		this.womanNum = womanNum;
	}

	public String getSumNum() {
		return sumNum;
	}

	public void setSumNum(String sumNum) {
		this.sumNum = sumNum;
	}

	
	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}



	public String getParentMobile() {
		return parentMobile;
	}

	public void setParentMobile(String parentMobile) {
		this.parentMobile = parentMobile;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getUnNationalMinority() {
		return unNationalMinority;
	}

	public void setUnNationalMinority(String unNationalMinority) {
		this.unNationalMinority = unNationalMinority;
	}

	public String getNationalMinority() {
		return nationalMinority;
	}

	public void setNationalMinority(String nationalMinority) {
		this.nationalMinority = nationalMinority;
	}

	public String getTotalNational() {
		return totalNational;
	}

	public void setTotalNational(String totalNational) {
		this.totalNational = totalNational;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getUniGradeCode() {
		return uniGradeCode;
	}

	public void setUniGradeCode(String uniGradeCode) {
		this.uniGradeCode = uniGradeCode;
	}

	@Override
	public String toString() {
		return "StudentVo{" +
				"manNum='" + manNum + '\'' +
				", womanNum='" + womanNum + '\'' +
				", sumNum='" + sumNum + '\'' +
				", userState='" + userState + '\'' +
				", parentMobile='" + parentMobile + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				", unNationalMinority='" + unNationalMinority + '\'' +
				", nationalMinority='" + nationalMinority + '\'' +
				", totalNational='" + totalNational + '\'' +
				", score=" + score +
				", number=" + number +
				", schoolName='" + schoolName + '\'' +
				", email='" + email + '\'' +
				", teamCode='" + teamCode + '\'' +
				", gradeId=" + gradeId +
				", gradeName='" + gradeName + '\'' +
				", uniGradeCode='" + uniGradeCode + '\'' +
				", teamNumber=" + teamNumber +
				", teamStudentId=" + teamStudentId +
				'}';
	}
}