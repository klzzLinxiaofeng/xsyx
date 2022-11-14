package platform.szxyzxx.web.bbx.client.vo;

public class ResponseOrder<T> {
	private String result;
	
	private T data;
	
	private Integer recordCount;

	public ResponseOrder() {
	}

	public ResponseOrder(String result, T data, Integer recordCount) {
		this.result = result;
		this.data = data;
		this.recordCount = recordCount;
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

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	
	
}
