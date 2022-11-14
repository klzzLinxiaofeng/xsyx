package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.SchoolYear;
/**
 * SchoolYear
 * @author AutoCreate
 *
 */
public class SchoolYearVo extends SchoolYear {
	private static final long serialVersionUID = 1L;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}