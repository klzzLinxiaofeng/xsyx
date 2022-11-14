package platform.szxyzxx.web.bbx.client.vo;

public class UserRoleVo {


	private static final long serialVersionUID = 1L;
	
	/**
	 * 学校Id
	 */
	private Integer  id;
	
	/**
	 * 学校名称 
	 */
	private String  name;
	
	private String code;
	
	
	
	public String getCode() {
		return code;
	}




	public void setCode(String code) {
		this.code = code;
	}




	public UserRoleVo() {

	}
	
	
	

	public UserRoleVo(Integer id, String name,String code) {
		this.id = id;
		this.name = name;
		this.code = code;
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
