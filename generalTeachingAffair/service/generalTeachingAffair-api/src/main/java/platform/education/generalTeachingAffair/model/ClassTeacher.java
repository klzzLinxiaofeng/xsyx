package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * 班主任
 * @author admin
 *
 */
public class ClassTeacher implements Model<Integer>{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	//班级ID
	private int teamId;
	//年级ID
	private int gradeId;
	//班级CODE
	private String code;
	//班级名称
	private String teamName;
	//教师ID
	private int teacherId;
	//教师名称
	private String teacherName;
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public Integer getKey() {
		// TODO Auto-generated method stub
		return null;
	}
}
