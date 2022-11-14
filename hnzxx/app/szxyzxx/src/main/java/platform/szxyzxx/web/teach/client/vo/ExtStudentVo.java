package platform.szxyzxx.web.teach.client.vo;

public class ExtStudentVo {
	/**
	 * 学生的userId
	 */
	private Integer id;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 学生性别
	 */
	private String sex;
	/**
	 * 学生班内学号
	 */
	private String number;
	/**
	 * 学生头像地址
	 */
	private String userIcon;

	/* ########## Added on 2017-07-26 BEGIN ########## */

	/**
	 * 学生id
	 */
	private Integer studentId;

	/**
	 * 用户账号
	 */
	private String userName;

	/* ########## Added on 2017-07-26 END ########## */
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
