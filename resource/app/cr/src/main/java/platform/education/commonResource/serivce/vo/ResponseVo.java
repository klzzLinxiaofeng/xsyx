package platform.education.commonResource.serivce.vo;

import java.io.Serializable;

public class ResponseVo<T> implements Serializable {
	
	private static final long serialVersionUID = 2421048094118048863L;

	/**
	 * 操作状态值
	 * success，fail
	 */
	private String result;
	
	/**
	 * 对象
	 */
	private T data;
	
	public ResponseVo() {
		
	}

	public ResponseVo(String result, T data) {
		this.result = result;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "{result : " + result + ", data : " + data.toString() + "}";
	}
	
}
