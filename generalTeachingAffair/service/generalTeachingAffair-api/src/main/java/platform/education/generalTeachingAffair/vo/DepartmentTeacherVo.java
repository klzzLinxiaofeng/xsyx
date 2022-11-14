package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.DepartmentTeacher;

public class DepartmentTeacherVo extends DepartmentTeacher {
	private static final long serialVersionUID = 1L;
	/**
	 * pj_person的id
	 */
	private Integer persionId;
	/**
	 * 照片的id
	 */
	private String photoUuid;
	/**
	 * 照片的链接
	 */
	private String photoSrc;
	
	private String departmentIds;
	
	private Integer teacherUserId;

	/**
	 * 部门名称，以逗号隔开
	 */
	private String departmentNames;

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}

	public Integer getPersionId() {
		return persionId;
	}

	public void setPersionId(Integer persionId) {
		this.persionId = persionId;
	}

	public String getPhotoUuid() {
		return photoUuid;
	}

	public void setPhotoUuid(String photoUuid) {
		this.photoUuid = photoUuid;
	}

	public String getPhotoSrc() {
		return photoSrc;
	}

	public void setPhotoSrc(String photoSrc) {
		this.photoSrc = photoSrc;
	}

	public Integer getTeacherUserId() {
		return teacherUserId;
	}

	public void setTeacherUserId(Integer teacherUserId) {
		this.teacherUserId = teacherUserId;
	}

	public String getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(String departmentNames) {
		this.departmentNames = departmentNames;
	}
}
