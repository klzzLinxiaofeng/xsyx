package platform.education.generalTeachingAffair.vo;

public class ExtImportStudentOfInSchoolVo {
	//学生ID
	private Integer id;

	//姓名
	private String name;

	//班内学号
	private Integer number;

	//用户ID
	private Integer userId;

	//用户账号
	private String userName;

	//班级ID
	private Integer teamId;

	//原班级ID
	private Integer oldTeamId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getOldTeamId() {
		return oldTeamId;
	}

	public void setOldTeamId(Integer oldTeamId) {
		this.oldTeamId = oldTeamId;
	}
}
