package platform.szxyzxx.web.bbx.client.vo;

public class CommonActivity {
	private Integer id;
	/**
	 * 班级的唯一标识
	 */
	private Integer teamId;
	
	
	private String teamName;
	/**
	 * 活动标题
	 */
	private String name;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String finishTime;
	/**
	 * 活动地点
	 */
	private String place;
	/**
	 * 说明，相关事宜
	 */
	private String comment;
	/**
	 * 学年
	 */
	private String schoolYear;
	/**
	 * 学期
	 */
	private String termCode;
	/**
	 * 班级活动的图片
	 */
	private String activityImage;
	
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 修改时间
	 */
	private String modifyDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSchoolYear() {
		return schoolYear;
	}
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	public String getActivityImage() {
		return activityImage;
	}
	public void setActivityImage(String activityImage) {
		this.activityImage = activityImage;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}
