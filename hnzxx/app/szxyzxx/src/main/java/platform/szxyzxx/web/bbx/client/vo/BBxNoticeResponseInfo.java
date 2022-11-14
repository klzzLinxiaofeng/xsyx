package platform.szxyzxx.web.bbx.client.vo;

public class BBxNoticeResponseInfo {
	
	private String msg;
	
	private String detail;

	public BBxNoticeResponseInfo(){
		
	}
	
	
	public BBxNoticeResponseInfo(String msg, String detail) {
		super();
		this.msg = msg;
		this.detail = detail;
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
	
	
	

}
