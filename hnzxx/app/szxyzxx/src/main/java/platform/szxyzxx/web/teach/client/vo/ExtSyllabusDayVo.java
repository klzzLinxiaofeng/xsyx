package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

public class ExtSyllabusDayVo {
	//星期代码
	private String day;
	
	//某星期的所有课程节次
	private List<ExtSyllabusLessonVo> lessons;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<ExtSyllabusLessonVo> getLessons() {
		return lessons;
	}

	public void setLessons(List<ExtSyllabusLessonVo> lessons) {
		this.lessons = lessons;
	}
	
}
