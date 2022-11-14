package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Student;
/**
 * Student
 * @author AutoCreate
 *
 */
public class StudentCondition extends Student {
	private static final long serialVersionUID = 1L;
	private String schoolYear;//学年
	private Integer gradeId;
	private boolean userNameLike = false;//是否使用模糊查询，默认为否
	private boolean nameLike = false;//是否使用模糊查询，默认为否
	//新增档案完成信息
	private String finishName;
	
	private Boolean interrupteur;
	
	public String getFinishName() {
		return finishName;
	}
	public void setFinishName(String finishName) {
		this.finishName = finishName;
	}
	public Boolean getInterrupteur() {
		return interrupteur;
	}
	public void setInterrupteur(Boolean interrupteur) {
		this.interrupteur = interrupteur;
	}

	/**
	 * 学生统计--民族 2015-11-5
	 */
	private String race;
	/**
	 * 学生统计--政治面貌 2015-11-5
	 */
	private String politicalStatus;
	
	
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	
	public boolean getUserNameLike() {
		return userNameLike;
	}
	public void setUserNameLike(boolean userNameLike) {
		this.userNameLike = userNameLike;
	}
	
	public boolean getNameLike() {
		return nameLike;
	}
	public void setNameLike(boolean nameLike) {
		this.nameLike = nameLike;
	}
	
}