package platform.education.paper.vo;
import platform.education.paper.model.PaPaper;
/**
 * PaPaper
 * @author AutoCreate
 *
 */
public class PaPaperVo extends PaPaper {
	private static final long serialVersionUID = 1L;
	
	private boolean isFav;
	
	private String catalogCode;
	
	private boolean hasTask;
	
	public boolean isFav() {
		return isFav;
	}

	public void setFav(boolean isFav) {
		this.isFav = isFav;
	}

	private String subjectCode;
	
	private String stageCode;
	
	private String subjectName;
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	private String stageName;

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getCatalogCode() {
		return catalogCode;
	}

	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public boolean isHasTask() {
		return hasTask;
	}

	public void setHasTask(boolean hasTask) {
		this.hasTask = hasTask;
	}

}