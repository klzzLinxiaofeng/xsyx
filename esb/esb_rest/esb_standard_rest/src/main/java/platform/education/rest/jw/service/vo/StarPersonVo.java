package platform.education.rest.jw.service.vo;

public class StarPersonVo {
	/**
	 * 对应的班级名
	 */
	private String teamName;
	/**
	 * 对应的学生名
	 */
	private String studentName;
	/**
	 * 排名
	 */
	private Integer rank;
	/**
	 * 总卡数
	 */
	private Integer totalScore;
	/**
	 * 激励卡数
	 */
	private Integer addScore;
	/**
	 * 发展卡数
	 */
	private Integer normalScore;
	/**
	 * 是否评定  1为评定，2为未评定
	 */
	private Integer flag;
	/**
	 * 评定时间
	 */
	private String date;
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public Integer getAddScore() {
		return addScore;
	}
	public void setAddScore(Integer addScore) {
		this.addScore = addScore;
	}
	public Integer getNormalScore() {
		return normalScore;
	}
	public void setNormalScore(Integer normalScore) {
		this.normalScore = normalScore;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	
}
