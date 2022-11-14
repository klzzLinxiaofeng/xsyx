package platform.szxyzxx.web.teach.client.vo;

public class ExtExamStudentVo {
	
	//学生ID（studentId）
	private Integer id;
	
	//学生姓名（别名）
	private String name;
	
	//学生分数
	private Float score;
	
	//考试类型（01 = 正常考试   03 = 缺考   11 = 补考）
	private String type;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
