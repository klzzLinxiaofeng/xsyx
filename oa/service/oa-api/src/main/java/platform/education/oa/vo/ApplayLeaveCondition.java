package platform.education.oa.vo;
import java.util.Date;

import platform.education.oa.model.ApplayLeave;
/**
 * ApplayLeave
 * @author AutoCreate
 *
 */
public class ApplayLeaveCondition extends ApplayLeave {
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
	
	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getLastDate() {
		return lastDate;
	}
	
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
}