package platform.education.generalTeachingAffair.vo;

public class StarEvaluateData implements Comparable{
	
	private Integer studentId;
	
	private String studentName;
	
	private Integer teamId;
	
	private String teamName;
	
	private Integer addScore;
	
	private Integer normalScore;
	
	private Integer totalScore;
	
	private Integer rank;
	
	private String teamCode;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	@Override
	public int compareTo(Object o) {
		StarEvaluateData data = (StarEvaluateData) o;
		int r = totalScore.compareTo(data.getTotalScore());		
		return (r != 0 ? -r : teamCode.compareTo(data.getTeamCode())); 
	}

	
}
