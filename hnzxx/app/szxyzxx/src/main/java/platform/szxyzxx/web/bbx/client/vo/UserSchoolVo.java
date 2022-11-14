package platform.szxyzxx.web.bbx.client.vo;

public class UserSchoolVo {


	private static final long serialVersionUID = 1L;
	
	/**
	 * 学校Id
	 */
	private Integer  id;
	
	/**
	 * 学校名称 
	 */
	private String  name;
	
	public UserSchoolVo() {

	}
	
	
	

	public UserSchoolVo(Integer id, String name) {
		this.id = id;
		this.name = name;
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
	
	
	
	
}
