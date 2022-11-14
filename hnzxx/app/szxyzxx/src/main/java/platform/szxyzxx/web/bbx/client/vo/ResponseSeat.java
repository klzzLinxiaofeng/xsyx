package platform.szxyzxx.web.bbx.client.vo;

public class ResponseSeat<T> {
	private String result;
	
	private T data;
	
	private Integer seatId;
	
	private String modifyDate;

	public ResponseSeat() {
	}

	public ResponseSeat(String result, T data, Integer seatId,String modifyDate) {
		this.result = result;
		this.data = data;
		this.seatId = seatId;
		this.modifyDate = modifyDate;
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

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	
	
	public String getModifyDate() {
		return modifyDate;
	}
	
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
}
