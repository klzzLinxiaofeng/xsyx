package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.SeatChartItem;
/**
 * SeatChartItem
 * @author AutoCreate
 *
 */
public class SeatChartItemVo extends SeatChartItem {
	private static final long serialVersionUID = 1L;
	public String studentName;
	public String studentSex;
	
	public String studentNumber;
	public String userIcon;
	
	
	public String userId;
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentSex() {
		return studentSex;
	}
	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}
	public String getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}