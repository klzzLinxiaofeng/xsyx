package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.PublicClass;

/**
 * PublicClass
 * 
 * @author AutoCreate
 *
 */
public class PublicClassCondition extends PublicClass {
	private static final long serialVersionUID = 1L;

	// 用于关键字查询
	private String keyword;
	
	private java.util.Date startDate;  //用于查询--开始时间
	
	private java.util.Date endDate;    //用于查询--结束时间
	private Integer leibie;    //

	public Integer getLeibie() {
		return leibie;
	}

	public void setLeibie(Integer leibie) {
		this.leibie = leibie;
	}

	public String getkeyword() {
		return keyword;
	}

	public void setkeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public java.util.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public java.util.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}

}