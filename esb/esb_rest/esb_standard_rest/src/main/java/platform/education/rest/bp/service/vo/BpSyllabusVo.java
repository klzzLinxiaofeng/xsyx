package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.List;

public class BpSyllabusVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<BpSyllabusLessonTimeVo> syllabusLessonTimeList;
	
	private List<BpSyllabusLessonDayVo> lessonDayList;

	public List<BpSyllabusLessonTimeVo> getSyllabusLessonTimeList() {
		return syllabusLessonTimeList;
	}

	public void setSyllabusLessonTimeList(
			List<BpSyllabusLessonTimeVo> syllabusLessonTimeList) {
		this.syllabusLessonTimeList = syllabusLessonTimeList;
	}

	public List<BpSyllabusLessonDayVo> getLessonDayList() {
		return lessonDayList;
	}

	public void setLessonDayList(List<BpSyllabusLessonDayVo> lessonDayList) {
		this.lessonDayList = lessonDayList;
	}
	
	

}
