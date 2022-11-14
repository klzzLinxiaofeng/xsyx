package platform.education.generalTeachingAffair.vo;

public class CheckResult {
	/**
	 * 字段的值
	 */
	private String value;
	/**
	 * 检验结果 0正确 1警告 2错误
	 */
	private Integer status;
	/**
	 * 警告/错误信息
	 */
	private String info;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public boolean checkCorrect() {
		if(status==null) {
			return false;
		} else {
			return status==0 ? true:false;
		}
	}

	public boolean checkWarn() {
		if(status==null) {
			return false;
		} else {
			return status==1 ? true:false;
		}
	}
	
	public boolean checkError() {
		if(status==null) {
			return false;
		} else {
			return status==2 ? true:false;
		}
	}
	
	public static CheckResult getInstance(String value) {
		CheckResult result = new CheckResult();
		result.setStatus(0);
		result.setValue(value);
		return result;
	}
}
