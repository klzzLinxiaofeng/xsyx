package platform.education.rest.bp.service.vo;

public class SimplifyTeacherVo {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 教师的id
	 */
	private Integer id;
	/**
	 * 教师的类型
	 * 1：班主任
	 * 2：任课教师
	 */
	private String type;
	/**
	 * 教师的姓名
	 */
	private String name;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 班级名称
	 */
	private String teamName;
	/**
	 * 科目名称
	 */
	private String subjectName;
	/**
	 * 教师头像
	 */
	private String userIcon;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	
}
