package platform.szxyzxx.web.schoolaffair.vo;

public class AptTaskItemImportVo {
	//项目ID
	private Integer id;
	//项目名称
	private String name;
	//对应项目所得分数
	private String score;
	//数据格式等错误信息
	private String errorMessage;
	//是否需要创建 ，需要创建这里的scoreId为空  不需要则有值
	private Integer scoreId;
	
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
}
