package platform.education.paper.vo;

public class QuestionKnoledgeScoreVo {
	private static final long serialVersionUID = 1L;
	private String subjectCode="";
    private String subjecName="";
    private Float Score;
     public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjecName() {
		return subjecName;
	}
	public void setSubjecName(String subjecName) {
		this.subjecName = subjecName;
	}
	public Float getScore() {
		return Score;
	}
	public void setScore(Float score) {
		Score = score;
	}
}
