package platform.education.generalTeachingAffair.vo;

public class TeamAvgScroeVo {
    
	private Integer ExamId;
	
	private String teamName;
	
	private float percent;

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getExamId() {
		return ExamId;
	}

	public void setExamId(Integer examId) {
		ExamId = examId;
	}


	public Integer getTeamNum() {
		return teamNum;
	}

	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}

	public Float getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}

	public Float getGradeAvg() {
		return GradeAvg;
	}

	public void setGradeAvg(Float gradeAvg) {
		GradeAvg = gradeAvg;
	}


	private Integer teamNum;
	
	private Float averageScore;
	
	private Float totalScore;
	



	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}


	private Float GradeAvg;
	
	private Integer teamRank;
	
	public Integer getTeamRank() {
		return teamRank;
	}

	public void setTeamRank(Integer teamRank) {
		this.teamRank = teamRank;
	}


	private Integer studentCount;

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
}
