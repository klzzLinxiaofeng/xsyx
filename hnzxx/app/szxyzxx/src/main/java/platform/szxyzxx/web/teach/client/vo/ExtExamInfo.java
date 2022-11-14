package platform.szxyzxx.web.teach.client.vo;

//某个考试的信息
public class ExtExamInfo {
	//考试Id
	private Integer id;
	//任课老师名称
	private String teacherName;
	//考试时间
	private String examDate;
	//应考人数（值为0表示还没有录入成绩）
	private Integer studentCount;
	//满分分数
	private String fullScore;
	//高分分数
	private String hightScore;
	//低分分数
	private String lowScore;
	//及格分数
	private String passScore;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	public String getFullScore() {
		return fullScore;
	}
	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
	}
	public String getHightScore() {
		return hightScore;
	}
	public void setHightScore(String hightScore) {
		this.hightScore = hightScore;
	}
	public String getLowScore() {
		return lowScore;
	}
	public void setLowScore(String lowScore) {
		this.lowScore = lowScore;
	}
	public String getPassScore() {
		return passScore;
	}
	public void setPassScore(String passScore) {
		this.passScore = passScore;
	}
}
