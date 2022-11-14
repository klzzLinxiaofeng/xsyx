package platform.education.generalTeachingAffair.vo;
import java.util.List;

import platform.education.generalTeachingAffair.model.PjGroupStudent;

/**
 * PjGroupStudent
 * @author AutoCreate
 *
 */
public class PjGroupStudentVo extends PjGroupStudent {
	private static final long serialVersionUID = 1L;

	private String studentName;

	private String imgUrl;

	private List<PjGroupStudentsVo> student;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<PjGroupStudentsVo> getStudent() {
		return student;
	}

	public void setStudent(List<PjGroupStudentsVo> student) {
		this.student = student;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}