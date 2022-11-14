package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ExamWorks;
/**
 * ExamWorks
 * @author AutoCreate
 *
 */
public class ExamWorksVo extends ExamWorks {
	private static final long serialVersionUID = 1L;

	private String gradeName;

	private String teamName;

	private String subjectName;

	private Integer teamNumber;

	private Float fullScore;

	private Float highScore;

	private Float lowScore;

	private Float passScore;

	private Integer posterId;

	private Integer publisherId;

	private Float totalScore;

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public Float getHighScore() {
		return highScore;
	}

	public void setHighScore(Float highScore) {
		this.highScore = highScore;
	}

	public Float getLowScore() {
		return lowScore;
	}

	public void setLowScore(Float lowScore) {
		this.lowScore = lowScore;
	}

	public Float getPassScore() {
		return passScore;
	}

	public void setPassScore(Float passScore) {
		this.passScore = passScore;
	}

	public Integer getPosterId() {
		return posterId;
	}

	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}

	public Integer getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
}