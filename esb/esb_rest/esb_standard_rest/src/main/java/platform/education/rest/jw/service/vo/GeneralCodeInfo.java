package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class GeneralCodeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//基础数据版本
	private String version;
	
	//代码表code值
	private String code;
	
	//code值下的项目
	private List<GeneralCode> GeneralCode;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<GeneralCode> getGeneralCode() {
		return GeneralCode;
	}

	public void setGeneralCode(List<GeneralCode> generalCode) {
		GeneralCode = generalCode;
	}
	
}
