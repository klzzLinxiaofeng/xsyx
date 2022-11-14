package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ParentStudent;
/**
 * ParentStudent
 * @author AutoCreate
 *
 */
public class ParentStudentCondition extends ParentStudent {
	private static final long serialVersionUID = 1L;
	private Integer schoolId;
	private Integer gradeId;
	private Integer teamId;
	private String name;
	private String userName;
	private String stuName;

	//2016-11-29 添加号码查询。
	private String mobile;

	private String carNo;


	/**
	 * 所属校车系统 萌童校卫：“0”；国盛：“1”
	 */
//	private Integer systemId;

	/**
	 * 学生信息是否发送到昊吉顺校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
	 */
	private Integer isSendSchoolBusHjs;
	/**
	 * 学生信息是否发送到国盛校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
	 */
	private Integer isSendSchoolBusGs;

	public Integer getIsSendSchoolBusHjs() {
		return isSendSchoolBusHjs;
	}

	public void setIsSendSchoolBusHjs(Integer isSendSchoolBusHjs) {
		this.isSendSchoolBusHjs = isSendSchoolBusHjs;
	}

	public Integer getIsSendSchoolBusGs() {
		return isSendSchoolBusGs;
	}

	public void setIsSendSchoolBusGs(Integer isSendSchoolBusGs) {
		this.isSendSchoolBusGs = isSendSchoolBusGs;
	}

	/**
	 * 用户信息是否发送到校车
	 */
//	private Integer isSend;
//
//	public Integer getIsSend() {
//		return isSend;
//	}
//
//	public void setIsSend(Integer isSend) {
//		this.isSend = isSend;
//	}

//	public Integer getSystemId() {
//		return systemId;
//	}
//
//	public void setSystemId(Integer systemId) {
//		this.systemId = systemId;
//	}

	@Override
	public String getStuName() {
		return stuName;
	}

	@Override
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
}