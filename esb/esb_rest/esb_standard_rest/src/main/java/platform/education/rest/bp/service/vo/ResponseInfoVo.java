package platform.education.rest.bp.service.vo;

import java.io.Serializable;

public class ResponseInfoVo<T> implements Serializable {

private static final long serialVersionUID = 1L;
	
	private String result;
	
	private T info;
	
	public ResponseInfoVo() {

	}

	public ResponseInfoVo(String result, T info) {
		this.result = result;
		this.info = info;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}
	
	
	
	
}
