package platform.education.rest.bbx.service.vo;

import java.io.Serializable;

public class SchoolInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5645225770614615249L;
	
	
	private String userId;
	
	private String schoolId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	
}
