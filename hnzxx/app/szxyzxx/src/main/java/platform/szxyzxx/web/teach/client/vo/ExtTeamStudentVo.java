package platform.szxyzxx.web.teach.client.vo;

/**
 * 功能描述：班级学生（pj_team_student）
 * @author 
 * @Date 2016-01-13
 */
public class ExtTeamStudentVo {

	/**
	 * 用户ID(userId)
	 */
	private Integer id;
	
	/**
	 * 学生ID
	 */
	private Integer studentId;
	
	/**
	 * 学生姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private String sex;
	
	/**
	 * 班内编号
	 */
	private Integer number;
	
	/**
	 * 用户头像的url地址
	 */
	private String userIcon;
	/**
	 * 学生别名
	 * @return
	 */
	private String alias;
	
	/**
	 * 学生编号
	 */
	private String studentNumber;
	
	/**
	 * 
	 * 学生账号
	 */
	private String userName;
	/**
	 * 班级学生id
	 */
	private Integer teamStudentId;
	
	public Integer getTeamStudentId() {
		return teamStudentId;
	}

	public void setTeamStudentId(Integer teamStudentId) {
		this.teamStudentId = teamStudentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

}
