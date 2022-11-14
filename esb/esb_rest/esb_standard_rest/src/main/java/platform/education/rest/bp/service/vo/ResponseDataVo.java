package platform.education.rest.bp.service.vo;

public class ResponseDataVo <T>{

	private String result;
	
	private T data [] ;

	public ResponseDataVo() {

	}
	
	
	public ResponseDataVo(String result, T[] data) {
		this.result = result;
		this.data = data;
	}


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public T[] getData() {
		return data;
	}


	public void setData(T[] data) {
		this.data = data;
	}
	
	
	
	
	
}
