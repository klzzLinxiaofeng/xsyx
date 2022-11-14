package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Department;
/**
 * Department
 * @author AutoCreate
 *
 */
public class DepartmentVo extends Department {
	private static final long serialVersionUID = 1L;
	/**
	 * 部门内有照片的教师人数
	 */
	private Long hasPhotoTeacher;
	/**
	 * 部门内没有照片的人数
	 */
	private Long noPhotoTeacher;
	
	private String hasPhotoNames;
	
	private String noPhotoNames;
	
	//文印申请数
	private Integer applayIndiaCount;

	public Integer getApplayIndiaCount() {
		return applayIndiaCount;
	}

	public void setApplayIndiaCount(Integer applayIndiaCount) {
		this.applayIndiaCount = applayIndiaCount;
	}

	public Long getHasPhotoTeacher() {
		return hasPhotoTeacher;
	}

	public void setHasPhotoTeacher(Long hasPhotoTeacher) {
		this.hasPhotoTeacher = hasPhotoTeacher;
	}

	public Long getNoPhotoTeacher() {
		return noPhotoTeacher;
	}

	public void setNoPhotoTeacher(Long noPhotoTeacher) {
		this.noPhotoTeacher = noPhotoTeacher;
	}

	public String getHasPhotoNames() {
		return hasPhotoNames;
	}

	public void setHasPhotoNames(String hasPhotoNames) {
		this.hasPhotoNames = hasPhotoNames;
	}

	public String getNoPhotoNames() {
		return noPhotoNames;
	}

	public void setNoPhotoNames(String noPhotoNames) {
		this.noPhotoNames = noPhotoNames;
	}

}