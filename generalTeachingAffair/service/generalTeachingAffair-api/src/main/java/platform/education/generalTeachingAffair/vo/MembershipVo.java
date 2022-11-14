package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Membership;
/**
 * Membership
 * @author AutoCreate
 *
 */
public class MembershipVo extends Membership {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 关联学校数量
	 */
	private Integer connectSchoolCount ;
	
	/**
	 * 关联的所有学校
	 */
	private String connectSchoolName;
	

	public Integer getConnectSchoolCount() {
		return connectSchoolCount;
	}

	public void setConnectSchoolCount(Integer connectSchoolCount) {
		this.connectSchoolCount = connectSchoolCount;
	}

	public String getConnectSchoolName() {
		return connectSchoolName;
	}

	public void setConnectSchoolName(String connectSchoolName) {
		this.connectSchoolName = connectSchoolName;
	}
	
	
	
}