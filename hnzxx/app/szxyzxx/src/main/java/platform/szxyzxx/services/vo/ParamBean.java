package platform.szxyzxx.services.vo;

import java.io.Serializable;

/**
 * 
 * @功能描述: 石基三中单点登录实体类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年12月7日上午11:53:54
 */
public class ParamBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String result;
	private String msg;
	private String userName;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
