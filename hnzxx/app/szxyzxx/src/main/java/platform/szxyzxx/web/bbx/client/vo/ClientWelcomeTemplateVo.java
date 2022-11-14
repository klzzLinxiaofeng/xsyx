package platform.szxyzxx.web.bbx.client.vo;

public class ClientWelcomeTemplateVo {
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校id （pj_school.id）  
	 */
	private Integer schoolId;
	/**
	 * 班级id（pj_team.id）
	 */
	private Integer teamId;
	/**
	 * 图片文件uuid
	 */
	private String fileUuid;
	
	/**
	 * 图片路径  fileUrl
	 */
	private String fileUrl;

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

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	
	

}
