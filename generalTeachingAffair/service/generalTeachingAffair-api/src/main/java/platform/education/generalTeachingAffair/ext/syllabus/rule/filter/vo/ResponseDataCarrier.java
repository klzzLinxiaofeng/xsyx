package platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo;

import java.io.Serializable;

public class ResponseDataCarrier implements Serializable {
	
	private static final long serialVersionUID = 2347551692121677925L;

	private String info;
	
	private Object responseData;
	
	public ResponseDataCarrier() {}
	
	public ResponseDataCarrier(String info) {
		this.info = info;
	}
	
	public ResponseDataCarrier(String info, String responseData) {
		this.info = info;
		this.responseData = responseData;
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

	@Override
	public String toString() {
		return "ResponseDataCarrier [info=" + info + "]";
	}
	
}
