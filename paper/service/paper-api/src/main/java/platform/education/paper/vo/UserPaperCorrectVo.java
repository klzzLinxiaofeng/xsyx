package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserPaperCorrectVo {
	private String questionType;
	private String questionUuid;
	private Integer pos;
	private String dbCorrectAnswer;
	private String dbAnswer;
	private Integer userId;
	private Integer userQuestionId;
	private String  userQuestionUuId;
	private Integer questionProperty;
	private String content;
	private Date createTime;
	private Date modifyTime;
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getQuestionProperty() {
		return questionProperty;
	}

	public void setQuestionProperty(Integer questionProperty) {
		this.questionProperty = questionProperty;
	}

	private List<Map<String, Object>> files = new ArrayList<Map<String, Object>>();
	
	public String getUserQuestionUuId() {
		return userQuestionUuId;
	}

	public void setUserQuestionUuId(String userQuestionUuId) {
		this.userQuestionUuId = userQuestionUuId;
	}

	private String []correctAnswer;
	private String []answer;
	
	private Double correctScore;
	private Double score;

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	

	public Double getCorrectScore() {
		return correctScore;
	}

	public void setCorrectScore(Double correctScore) {
		this.correctScore = correctScore;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getDbCorrectAnswer() {
		return dbCorrectAnswer;
	}

	public void setDbCorrectAnswer(String dbCorrectAnswer) {
		this.dbCorrectAnswer = dbCorrectAnswer;
	}

	public String getDbAnswer() {
		return dbAnswer;
	}

	public void setDbAnswer(String dbAnswer) {
		this.dbAnswer = dbAnswer;
	}

	public String[] getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserQuestionId() {
		return userQuestionId;
	}

	public void setUserQuestionId(Integer userQuestionId) {
		this.userQuestionId = userQuestionId;
	}

	public List<Map<String, Object>> getFiles() {
		return files;
	}

	public void setFiles(List<Map<String, Object>> files) {
		this.files = files;
	}

}
