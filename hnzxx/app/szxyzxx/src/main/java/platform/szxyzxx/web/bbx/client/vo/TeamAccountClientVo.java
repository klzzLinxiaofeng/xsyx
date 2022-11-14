package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

public class TeamAccountClientVo<T> {

	private static final long serialVersionUID = 1L;
	
/*	*//**
	 * 角色code值
	 *//*
	private String  currentRoleCode;*/
	
	/**
	 * 对象
	 */
	private List<T> glist;
	
	public TeamAccountClientVo() {
		
	}
	
	public TeamAccountClientVo( List<T> glist) {
		this.glist = glist;
	}

	public List<T> getGlist() {
		return glist;
	}

	public void setGlist(List<T> glist) {
		this.glist = glist;
	}

	

	
	
	
	
}
