package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

import platform.education.clazz.vo.TeamAwardVo;

public class TeamAwardClientVo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 班级荣誉的list
	 */
	
	private List<TeamAwardVo>teamAwardVoList;
	
	
	/**
	 * 数据总条数
	 */
	private Integer totalRow;
	
	
	public Integer getTotalRow() {
		return totalRow;
	}


	public void setTotalRow(Integer totalRow) {
		this.totalRow = totalRow;
	}
	

	public List<TeamAwardVo> getTeamAwardVoList() {
		return teamAwardVoList;
	}


	public void setTeamAwardVoList(List<TeamAwardVo> teamAwardVoList) {
		this.teamAwardVoList = teamAwardVoList;
	}



	
	
	
}
