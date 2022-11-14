package platform.education.generalTeachingAffair.vo;
import java.util.ArrayList;
import java.util.List;

import platform.education.generalTeachingAffair.model.PjTeacherSalary;
/**
 * PjTeacherSalary
 * @author AutoCreate
 *
 */
public class PjTeacherSalaryVo extends PjTeacherSalary {
	private static final long serialVersionUID = 1L;
	
	private List<SalaryFieldValue> valueList = new ArrayList<SalaryFieldValue>();

	public List<SalaryFieldValue> getValueList() {
		return valueList;
	}

	
	
}