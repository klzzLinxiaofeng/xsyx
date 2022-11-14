package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;


/**
 * 已阅、未阅返回详细信息
 * @author huangyanchun
 *
 * @param <D>
 * @param <T>
 */
public class ResponseReadData<D,T> {
	
	
	private D readed;
	
	
	private T notRead;



	public D getReaded() {
		return readed;
	}
	
	
	public void setReaded(D readed) {
		this.readed = readed;
	}
	


	public T getNotRead() {
		return notRead;
	}


	public void setNotRead(T notRead) {
		this.notRead = notRead;
	}


	public ResponseReadData(D readed, T notRead) {
		this.readed = readed;
		this.notRead = notRead;
	}

    public ResponseReadData(){
    	
    }

	
	

}
