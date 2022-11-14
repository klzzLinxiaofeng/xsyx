package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.List;

public class BpPraiseClientVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long createDate;
	
	private List<PraiseClientVo> praiseClientList;

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public List<PraiseClientVo> getPraiseClientList() {
		return praiseClientList;
	}

	public void setPraiseClientList(List<PraiseClientVo> praiseClientList) {
		this.praiseClientList = praiseClientList;
	}
	
	
}
