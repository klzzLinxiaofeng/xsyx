package platform.education.rest.bp.service.vo;
import java.util.List;

import platform.education.clazz.model.Course;
import platform.education.clazz.model.CourseConfig;
import platform.education.clazz.vo.CourseConfigDetailVo;
/**
 * CourseConfig
 * @author AutoCreate
 *
 */
public class BpCourseStudentVo extends CourseConfig {
	private static final long serialVersionUID = 1L;

	private List<CourseConfigDetailVo> courseConfigDetailVoList;

	private List<Course> courseList;

	private String prompt;


	public List<CourseConfigDetailVo> getCourseConfigDetailVoList() {
		return courseConfigDetailVoList;
	}

	public void setCourseConfigDetailVoList(
			List<CourseConfigDetailVo> courseConfigDetailVoList) {
		this.courseConfigDetailVoList = courseConfigDetailVoList;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}


}