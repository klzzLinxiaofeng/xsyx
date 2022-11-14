package platform.education.oa.vo;
import java.util.Date;

import platform.education.oa.model.AcceptRepari;
/**
 * AcceptRepari
 * @author AutoCreate
 *
 */
public class AcceptRepariCondition extends AcceptRepari {
	private static final long serialVersionUID = 1L;
	Date beginDate;
	Date endDate;
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