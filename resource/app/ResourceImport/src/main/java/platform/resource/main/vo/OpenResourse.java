package platform.resource.main.vo;

import java.util.List;

public class OpenResourse {
	
	private List<TextBookVoResourse> voResourseList;
	private String message;
	public static String ERROR = "目录下的教材有问题";

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<TextBookVoResourse> getVoResourseList() {
		return voResourseList;
	}

	public void setVoResourseList(List<TextBookVoResourse> voResourseList) {
		this.voResourseList = voResourseList;
	}
	

}
