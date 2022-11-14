package platform.szxyzxx.web.common.vo;

import java.io.Serializable;

/**
 * 
 * @author clouds
 *
 */
public class ImgResponseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer error;
	private String message;
	private String url;

	public ImgResponseVo() {
	}

	public ImgResponseVo(Integer error, String message, String url) {
		this.error = error;
		this.message = message;
		this.url = url;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
