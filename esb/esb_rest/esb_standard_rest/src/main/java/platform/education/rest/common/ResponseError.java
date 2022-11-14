package platform.education.rest.common;

import platform.education.rest.common.constants.CommonCode;

/**
 * 接口返回错误信息的时候使用
 * example：
 *	{
 *		"result":"060111",
 *		"info":{
 *		"msg":"参数为空", 
 *		"detail":"姓名不能为空。",
 * 		"param":"name"
 *		}
 *	}
 * 
 * 
 * @see 迅云 OpenAPI 开发规范
 * 
 * @author hmzhang 2016/07/23
 *
 */
public class ResponseError {
	/**
	 * @see CommonCode
	 * 错误码
	 */
	private String result;
	
	/**
	 * 错误信息
	 */
	private ResponseInfo info;
	
	public ResponseError(){}
	
	public ResponseError(String result, ResponseInfo info) {
		this.result = result;
		this.info = info;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public ResponseInfo getInfo() {
		return info;
	}

	public void setInfo(ResponseInfo info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "{result : " + result + ", info : " + info.toString() + "}";
	}
}
