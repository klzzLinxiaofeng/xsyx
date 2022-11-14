package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Charge;
/**
 * Charge
 * @author AutoCreate
 *
 */
public class ChargeVo extends Charge {
	private static final long serialVersionUID = 1L;

	private String schoolYear;

	private String schoolTerm;

	private String itemName;

	private String gradeName;

	private String teamName;

	private String studentName;
	//百分比（统计用）
	private String percent;

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getSchoolTerm() {
		return schoolTerm;
	}

	public void setSchoolTerm(String schoolTerm) {
		this.schoolTerm = schoolTerm;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}