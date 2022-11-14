package platform.szxyzxx.web.teach.client.vo;

/**
 * @function 根据年级获取教师的一个TeacherVo
 * @author panfei
 * @data 2016-2-18
 */
public class ExtTeamTeacherVo {
	//1：班主任    2：任课教师
	private String type;
	
	//教师的UserId
	private Integer id;
	
	//教师姓名
	private String name;
	
	//教师性别
	private String sex;
	
	//科目code
	private String subjectCode;
	
	//科目名称
	private String subjectName;
	
	//头像
	private String userIcon;

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

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
