package platform.education.generalTeachingAffair.vo;
import java.util.Date;

import platform.education.generalTeachingAffair.model.Practice;
/**
 * Practice
 * @author AutoCreate
 *
 */
public class PracticeCondition extends Practice {
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private Date endDate;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}