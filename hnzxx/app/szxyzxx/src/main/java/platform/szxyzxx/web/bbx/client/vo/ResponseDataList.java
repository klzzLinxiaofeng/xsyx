package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

/**
 * 返回详细信息
 * @author hmzhang
 *
 */
public class ResponseDataList<T> {
	
	private String result;
	
	private List<T> list;

	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public ResponseDataList(String result, List<T> list) {
		super();
		this.result = result;
		this.list = list;
	}

	
	
	
}
