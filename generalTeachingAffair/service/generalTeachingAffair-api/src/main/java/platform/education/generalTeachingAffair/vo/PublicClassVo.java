package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.PublicClass;
/**
 * PublicClass
 * @author AutoCreate
 *
 */
public class PublicClassVo extends PublicClass {
	private static final long serialVersionUID = 1L;
	
	//教师名称
	private String teacherName;
	
	//是否可以报名  "0"--不可以报名   "1"--可以报名
	private String isEnroll;
	
	//报名状态
	private String enrollState;

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getIsEnroll() {
		return isEnroll;
	}

	public void setIsEnroll(String isEnroll) {
		this.isEnroll = isEnroll;
	}

	public String getEnrollState() {
		return enrollState;
	}

	public void setEnrollState(String enrollState) {
		this.enrollState = enrollState;
	}
	
}