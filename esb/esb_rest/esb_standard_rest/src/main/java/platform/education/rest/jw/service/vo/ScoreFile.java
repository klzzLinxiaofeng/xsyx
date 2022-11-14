package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ScoreFile  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String UUID;
	
	private String url;

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	

}
