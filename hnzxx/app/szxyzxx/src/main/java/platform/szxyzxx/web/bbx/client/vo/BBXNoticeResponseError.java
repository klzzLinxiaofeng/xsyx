package platform.szxyzxx.web.bbx.client.vo;

public class BBXNoticeResponseError {

	private String result;
	
	private BBxNoticeResponseInfo info;

	
	public BBXNoticeResponseError(){
		
	}
	
	
	public BBXNoticeResponseError(String result, BBxNoticeResponseInfo info) {
		super();
		this.result = result;
		this.info = info;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BBxNoticeResponseInfo getInfo() {
		return info;
	}

	public void setInfo(BBxNoticeResponseInfo info) {
		this.info = info;
	}
	
	
	
	
}
