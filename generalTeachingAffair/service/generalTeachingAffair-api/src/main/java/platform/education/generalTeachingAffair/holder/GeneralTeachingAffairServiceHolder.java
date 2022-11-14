package platform.education.generalTeachingAffair.holder;

import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;

public class GeneralTeachingAffairServiceHolder {

	private static GeneralTeachingAffairServiceHolder instance;

	private GradeService gradeService;

	private TeamService teamService;

	private TeacherService teacherService;

	private StudentService studentService;

	private SchoolService schoolService;

	public GeneralTeachingAffairServiceHolder() {
		instance = this;
	}

	public static GeneralTeachingAffairServiceHolder getInstance() {
		return instance;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public SchoolService getSchoolService() {
		return schoolService;
	}

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

}
