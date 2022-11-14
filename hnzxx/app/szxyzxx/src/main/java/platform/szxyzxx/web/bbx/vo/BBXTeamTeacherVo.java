package platform.szxyzxx.web.bbx.vo;

public class BBXTeamTeacherVo implements Comparable<BBXTeamTeacherVo>{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 班级学段code
	 */
	private String stageCode;
	
	/**
	 * 班级id
	 */
	private Integer teamId;
	
	/**
	 *班号：在同一年级中的顺序编号
	 */
	private Integer teamNumber;
	
	/**
	 * 班级名称
	 */
	private String teamName;
	
	/**
	 * 年级id
	 */
	private Integer gradeId;
	
	/**
	 * 年级号：年级在学校中的顺序，如初二是2
	 */
	private Integer gradeNumber;
	
	/**
	 * 年级名称
	 */
	private String gradeName;

	/**
	 * 当前老师在此班级担任的最高职位，默认是班主任
	 */
	private Integer type = 1;
	
	
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getGradeNumber() {
		return gradeNumber;
	}







	public void setGradeNumber(Integer gradeNumber) {
		this.gradeNumber = gradeNumber;
	}







	public String getGradeName() {
		return gradeName;
	}







	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}




	public BBXTeamTeacherVo(){
		
		
	}


	public BBXTeamTeacherVo(String stageCode, Integer teamId,
			Integer teamNumber, String teamName, Integer gradeId,
			Integer gradeNumber, String gradeName) {
		super();
		this.stageCode = stageCode;
		this.teamId = teamId;
		this.teamNumber = teamNumber;
		this.teamName = teamName;
		this.gradeId = gradeId;
		this.gradeNumber = gradeNumber;
		this.gradeName = gradeName;
	}





	@Override
	public int compareTo(BBXTeamTeacherVo o) {
		
		return this.teamNumber.compareTo(o.getTeamNumber());
	}

}
