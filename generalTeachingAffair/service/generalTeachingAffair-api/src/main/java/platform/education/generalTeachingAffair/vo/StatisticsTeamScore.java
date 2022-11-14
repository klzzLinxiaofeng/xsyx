package platform.education.generalTeachingAffair.vo;

import java.util.List;
import java.util.Map;

public class StatisticsTeamScore {
	
	private float gradeScoreAvg;
	
	private List<TeamAvgScroeVo>  teamAvgScore;
	
	private Map<Integer,TeamAvgScroeVo> teamMap;


	public Map<Integer, TeamAvgScroeVo> getTeamMap() {
		return teamMap;
	}

	public void setTeamMap(Map<Integer, TeamAvgScroeVo> teamMap) {
		this.teamMap = teamMap;
	}

	public float getGradeScoreAvg() {
		return gradeScoreAvg;
	}

	public void setGradeScoreAvg(float gradeScoreAvg) {
		this.gradeScoreAvg = gradeScoreAvg;
	}

	public List<TeamAvgScroeVo> getTeamAvgScore() {
		return teamAvgScore;
	}

	public void setTeamAvgScore(List<TeamAvgScroeVo> teamAvgScore) {
		this.teamAvgScore = teamAvgScore;
	}

}
