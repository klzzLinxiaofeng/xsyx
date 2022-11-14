package platform.education.paper.vo;
import java.util.Date;

import platform.education.paper.model.TaskInterscore;
/**
 * TaskInterscore
 * @author AutoCreate
 *
 */
public class TaskInterscoreVo extends TaskInterscore {
	private static final long serialVersionUID = 1L;
	private String title;
    private String subjectCode;
    private Date   startTime;
    private Date   finishTime;
    private String difficultyString;
    private Float score;
    private String catalogCode;

    

	public String getCatalogCode() {
		return catalogCode;
	}
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getDifficultyString() {
		return difficultyString;
	}
	public void setDifficultyString(String difficultyString) {
		this.difficultyString = difficultyString;
	}
}