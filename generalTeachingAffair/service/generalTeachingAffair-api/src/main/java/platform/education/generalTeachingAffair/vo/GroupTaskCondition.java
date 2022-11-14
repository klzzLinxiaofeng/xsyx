package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.GroupTask;
/**
 * GroupTask
 * @author AutoCreate
 *
 */
public class GroupTaskCondition extends GroupTask {
	private static final long serialVersionUID = 1L;

	private Boolean isScored;

	public Boolean getIsScored() {
		return this.isScored;
	}

	public void setIsScored(Boolean isScored) {
		this.isScored = isScored;
	}
}