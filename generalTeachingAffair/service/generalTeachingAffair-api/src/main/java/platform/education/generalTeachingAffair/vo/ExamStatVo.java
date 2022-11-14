package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ExamStat;
/**
 * ExamStat
 * @author AutoCreate
 *
 */
public class ExamStatVo extends ExamStat {
	private static final long serialVersionUID = 1L;
	
	private float max_score;
	
	private float min_score;
	
	private Long nextExamStudent;

	//在年级中的平均分排名
	private Integer rank;

	private String teamName;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public float getMax_score() {
		return max_score;
	}

	public void setMax_score(float max_score) {
		this.max_score = max_score;
	}

	public float getMin_score() {
		return min_score;
	}

	public void setMin_score(float min_score) {
		this.min_score = min_score;
	}

	public Long getNextExamStudent() {
		return nextExamStudent;
	}

	public void setNextExamStudent(Long nextExamStudent) {
		this.nextExamStudent = nextExamStudent;
	}
	
	
}