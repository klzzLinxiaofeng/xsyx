package platform.education.paper.model.pc;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * PC端Paper model
 * @author pantq
 *
 */

public class PcPaper implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer userId; 
    private String  paperId; 
    private String  paperTitle; 
    private Integer paperType; 
    private String  subjectCode; 
    private String  publishCode; 
    private String  gradeCode;
    private String  volumeCode;
    private String  bookId; 
    private String  bookUnitId;
    private String  bookSectionId;
    private String  knowledge; 
    private Double  difficulty;
    private String  bookName; 
    private String  bookUnitName; 
    private String  bookSectionName; 
    private Date    createTime;
    private Float   score;
    
    private List<PcQuestion> questions;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPaperId() {
		return paperId;
	}
	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public Integer getPaperType() {
		return paperType;
	}
	public void setPaperType(Integer paperType) {
		this.paperType = paperType;
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
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookUnitName() {
		return bookUnitName;
	}
	public void setBookUnitName(String bookUnitName) {
		this.bookUnitName = bookUnitName;
	}
	public String getBookSectionName() {
		return bookSectionName;
	}
	public void setBookSectionName(String bookSectionName) {
		this.bookSectionName = bookSectionName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public List<PcQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<PcQuestion> questions) {
		this.questions = questions;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Double difficulty) {
		this.difficulty = difficulty;
	}
	
}
