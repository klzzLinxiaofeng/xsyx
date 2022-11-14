package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.List;

public class BpSyllabusLessonDayVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String dayOfWeek;
	
	private List<BpSyllabusLessonDetailVo> lessonDetailList;

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public List<BpSyllabusLessonDetailVo> getLessonDetailList() {
		return lessonDetailList;
	}

	public void setLessonDetailList(List<BpSyllabusLessonDetailVo> lessonDetailList) {
		this.lessonDetailList = lessonDetailList;
	}
	
	

}
