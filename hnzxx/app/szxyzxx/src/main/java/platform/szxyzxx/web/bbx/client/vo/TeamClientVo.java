package platform.szxyzxx.web.bbx.client.vo;

public class TeamClientVo  implements Comparable<TeamClientVo>{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 班级id
	 */
	private Integer teamId;
	/**
	 * 班级名称
	 */
	private String teamName;
	
	/**
	 *班号：在同一年级中的顺序编号
	 */
	private Integer teamNumber;
	
	/**
	 * 班级是否已经开通班级账号
	 */
	private Integer openState;
	
	
	public TeamClientVo() {
		
	}
	
	
	public TeamClientVo(Integer teamId, String teamName, Integer teamNumber,
			Integer openState) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.teamNumber = teamNumber;
		this.openState = openState;
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
	
	
	
	public Integer getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(Integer teamNumber) {
		this.teamNumber = teamNumber;
	}
	@Override
	public int compareTo(TeamClientVo o) {
		return this.teamNumber.compareTo(o.getTeamNumber());
	}


	public Integer getOpenState() {
		return openState;
	}


	public void setOpenState(Integer openState) {
		this.openState = openState;
	}
	
	
	
	
}
