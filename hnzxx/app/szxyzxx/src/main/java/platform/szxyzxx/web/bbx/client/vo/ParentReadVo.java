package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

/**
 * 家长已阅、未阅详细信息
 * @author huangyanchun
 *
 * @param <T>
 */
public class ParentReadVo<T> {

	//是否已阅（true 1 为已阅  false 0为未阅）
	private Boolean hasRead; 
	
	private List<T> t;
	
	
	public Boolean getHasRead() {
		return hasRead;
	}
	
	public void setHasRead(Boolean hasRead) {
		this.hasRead = hasRead;
	}
	
	
	public List<T> getT() {
		return t;
	}
	
	
	public void setT(List<T> t) {
		this.t = t;
	}
	
	
}
