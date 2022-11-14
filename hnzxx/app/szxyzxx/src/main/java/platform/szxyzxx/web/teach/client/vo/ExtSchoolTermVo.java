package platform.szxyzxx.web.teach.client.vo;

/**
 * 功能描述：学校学期（pj_school_term）
 * @author 
 * @Date 2016-01-13
 */
public class ExtSchoolTermVo {
	
	/**
	 * 学期代码：学校ID+学年+国标学期码（48734-2015-1）
	 */
	private String code;
	
	/**
	 * 学期名称
	 */
	private String name;
	
	/**
	 * 国标学期代码
	 */
	private String value;
	
	/**
	 * 是否是当前学期标志
	 */
	Boolean isCurrent;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
}
