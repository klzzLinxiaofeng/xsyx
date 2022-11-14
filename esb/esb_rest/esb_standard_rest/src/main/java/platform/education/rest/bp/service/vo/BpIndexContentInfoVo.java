package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.List;

public class BpIndexContentInfoVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<CircleMessageClientVo> circleMessageClientVoList;
	
	private List<CommonActivity> commonActivityList;
	
	private String signature;
	
	private String backgroundFileUrl;
	
	private String backgroundTemplate;

	public List<CircleMessageClientVo> getCircleMessageClientVoList() {
		return circleMessageClientVoList;
	}

	public void setCircleMessageClientVoList(
			List<CircleMessageClientVo> circleMessageClientVoList) {
		this.circleMessageClientVoList = circleMessageClientVoList;
	}

	public List<CommonActivity> getCommonActivityList() {
		return commonActivityList;
	}

	public void setCommonActivityList(List<CommonActivity> commonActivityList) {
		this.commonActivityList = commonActivityList;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getBackgroundFileUrl() {
		return backgroundFileUrl;
	}

	public void setBackgroundFileUrl(String backgroundFileUrl) {
		this.backgroundFileUrl = backgroundFileUrl;
	}

	public String getBackgroundTemplate() {
		return backgroundTemplate;
	}

	public void setBackgroundTemplate(String backgroundTemplate) {
		this.backgroundTemplate = backgroundTemplate;
	}
}
