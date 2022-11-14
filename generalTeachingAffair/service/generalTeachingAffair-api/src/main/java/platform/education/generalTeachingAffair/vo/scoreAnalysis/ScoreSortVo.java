package platform.education.generalTeachingAffair.vo.scoreAnalysis;

import java.io.Serializable;

public class ScoreSortVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//表主键
	private int id;
	//排名
	private int teamRank;
	//得分
	private float score;
	//班级总分
	private float totalScore;
	//班级平均分
	private float averageScore;
	//年级排名
	private int gradeRank;
	
	public int getGradeRank() {
		return gradeRank;
	}
	public void setGradeRank(int gradeRank) {
		this.gradeRank = gradeRank;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}
	public float getAverageScore() {
		return averageScore;
	}
	public void setAverageScore(float averageScore) {
		this.averageScore = averageScore;
	}
	public int getTeamRank() {
		return teamRank;
	}
	public void setTeamRank(int teamRank) {
		this.teamRank = teamRank;
	}
}
