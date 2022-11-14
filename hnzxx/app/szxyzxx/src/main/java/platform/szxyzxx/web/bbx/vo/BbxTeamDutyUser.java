package platform.szxyzxx.web.bbx.vo;

import java.util.Date;
import java.util.List;

import platform.education.clazz.model.TeamDutyUser;
import platform.education.clazz.vo.TeamDutyUserVo;

public class BbxTeamDutyUser {
	private static final long serialVersionUID = 1L;
	
	private List<TeamDutyUserVo> tduList;
	
	private String dayOfWeek;
	
	private Date dutyDate;
	
	private Integer status;
	
	public BbxTeamDutyUser(){
		
	}
	



	public BbxTeamDutyUser(List<TeamDutyUserVo> tduList, String dayOfWeek, Date dutyDate) {
		this.tduList = tduList;
		this.dayOfWeek = dayOfWeek;
		this.dutyDate = dutyDate;
	}


	public List<TeamDutyUserVo> getTduList() {
		return tduList;
	}

	public void setTduList(List<TeamDutyUserVo> tduList) {
		this.tduList = tduList;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}


	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	public Date getDutyDate() {
		return dutyDate;
	}

	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
