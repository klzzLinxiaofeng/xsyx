package platform.education.rest.bp.service.vo;

import java.io.Serializable;

public class BpSyllabusLessonTimeVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer lesson;
	
	private String startTime;
	
	private String endTime;

	public Integer getLesson() {
		return lesson;
	}

	public void setLesson(Integer lesson) {
		this.lesson = lesson;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
