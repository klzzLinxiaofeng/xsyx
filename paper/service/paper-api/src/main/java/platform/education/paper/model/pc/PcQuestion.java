package platform.education.paper.model.pc;

import java.io.Serializable;
import java.util.List;

/**
 * Pc端question模型
 * @author pantq
 *
 */

public class PcQuestion implements Serializable{

	 /**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	 private List<PcQuestion> questions;
     private String num; 
     private String[] answer;
     private String[] correctAnswer; 
     private String type; 
     private String content; 
     private String extraContent;
     private String questionId;
     private Integer pos; 
     private Double difficulty; 
     private String explanation;
     private String[] examPoints;
     private String comment;
     private String subjectCode;
     private String publishCode;
     private String gradeCode;
     private String volumeCode;
     private String  bookId;
     private String  bookUnitId;
     private String bookSectionId;
     private String knowledge;
     private Integer  knowledgeId ;
     private Integer isSubjective;
     private String groudId;
     private String groupTitle; 
     private Double score;
     private String cognition;
	
	
	
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
	public String getExtraContent() {
		return extraContent;
	}
	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	public Double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Double difficulty) {
		this.difficulty = difficulty;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getPublishCode() {
		return publishCode;
	}
	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getVolumeCode() {
		return volumeCode;
	}
	public void setVolumeCode(String volumeCode) {
		this.volumeCode = volumeCode;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookUnitId() {
		return bookUnitId;
	}
	public void setBookUnitId(String bookUnitId) {
		this.bookUnitId = bookUnitId;
	}
	public String getBookSectionId() {
		return bookSectionId;
	}
	public void setBookSectionId(String bookSectionId) {
		this.bookSectionId = bookSectionId;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	
	public Integer getIsSubjective() {
		return isSubjective;
	}
	public void setIsSubjective(Integer isSubjective) {
		this.isSubjective = isSubjective;
	}
	public String getGroudId() {
		return groudId;
	}
	public void setGroudId(String groudId) {
		this.groudId = groudId;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getCognition() {
		return cognition;
	}
	public void setCognition(String cognition) {
		this.cognition = cognition;
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
	public void setExamPoints(String[] examPoints) {
		this.examPoints = examPoints;
	}
	public Integer getKnowledgeId() {
		return knowledgeId;
	}
	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}
	public String[] getExamPoints() {
		return examPoints;
	}
	public void setQuestions(List<PcQuestion> questions) {
		this.questions = questions;
	}
	public List<PcQuestion> getQuestions() {
		return questions;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

}
