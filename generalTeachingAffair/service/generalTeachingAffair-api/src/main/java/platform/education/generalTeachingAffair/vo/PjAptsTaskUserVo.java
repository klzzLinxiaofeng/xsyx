package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.PjAptsTaskUser;

import java.util.Date;
import java.util.List;

/**
 * PjAptsTaskUser
 * @author AutoCreate
 *
 */
public class PjAptsTaskUserVo extends PjAptsTaskUser {
	private static final long serialVersionUID = 1L;
	
	private String teamName;
	
	private String  teacherName;

	private Integer teacherUserId;

	private Integer judgeScore;
	
	private String description;
	
	private String subjectName;

	private  String gradeName;
	
	private Date sortDate;

	private String weekNum;

	private  String weekNumChinese;

	public String getWeekNumChinese() {
		return weekNumChinese;
	}

	public void setWeekNumChinese(String weekNumChinese) {
		this.weekNumChinese = weekNumChinese;
	}

	private  String weekDay;
	
	private Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private List<Float> avgScores;

	public List<Float> getAvgScores() {
		return avgScores;
	}

	public void setAvgScores(List<Float> avgScores) {
		this.avgScores = avgScores;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getWeekNum() {
		return weekNum;
	}

	public void setWeekNum(String weekNum) {
		this.weekNum = weekNum;
	}

	public Date getSortDate() {
		return sortDate;
	}

	public void setSortDate(Date sortDate) {
		this.sortDate = sortDate;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getJudgeScore() {
		return judgeScore;
	}

	public void setJudgeScore(Integer judgeScore) {
		this.judgeScore = judgeScore;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Integer teacherUserId) {
		this.teacherUserId = teacherUserId;
	}
}