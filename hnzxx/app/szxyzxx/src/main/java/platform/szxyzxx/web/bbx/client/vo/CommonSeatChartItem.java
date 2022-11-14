package platform.szxyzxx.web.bbx.client.vo;

public class CommonSeatChartItem {
	private Integer seatId;
	/**
	 * studentId
	 */
	private Integer userId;
	/**
	 * 行数
	 */
	private Integer positionX;
	/**
	 * 组数（列数）
	 */
	private Integer positionY;
	
	private String studentName;
	private String studentSex;
	
	private String studentNumber;
	private String userIcon;
	

	
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getPositionX() {
		return positionX;
	}
	public void setPositionX(Integer positionX) {
		this.positionX = positionX;
	}
	public Integer getPositionY() {
		return positionY;
	}
	public void setPositionY(Integer positionY) {
		this.positionY = positionY;
	}
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

	
}
