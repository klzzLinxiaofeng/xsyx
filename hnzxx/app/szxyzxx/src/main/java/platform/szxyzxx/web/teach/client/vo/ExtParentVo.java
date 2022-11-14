package platform.szxyzxx.web.teach.client.vo;

/**
 * @function 家长信息
 * @date 2016-2-22
 */

public class ExtParentVo {
	//家长userId
	private Integer id;
	
	//家长姓名
	private String name;
	
	//家长手机号码
	private String mobile;
	
	//家长与学生的关系
	private String relation;
	
	//主监护人关系
	private String rank;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
}
