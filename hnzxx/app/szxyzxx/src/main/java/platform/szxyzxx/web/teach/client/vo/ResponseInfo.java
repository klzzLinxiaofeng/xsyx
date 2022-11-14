package platform.szxyzxx.web.teach.client.vo;

public class ResponseInfo {
	
	private String msg;
	
	private String detail;
	
	private String param;

	
	public ResponseInfo() {
		
	}
	
	public ResponseInfo(String msg,String detail,String param) {
		this.msg = msg;
		this.detail = detail;
		this.param = param;
	}
	
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	
}
