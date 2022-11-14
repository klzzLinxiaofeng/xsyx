package platform.education.rest.common;

import java.io.Serializable;

public class ResponseNormal implements Serializable{
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1081231432848316143L;
	
	private String result;

	public ResponseNormal(){}
	
	public ResponseNormal(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
