package platform.education.rest.common;

public class PageResponseVo<T> extends ResponseVo<T> {
	
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
	/**
	 * 总记录数 
	 */
	private Integer totalRows;
	
	public PageResponseVo() {
		
	}

	public PageResponseVo(String result, T data, Integer totalRows) {
		this.result = result;
		this.data = data;
		this.totalRows = totalRows;
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
		return "{result : " + result + ", data : " + data.toString() + ",totalRows : "+ totalRows +"}";
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}
	
}
