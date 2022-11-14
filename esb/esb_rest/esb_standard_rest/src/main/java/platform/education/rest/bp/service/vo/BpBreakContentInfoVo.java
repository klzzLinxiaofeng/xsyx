package platform.education.rest.bp.service.vo;

import platform.education.clazz.vo.BpBwPictureAlbumVo;

import java.io.Serializable;
import java.util.List;

public class BpBreakContentInfoVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<DutyUser> tdulist;
	
	BpPraiseClientVo bpPraiseClientVo;
	
	private List<ClientTeamAwardVo> clientTeamAwardVoList;
	
	private List<BpBwFileVo> bpBwFileVoList;
	
	private List<BpBwPictureAlbumVo> albumList;
	
	

	public List<BpBwPictureAlbumVo> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<BpBwPictureAlbumVo> albumList) {
		this.albumList = albumList;
	}

	public List<DutyUser> getTdulist() {
		return tdulist;
	}

	public void setTdulist(List<DutyUser> tdulist) {
		this.tdulist = tdulist;
	}

	public BpPraiseClientVo getBpPraiseClientVo() {
		return bpPraiseClientVo;
	}

	public void setBpPraiseClientVo(BpPraiseClientVo bpPraiseClientVo) {
		this.bpPraiseClientVo = bpPraiseClientVo;
	}

	public List<ClientTeamAwardVo> getClientTeamAwardVoList() {
		return clientTeamAwardVoList;
	}

	public void setClientTeamAwardVoList(
			List<ClientTeamAwardVo> clientTeamAwardVoList) {
		this.clientTeamAwardVoList = clientTeamAwardVoList;
	}

	public List<BpBwFileVo> getBpBwFileVoList() {
		return bpBwFileVoList;
	}

	public void setBpBwFileVoList(List<BpBwFileVo> bpBwFileVoList) {
		this.bpBwFileVoList = bpBwFileVoList;
	}
	
	
	
	
	

}
