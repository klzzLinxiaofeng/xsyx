package platform.szxyzxx.web.common.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

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

public class DataOfTeachingAffairTage extends RequestContextAwareTag {

	private static final long serialVersionUID = 5554813003668271076L;

	private final static GradeService gradeService = GeneralTeachingAffairServiceHolder.getInstance().getGradeService();
	
	private final static TeamService teamService = GeneralTeachingAffairServiceHolder.getInstance().getTeamService();
	
	private final static TeacherService teacherService  = GeneralTeachingAffairServiceHolder.getInstance().getTeacherService();
	
	private final static StudentService studentService =  GeneralTeachingAffairServiceHolder.getInstance().getStudentService();
	
	private final static SchoolService schoolService = GeneralTeachingAffairServiceHolder.getInstance().getSchoolService();

	public String type;
	public String code;

	private String outPutHtml = "";

	@SuppressWarnings("static-access")
	@Override
	protected int doStartTagInternal() throws Exception {
		String result = null;
		if ("grade".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Grade grade = gradeService.findGradeById(id);
				if (grade != null) {
					result = grade.getFullName();
				} else {
					result = "???";
				}
			} catch (Exception e) {
				result = "????????????????????????ID";
			}
		} else if ("team".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Team team = teamService.findTeamById(id);
				if (team != null) {
					result = team.getFullName();
				} else {
					result = "???";
				}
			} catch (Exception e) {
				result = "????????????????????????ID";
			}
		} else if ("teacher".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Teacher teacher = teacherService.findTeacherById(id);
				if (teacher != null) {
					result = teacher.getName();
				} else {
					result = "???";
				}
			} catch (Exception e) {
				result = "????????????????????????ID";
			}
		} else if ("stu".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				Student student = studentService.findStudentById(id);
				if (student != null) {
					result = student.getName();
				} else {
					result = "???";
				}
			} catch (Exception e) {
				result = "????????????????????????ID";
			}
		} else if ("school".equals(type)) {
			try {
				Integer id = Integer.parseInt(code);
				School school = schoolService.findSchoolById(id);
				if (school != null) {
					result = school.getName();
				} else {
					result = "???";
				}
			} catch (Exception e) {
				result = "????????????????????????ID";
			}
		}
		if (result != null) {
			outPutHtml = result.toString();
		}
		return this.EVAL_BODY_AGAIN;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(outPutHtml);
			outPutHtml = "";
		} catch (IOException e) {
			throw new JspException("??????????????????!", e);
		}
		return EVAL_PAGE;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
