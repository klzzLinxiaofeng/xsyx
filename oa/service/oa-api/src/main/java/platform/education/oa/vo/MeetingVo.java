package platform.education.oa.vo;
import java.util.List;
import platform.education.oa.model.Meeting;
import platform.education.oa.model.MeetingUser;
/**
 * Meeting
 * @author AutoCreate
 *
 */
public class MeetingVo extends Meeting {
	private static final long serialVersionUID = 1L;
	private List<MeetingUser> meetingUser;
	private int day;
	private int hour;
	private String fileName;
	private String meetingStatus;
	private String isCanModify;
	private Boolean isCheck;
	
	public Boolean getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getMeetingStatus() {
		return meetingStatus;
	}
	public void setMeetingStatus(String meetingStatus) {
		this.meetingStatus = meetingStatus;
	}
	public String getIsCanModify() {
		return isCanModify;
	}
	public void setIsCanModify(String isCanModify) {
		this.isCanModify = isCanModify;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public List<MeetingUser> getMeetingUser() {
		return meetingUser;
	}
	public void setMeetingUser(List<MeetingUser> meetingUser) {
		this.meetingUser = meetingUser;
	}
}