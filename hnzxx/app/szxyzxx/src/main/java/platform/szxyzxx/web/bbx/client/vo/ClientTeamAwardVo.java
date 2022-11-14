package platform.szxyzxx.web.bbx.client.vo;


public class ClientTeamAwardVo {

private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 对应学校   pj_school.id
	 */
	private Integer schoolId;
	/**
	 * 对应班级  pj_team.id
	 */
	private Integer teamId;
	
	/**
	 * 对应班级名称  pj_team.name
	 */
	private String teanName;
	
	
	/**
	 * 荣誉称号
	 */
	private String name;

	/**
	 * 图像id
	 */
	private String awardImage;
	
	/**
	 * 图像URL
	 */
	private String awardImageURL ;
	
	
	/**
	 * 说明
	 */
	private String comment;
	/**
	 * 学年  pj_school_year.year
	 */
	private String schoolYear;
	/**
	 * 学期代码   XY-JY-XQ
	 */
	private String termCode;
	/**
	 * 班级荣誉获得时间
	 */
	private String awardTime;
	
	/**
	 * 班级荣誉记录创建时间
	 */
	private String createDate;
	
	/**
	 * 班级荣誉记录修改时间
	 */
	private String modifyDate;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	
	public String getTeanName() {
		return teanName;
	}
	
	public void setTeanName(String teanName) {
		this.teanName = teanName;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAwardImage() {
		return awardImage;
	}

	public void setAwardImage(String awardImage) {
		this.awardImage = awardImage;
	}

	
	public String getAwardImageURL() {
		return awardImageURL;
	}
	
	
	public void setAwardImageURL(String awardImageURL) {
		this.awardImageURL = awardImageURL;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
	
}
