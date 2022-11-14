package platform.education.rest.basic.service.vo;

import java.io.Serializable;

public class WeekInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//周次编号
	private String periodCode;
	//周次名
	private String name;
	
	private String beginDate;
	
	private String endDate;
	//是否当前周
	private Integer isCurrent;

	
	public String getPeriodCode() {
		return periodCode;
	}

	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Integer getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}

}
