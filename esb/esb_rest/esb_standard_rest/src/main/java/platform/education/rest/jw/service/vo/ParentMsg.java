package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ParentMsg implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//用户ID
	private Integer userId;
	//学生姓名
	private String name;
	//头像
	private String icon;
	//学校ID
	private Integer schoolId;
	//学校名称
	private String schoolName;
	//年级ID
	private Integer gradeId;
	//年级名称
	private String gradeName;
	//班级ID
	private Integer teamId;
	//班级名称
	private String teamName;
	//学生性别
	private String sex;
	//档案照片
	private String archivesIcon;

	public String getArchivesIcon() {
		return archivesIcon;
	}
	public void setArchivesIcon(String archivesIcon) {
		this.archivesIcon = archivesIcon;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
