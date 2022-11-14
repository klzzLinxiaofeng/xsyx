package platform.education.generalTeachingAffair.vo;

import java.util.List;

import platform.education.generalTeachingAffair.model.ExamTeamSubject;
import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.model.Subject;

public class ExamScoreVo{
	
	
	private List<StudentScoreSort> studentScoreSort;//每次考试的学生成绩
	private String examType;//考试类别
	private String examName;//考试名称
	private String stuAverage;//平均成绩
	private String stuTotal;//总成绩
	private String stuTeamRank;//班级排名
	private String stuGradeRank;//年级排名
	private Integer studentId;//学生id
	private String studentName;//学生名称
	private String studentNumber;
	private Integer scoreNum;
	private String examTypeName;//考试类别名称
	
	
	public String getExamTypeName() {
		return examTypeName;
	}

	public void setExamTypeName(String examTypeName) {
		this.examTypeName = examTypeName;
	}

	public Integer getScoreNum() {
		return scoreNum;
	}

	public void setScoreNum(Integer scoreNum) {
		this.scoreNum = scoreNum;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStuTeamRank() {
		return stuTeamRank;
	}

	public void setStuTeamRank(String stuTeamRank) {
		this.stuTeamRank = stuTeamRank;
	}

	public String getStuGradeRank() {
		return stuGradeRank;
	}

	public void setStuGradeRank(String stuGradeRank) {
		this.stuGradeRank = stuGradeRank;
	}

	public String getStuTotal() {
		return stuTotal;
	}

	public void setStuTotal(String stuTotal) {
		this.stuTotal = stuTotal;
	}

	public String getStuAverage() {
		return stuAverage;
	}

	public void setStuAverage(String stuAverage) {
		this.stuAverage = stuAverage;
	}



	public String getExamType() {
		return examType;
	}
	
	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public List<StudentScoreSort> getStudentScoreSort() {
		return studentScoreSort;
	}

	public void setStudentScoreSort(List<StudentScoreSort> studentScoreSort) {
		this.studentScoreSort = studentScoreSort;
	}
	
	
}
