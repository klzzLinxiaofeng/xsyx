package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssemblyQuestion {
	private String id;
	private Integer pos;
	private Integer num;
	private Float score;
	private String memo;
	private String[] answer;
	private String[] correctAnswer;
	private String type;
	private String content;
	private String explanation;
	private Float difficulty;
	private String[] knowledges;
	private String isSubjective;
	private Map<String, Object> subject;
	private String cognition;
	private List<AssemblyQuestion> questions = new ArrayList<AssemblyQuestion>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public String[] getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Float getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Float difficulty) {
		this.difficulty = difficulty;
	}

	public String[] getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(String[] knowledges) {
		this.knowledges = knowledges;
	}

	public String getIsSubjective() {
		return isSubjective;
	}

	public void setIsSubjective(String isSubjective) {
		this.isSubjective = isSubjective;
	}

	public Map<String, Object> getSubject() {
		return subject;
	}

	public void setSubject(Map<String, Object> subject) {
		this.subject = subject;
	}

	public String getCognition() {
		return cognition;
	}

	public void setCognition(String cognition) {
		this.cognition = cognition;
	}

	public List<AssemblyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AssemblyQuestion> questions) {
		this.questions = questions;
	}
}
