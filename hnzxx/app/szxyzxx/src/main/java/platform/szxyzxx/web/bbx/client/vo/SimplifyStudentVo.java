package platform.szxyzxx.web.bbx.client.vo;
/**
 * 精简的student信息，提供给移动端使用，方便数据的传输
 * @author Administrator
 *
 */
public class SimplifyStudentVo {
	private static final long serialVersionUID = 1L;
	/**
	 * 学生的id
	 */
	private Integer id;
	/**
	 * 班级名称
	 */
	private String teamName;
	/**
	 * 学生姓名
	 */
	private String name;
	/**
	 * 学生性别
	 */
	private String sex;
	/**
	 * 学生头像
	 */
	private String userIcon;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
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
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	

}
