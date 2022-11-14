package platform.education.generalTeachingAffair.vo;

public class ExtImportErrorMsg {
	//导入数据记录序号
	private Integer id;
	//导入错误信息
	private String msg;
	//导入错误编码
	private String errorCode;
	//号码重复的家长的userId
	private String detail;
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
