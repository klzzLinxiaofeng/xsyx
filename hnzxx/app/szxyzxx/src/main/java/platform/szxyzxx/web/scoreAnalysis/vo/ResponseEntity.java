package platform.szxyzxx.web.scoreAnalysis.vo;

import java.io.Serializable;

/**
 * 
 * @功能描述: 响应参数实体类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年3月1日下午1:07:15
 */
public class ResponseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//返回状态 0已缴费 1未缴费 2错误
	private String status;
	//缴费的url,其中后缀参数callbackurl是作为支付成功后跳回的链接，此链接需要用urlencode编码处理，如果不传，则默认跳首页。
	private String jump_url;
	//状态说明
	private String message;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJump_url() {
		return jump_url;
	}
	public void setJump_url(String jump_url) {
		this.jump_url = jump_url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
