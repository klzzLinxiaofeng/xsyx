package platform.szxyzxx.web.bbx.client.vo;
/**
 * 获取班级信息
 * @author hmzhang
 *
 */
public class CommonTeam {
	private static final long serialVersionUID = 1L;
	
	private Integer schoolId;
	
	private Integer gradeId;
	
	private String gradeName;
	
	private Integer teamId;
	
	private String teamName;
	
	private String code2;
	
	private Integer studentCount;
	
	private Integer teacherCount;
	
	private String masterName;
	
	private String createDate;

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

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public Integer getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(Integer teacherCount) {
		this.teacherCount = teacherCount;
	}

	public String getMasterName() {
		return masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
}
