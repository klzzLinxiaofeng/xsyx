package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

public class ExtSyllabusVo {
	//课表Id
	private Integer syllabusId;
	
	//上午课程节数
	private Integer morning;
	
	//上午课程节数
	private Integer afternoon;
		
	//上午课程节数
	private Integer evening;
	
	//每天课程情况
	private List<ExtSyllabusDayVo> days;

	public Integer getSyllabusId() {
		return syllabusId;
	}

	public void setSyllabusId(Integer syllabusId) {
		this.syllabusId = syllabusId;
	}

	public Integer getMorning() {
		return morning;
	}

	public void setMorning(Integer morning) {
		this.morning = morning;
	}

	public Integer getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}

	public Integer getEvening() {
		return evening;
	}

	public void setEvening(Integer evening) {
		this.evening = evening;
	}

	public List<ExtSyllabusDayVo> getDays() {
		return days;
	}

	public void setDays(List<ExtSyllabusDayVo> days) {
		this.days = days;
	}
}
