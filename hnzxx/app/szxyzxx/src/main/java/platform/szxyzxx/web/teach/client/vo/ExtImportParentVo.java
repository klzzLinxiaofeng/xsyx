package platform.szxyzxx.web.teach.client.vo;

public class ExtImportParentVo {
	//学生的UserId
	private Integer id;
	//家长姓名
	private String name;
	//与子女的关系
	private String relation;
	//手机
	private String mobile;
	
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
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}
