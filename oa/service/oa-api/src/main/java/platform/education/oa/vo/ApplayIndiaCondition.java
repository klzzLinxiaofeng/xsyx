package platform.education.oa.vo;

import java.util.Date;

import platform.education.oa.model.ApplayIndia;
/**
 * ApplayIndia
 * @author AutoCreate
 *
 */
public class ApplayIndiaCondition extends ApplayIndia {
	private static final long serialVersionUID = 1L;
	
	private Integer teacherId;
	
	private String searchWord;
	
	private Date beginDate;
	
	private Date lastDate;

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}


	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(java.util.Date firstDate) {
		this.beginDate = firstDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	


}