package platform.szxyzxx.web.teach.client.vo;

public class ResponseError {
	
	private String result;
	
	private ResponseInfo info;
	
	public ResponseError(){}
	
	public ResponseError(String result, ResponseInfo info) {
		this.result = result;
		this.info = info;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ResponseInfo getInfo() {
		return info;
	}

	public void setInfo(ResponseInfo info) {
		this.info = info;
	}
	
}
