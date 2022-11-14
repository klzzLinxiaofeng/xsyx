package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class TeacherSalary implements Serializable{
	private static final long serialVersionUID = -8480276483557179438L;
	private Integer id;
	private Integer userId;
	private String name;
	private Integer year;
	private Integer month;
	private Float salaryTotal;
	private FieldNameValue salaryDetail;
	
	public TeacherSalary() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Float getSalaryTotal() {
		return salaryTotal;
	}
	public void setSalaryTotal(Float salaryTotal) {
		this.salaryTotal = salaryTotal;
	}

	public FieldNameValue getSalaryDetail() {
		return salaryDetail;
	}

	public void setSalaryDetail(FieldNameValue salaryDetail) {
		this.salaryDetail = salaryDetail;
	}
	
	
}
