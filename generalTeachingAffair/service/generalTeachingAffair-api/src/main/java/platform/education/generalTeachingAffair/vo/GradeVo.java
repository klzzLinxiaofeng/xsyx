package platform.education.generalTeachingAffair.vo;
import java.util.List;

import platform.education.generalTeachingAffair.model.Grade;
/**
 * Grade
 * @author AutoCreate
 *
 */
public class GradeVo extends Grade {
	private static final long serialVersionUID = 1L;
	private Integer classNumber;
	private Integer teacherNumber;
	private Integer headTeacherNumber;
	private List<SubjectVo> subjectList;
	
	/* 学生年级统计 2015-11-05*/
	private Long teamNumber;   //班级数
	
	private Long StudentNumber;   //总人数
	
	private Long manNumber;   //男生数
	
	private Long womanNumber;  //女生数
	
	private Long untoldNumber; //未标明性别
	/* 学生年级统计 2015-11-05*/
	
	public List<SubjectVo> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<SubjectVo> subjectList) {
		this.subjectList = subjectList;
	}
	public Integer getClassNumber() {
		return classNumber;
	}
	public void setClassNumber(Integer classNumber) {
		this.classNumber = classNumber;
	}
	public Integer getTeacherNumber() {
		return teacherNumber;
	}
	public void setTeacherNumber(Integer teacherNumber) {
		this.teacherNumber = teacherNumber;
	}
	public Integer getHeadTeacherNumber() {
		return headTeacherNumber;
	}
	public void setHeadTeacherNumber(Integer headTeacherNumber) {
		this.headTeacherNumber = headTeacherNumber;
	}
	public Long getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(Long teamNumber) {
		this.teamNumber = teamNumber;
	}
	public Long getStudentNumber() {
		return StudentNumber;
	}
	public void setStudentNumber(Long studentNumber) {
		StudentNumber = studentNumber;
	}
	public Long getManNumber() {
		return manNumber;
	}
	public void setManNumber(Long manNumber) {
		this.manNumber = manNumber;
	}
	public Long getWomanNumber() {
		return womanNumber;
	}
	public void setWomanNumber(Long womanNumber) {
		this.womanNumber = womanNumber;
	}
	public Long getUntoldNumber() {
		return untoldNumber;
	}
	public void setUntoldNumber(Long untoldNumber) {
		this.untoldNumber = untoldNumber;
	}
	
}