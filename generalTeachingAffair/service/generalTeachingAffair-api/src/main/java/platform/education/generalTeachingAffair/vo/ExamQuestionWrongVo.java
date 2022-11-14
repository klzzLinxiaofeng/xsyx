package platform.education.generalTeachingAffair.vo;


public class ExamQuestionWrongVo {
	private String content;
	
	private Double difficulity;
	
	private String teamScoringRite;
	
	private String groupId;
	
	private String complexTitle;
	
	private String answer;
	
	private String correctAnswer;
	
	private String explanation;
	
	private String questionUuid;
	
	private String questionType;
	
	private  float  teamScoringRate;
  
	private  float gradeScoringRate;
	
	private Integer averageTime;
	
	private Integer answerCount;
	
	private Integer rightAnswerCount;
	
	private Integer parentId;
	
	private Integer number;
	
	private Integer score;
	
	private String paperTitle;
	
	private Integer questionId;
	
	private String parentType;
    public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getPaperTitle() {
		return paperTitle;
	}

	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(Integer averageTime) {
		this.averageTime = averageTime;
	}

	public Integer getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(Integer answerCount) {
		this.answerCount = answerCount;
	}

	public Integer getRightAnswerCount() {
		return rightAnswerCount;
	}

	public void setRightAnswerCount(Integer rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}

	private  Integer examQuestionId;
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeamScoringRite() {
		return teamScoringRite;
	}

	public void setTeamScoringRite(String teamScoringRite) {
		this.teamScoringRite = teamScoringRite;
	}


	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getComplexTitle() {
		return complexTitle;
	}

	public void setComplexTitle(String complexTitle) {
		this.complexTitle = complexTitle;
	}


	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * 
	 */
	

	public Double getDifficulity() {
		return difficulity;
	}

	public void setDifficulity(Double difficulity) {
		this.difficulity = difficulity;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public float getTeamScoringRate() {
		return teamScoringRate;
	}

	public void setTeamScoringRate(float teamScoringRate) {
		this.teamScoringRate = teamScoringRate;
	}

	public float getGradeScoringRate() {
		return gradeScoringRate;
	}

	public void setGradeScoringRate(float gradeScoringRate) {
		this.gradeScoringRate = gradeScoringRate;
	}

	public Integer getExamQuestionId() {
		return examQuestionId;
	}

	public void setExamQuestionId(Integer examQuestionId) {
		this.examQuestionId = examQuestionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
