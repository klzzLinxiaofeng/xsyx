package platform.education.commonResource.web.resource.vo;

/**
 *
 * @author 罗志明
 */
public class MyResourceVo {

    private Object resEnt;
    private String thumbnailUrl;
    private Integer iconType;
	public Object getResEnt() {
		return resEnt;
	}
	public void setResEnt(Object resEnt) {
		this.resEnt = resEnt;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public Integer getIconType() {
		return iconType;
	}
	public void setIconType(Integer iconType) {
		this.iconType = iconType;
	}
}
