package platform.education.generalTeachingAffair.vo;

import java.util.List;

import framework.generic.dao.Model;

public class StudentMergeParentVo implements Model<Integer>{
	
	private static final long serialVersionUID = 1L;

	
	private String studentNumber;//学生唯一学籍号
	private String studentName;//学生姓名
	private Integer studentId;//学生id
	private Integer studentParentNum;
	private Integer teamId;//班级id
	private String teamName;//班级名称
	private Integer schoolId;//学校id
	private String schoolName;//学校名称
	private List<ParentVo> parentVoList;//家长信息集合
	
	
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


	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}


	public String getSchoolName() {
		return schoolName;
	}


	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}


	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return this.studentId;
	}


	public String getStudentNumber() {
		return studentNumber;
	}


	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}


	public Integer getStudentParentNum() {
		return studentParentNum;
	}


	public void setStudentParentNum(Integer studentParentNum) {
		this.studentParentNum = studentParentNum;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public List<ParentVo> getParentVoList() {
		return parentVoList;
	}


	public void setParentVoList(List<ParentVo> parentVoList) {
		this.parentVoList = parentVoList;
	}
	
	
	

}
