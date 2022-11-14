package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.SchoolTerm;
/**
 * SchoolTerm
 * @author AutoCreate
 *
 */
public class SchoolTermCondition extends SchoolTerm {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 关联pj_school_year.name
	 */
	private String schoolYearName;

	/**
	 * 获取pj_school_year.name
	 * @return
	 */
	public String getSchoolYearName() {
		return schoolYearName;
	}

	/**
	 * 设置pj_school_year.name
	 * @param schoolYearName
	 */
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	
}