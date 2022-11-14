package platform.szxyzxx.services.vo;

public class TeamQuestionVo {
	   private static final long serialVersionUID = 1L;
	   private String questionUuid;	
	   private float teamRight;
	   private Integer examId;
	public String getQuestionUuid() {
		return questionUuid;
	}
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}
	public float getTeamRight() {
		return teamRight;
	}
	public void setTeamRight(float teamRight) {
		this.teamRight = teamRight;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
}
