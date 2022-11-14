package platform.education.rest.bbx.service.vo;

/**
 * 
 * @author hmzhang 2016/07/24
 *
 */
public class StudentInfo {
	/**
	 * 学生的userId
	 */
	private Integer userId;
	
	/**
	 * 学生姓名
	 */
	private String name;
	
	private String icon;
	
	public StudentInfo(){
		
	}

	public StudentInfo(Integer userId, String name, String icon) {
		super();
		this.userId = userId;
		this.name = name;
		this.icon = icon;
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

	public String getIcon()
	{
		return icon;
	}

	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	
}
