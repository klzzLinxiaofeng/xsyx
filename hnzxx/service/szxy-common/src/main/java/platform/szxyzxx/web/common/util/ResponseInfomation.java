package platform.szxyzxx.web.common.util;
/**
 * 
 * @author panweiliang
 * @date 2014/03/06
 */
public class ResponseInfomation {
	
	/**
	 * 操作成功
	 */
	public final static String OPERATION_SUC = "success";
	/**
	 * 操作失败 用户请求数据有误引起的
	 */
	public final static String OPERATION_FAIL = "fail";
	/**
	 * 操作失败 系统异常引起的
	 */
	public final static String OPERATION_ERROR = "error";
	/**
	 * 用户无登陆 引起操作终止
	 */
	public final static String NO_LOGIN = "noLogin";
	
	/**
	 * 不能允许删除
	 */
	public final static String NO_DELETE = "noDelete";
	
	/**
	 * 数据重复
	 */
	public final static String DATA_REPEAT = "dataRepeat";
	
	
	/**
	 * 密码错误
	 */
	public final static String 	PASSWORD_ERROR="password_error";
	
	
	/**
	 * 上传的照片名称和教师/学生的名称不一致
	 */
	public final static String OUT_OF_ACCORD ="out_of_accord";
	
	
	
	
	public String pk;
	
	public String info = ResponseInfomation.OPERATION_FAIL;
	
	public Object responseData;
	
	public ResponseInfomation() {
		
	}

	public ResponseInfomation(String info) {
		this.info = info;
	}
	
	public ResponseInfomation(String pk, String info) {
		super();
		this.pk = pk;
		this.info = info;
	}
	
	public ResponseInfomation(Object responseData, String info) {
		this.responseData = responseData;
		this.info = info;
	}
	
	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	
}
