package platform.education.oa.vo;
import platform.education.oa.model.Repair;
/**
 * Repair
 * @author AutoCreate
 *
 */
public class RepairVo extends Repair {
	private static final long serialVersionUID = 1L;
	private String appName;
	private String fileUuid;
	private String thumbUrl;
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getFileUuid() {
		return fileUuid;
	}
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
}