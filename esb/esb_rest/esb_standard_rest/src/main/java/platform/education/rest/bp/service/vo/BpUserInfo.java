package platform.education.rest.bp.service.vo;


import java.io.Serializable;
import java.util.List;

public class BpUserInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 设备号
	 */
	private String signageName;
	/**
	 * 学校Id
	 */
	private Integer schoolId;
	/**
	 * 班级Id
	 */
	private Integer teamId;
	/**
	 * 当前学期code
	 */
	private String termCode;
	/**
	 * 环信帐号
	 */
	private String hxAccount;
	/**
	 * 房间Id
	 */
	private Integer roomId;
	/**
	 * 设备类型
	 */
	private String signageType;
	
	private Boolean isOpen;

	private List<BpSyllabusLessonTimeVo> syllabusLessonTimeList;
	
	private List<BpSyllabusLessonDayVo> lessonDayList;
	public Boolean getIsOpen() {
		return isOpen;
	}
	
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
		
	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public List<BpSyllabusLessonTimeVo> getSyllabusLessonTimeList() {
		return syllabusLessonTimeList;
	}

	public void setSyllabusLessonTimeList(
			List<BpSyllabusLessonTimeVo> syllabusLessonTimeList) {
		this.syllabusLessonTimeList = syllabusLessonTimeList;
	}

	public List<BpSyllabusLessonDayVo> getLessonDayList() {
		return lessonDayList;
	}

	public void setLessonDayList(List<BpSyllabusLessonDayVo> lessonDayList) {
		this.lessonDayList = lessonDayList;
	}

	public String getSignageName() {
		return signageName;
	}

	public void setSignageName(String signageName) {
		this.signageName = signageName;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getHxAccount() {
		return hxAccount;
	}

	public void setHxAccount(String hxAccount) {
		this.hxAccount = hxAccount;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

	public String getSignageType() {
		return signageType;
	}

	public void setSignageType(String signageType) {
		this.signageType = signageType;
	}
}
