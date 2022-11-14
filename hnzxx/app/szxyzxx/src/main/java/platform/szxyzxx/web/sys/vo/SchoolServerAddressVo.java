package platform.szxyzxx.web.sys.vo;

public class SchoolServerAddressVo {
	/**
	 * id
	 */
	private Integer Id;
	
	private Integer gorupId;
	
	private String code;
	
	private String name;
	
	private String eduServer;
	
	private String eduPort;
	
	private String apiServer;
	
	private String apiPort;


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGorupId() {
		return gorupId;
	}

	public void setGorupId(Integer gorupId) {
		this.gorupId = gorupId;
	}

	public String getEduServer() {
		return eduServer;
	}

	public void setEduServer(String eduServer) {
		this.eduServer = eduServer;
	}

	public String getEduPort() {
		return eduPort;
	}

	public void setEduPort(String eduPort) {
		this.eduPort = eduPort;
	}

	public String getApiServer() {
		return apiServer;
	}

	public void setApiServer(String apiServer) {
		this.apiServer = apiServer;
	}

	public String getApiPort() {
		return apiPort;
	}

	public void setApiPort(String apiPort) {
		this.apiPort = apiPort;
	}
	
	
}
