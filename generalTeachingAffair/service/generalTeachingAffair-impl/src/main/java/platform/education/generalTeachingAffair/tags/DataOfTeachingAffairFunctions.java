package platform.education.generalTeachingAffair.tags;

import platform.education.generalTeachingAffair.holder.GeneralTeachingAffairServiceHolder;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;

public class DataOfTeachingAffairFunctions {
	
	private final static GradeService gradeService = GeneralTeachingAffairServiceHolder.getInstance().getGradeService();
	
	private final static TeamService teamService = GeneralTeachingAffairServiceHolder.getInstance().getTeamService();
	
	private final static TeacherService teacherService = GeneralTeachingAffairServiceHolder.getInstance().getTeacherService();
	
	private final static StudentService studentService = GeneralTeachingAffairServiceHolder.getInstance().getStudentService();
	
	private final static SchoolService schoolService = GeneralTeachingAffairServiceHolder.getInstance().getSchoolService();
	
	public static String getFieldVal(String type, String code) {
		String result = null;
		if("grade".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Grade grade = gradeService.findGradeById(id);
				if (grade != null) {
					result = grade.getFullName();
				} else {
					result = "无";
				}
			} catch (Exception e) {
				result = "请传入年级的主键ID";
			}
		} else if ("team".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Team team = teamService.findTeamById(id);
				if (team != null) {
					result = team.getFullName();
				} else {
					result = "无";
				}
			} catch (Exception e) {
				result = "请传入班级的主键ID";
			}
		} else if ("teacher".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Teacher teacher = teacherService.findTeacherById(id);
				if (teacher != null) {
					result = teacher.getName();
				} else {
					result = "无";
				}
			} catch (Exception e) {
				result = "请传入教师的主键ID";
			}
		} else if ("stu".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Student student = studentService.findStudentById(id);
				if (student != null) {
					result = student.getName();
				} else {
					result = "无";
				}
			} catch (Exception e) {
				result = "请传入学生的主键ID";
			}
		} else if ("school".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				School school = schoolService.findSchoolById(id);
				if (school != null) {
					result = school.getName();
				} else {
					result = "无";
				}
			} catch (Exception e) {
				result = "请传入学校的主键ID";
			}
		}
		return result;
	}
	
}
