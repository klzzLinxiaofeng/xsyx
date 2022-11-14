package platform.education.oa.vo;
import platform.education.oa.model.ApplyRepair;

import java.util.Date;
/**
 * ApplyRepair
 * @author AutoCreate
 *
 */
public class ApplyRepairCondition extends ApplyRepair {
	private static final long serialVersionUID = 1L;
	Date beginDate;
	Date endDate;
	private String startTime;
	private String endTime;
	private String searchWord;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}