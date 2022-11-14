package platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo;

import java.io.Serializable;

public class DataCarrier<T> implements Serializable {
	
	private static final long serialVersionUID = -7194056121650568758L;
	
	T data;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	
}
