package platform.szxyzxx.web.teach.client.vo;

public class ExtImportStudentVo {
	//导入记录的顺序号
	private Integer id;
	//姓名
	private String name;
	//班内学号
	private Integer number;
	//性别
	private String sex;
	//别名
	private String alias;
	//职务
	private String position;
	
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
