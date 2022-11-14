package platform.education.generalTeachingAffair.vo;

import java.util.List;

import platform.education.generalTeachingAffair.model.TeacherDetailInfo;

public class TeacherDetailInfoVo extends TeacherDetailInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SalaryFieldnameValue> salaryDetail;
	
	private String message;
	
	private Integer roleId;
	
	private String salaryDate;

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public List<SalaryFieldnameValue> getSalaryDetail() {
		return salaryDetail;
	}

	public void setSalaryDetail(List<SalaryFieldnameValue> salaryDetail) {
		this.salaryDetail = salaryDetail;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
