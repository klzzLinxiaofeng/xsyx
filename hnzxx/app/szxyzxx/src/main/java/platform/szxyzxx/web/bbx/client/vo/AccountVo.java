package platform.szxyzxx.web.bbx.client.vo;

public class AccountVo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 学校Id
	 */
	private Integer schoolId;
	
	/**
	 * 班级Id
	 */
	private Integer teamId;
	
	/**
	 * 班级uuid 
	 */
    private String teamUuid;

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

	
	public String getTeamUuid() {
		return teamUuid;
	}
	
	
	public void setTeamUuid(String teamUuid) {
		this.teamUuid = teamUuid;
	}
	
	
}
