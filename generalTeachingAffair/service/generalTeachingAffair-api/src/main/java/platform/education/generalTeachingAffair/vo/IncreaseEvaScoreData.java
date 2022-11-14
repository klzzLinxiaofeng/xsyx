package platform.education.generalTeachingAffair.vo;

/**
 * 学生激励评价总数记录
 */
public class IncreaseEvaScoreData {
	private Integer number;
	
	private Integer studentId;
	
	private String studentName;

	private Integer count;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

}
