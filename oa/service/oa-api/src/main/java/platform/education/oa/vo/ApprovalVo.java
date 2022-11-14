package platform.education.oa.vo;
import platform.education.oa.model.Approval;
/**
 * Approval
 * @author AutoCreate
 *
 */
public class ApprovalVo extends Approval {
	private static final long serialVersionUID = 1L;
	private String teacherName;
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
}