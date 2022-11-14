package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.PjTeacherSalary;
/**
 * PjTeacherSalary
 * @author AutoCreate
 *
 */
public class PjTeacherSalaryCondition extends PjTeacherSalary {
	private static final long serialVersionUID = 1L;
	
	private Integer departmentId;

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	
	
}