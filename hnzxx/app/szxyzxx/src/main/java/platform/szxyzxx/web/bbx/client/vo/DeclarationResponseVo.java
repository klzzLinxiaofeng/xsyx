package platform.szxyzxx.web.bbx.client.vo;

/**
 * 班级宣言返回值
 * @author huangyanchun
 *
 */
public class DeclarationResponseVo {

	private static final long serialVersionUID = 1L;
	/**
	 * 返回的结果状态
	 */
	private String result;
	
	/**
	 * 返回值
	 */
	private String data;
	
	public DeclarationResponseVo() {

	}

	public DeclarationResponseVo(String result, String data) {
		super();
		this.result = result;
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
	
}
