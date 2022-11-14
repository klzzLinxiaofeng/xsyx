package platform.szxyzxx.web.statistic.vo;

import java.io.Serializable;
import java.util.Date;

public class TeamBasicVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer teamId;
	private String teamName;
	private String subjectCode;
	private String subjectName;
	private Date examDate;
	private Integer teacherId;
	private String teacherName;
	private Float fullScore;
	private Integer studentCount;
	private Float passScore;
	private Integer questionCount;
	private Float avgScore;
	private Float hightScore;
	private Float lowScore;
	private Integer gradeRank;
	private Float sdScore;
	private Float madValue;
	private Float movValue;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Float getFullScore() {
		return fullScore;
	}

	public void setFullScore(Float fullScore) {
		this.fullScore = fullScore;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	public Float getPassScore() {
		return passScore;
	}

	public void setPassScore(Float passScore) {
		this.passScore = passScore;
	}

	public Integer getQuestionCount() {
		return questionCount;
	}

	public void setQuestionCount(Integer questionCount) {
		this.questionCount = questionCount;
	}

	public Float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Float avgScore) {
		this.avgScore = avgScore;
	}

	public Float getHightScore() {
		return hightScore;
	}

	public void setHightScore(Float hightScore) {
		this.hightScore = hightScore;
	}

	public Float getLowScore() {
		return lowScore;
	}

	public void setLowScore(Float lowScore) {
		this.lowScore = lowScore;
	}

	public Integer getGradeRank() {
		return gradeRank;
	}

	public void setGradeRank(Integer gradeRank) {
		this.gradeRank = gradeRank;
	}

	public Float getSdScore() {
		return sdScore;
	}

	public void setSdScore(Float sdScore) {
		this.sdScore = sdScore;
	}

	public Float getMadValue() {
		return madValue;
	}

	public void setMadValue(Float madValue) {
		this.madValue = madValue;
	}

	public Float getMovValue() {
		return movValue;
	}

	public void setMovValue(Float movValue) {
		this.movValue = movValue;
	}
}
