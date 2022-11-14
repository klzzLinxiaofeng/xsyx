package platform.education.rest.bp.service.vo;

public class CommonHomework {
	private Integer id;
	/**
	 * 被布置作业的班级   team.id
	 */
	private Integer teamId;
	/**
	 * 作业科目  subject.code
	 */
	private String teamName;
	
	private String subjectCode;
	/**
	 * termCode
	 */
	private String termCode;
	/**
	 * 发送者userId  user.id
	 */
	private Integer postId;
	/**
	 * 作业内容
	 */
	private String content;
	
	private String subjectName;
	
	/**
	 * 发送者姓名
	 */
	private String posterName;
	
	/**
	 * 发送者所属校区名称
	 */
	private String posterSchoolName;
	
	/**
	 * 发送者头像路径
	 */
	private String posterIcon;
	
	/**
	 * 是否查阅通知标志
	 */
	private Integer hasRead;
	
	
	private String createDate;
	
	private String modifyDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getPosterSchoolName() {
		return posterSchoolName;
	}

	public void setPosterSchoolName(String posterSchoolName) {
		this.posterSchoolName = posterSchoolName;
	}

	public String getPosterIcon() {
		return posterIcon;
	}

	public void setPosterIcon(String posterIcon) {
		this.posterIcon = posterIcon;
	}
	
	
	public Integer getHasRead() {
		return hasRead;
	}
	
	
	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}	
	
	

}
