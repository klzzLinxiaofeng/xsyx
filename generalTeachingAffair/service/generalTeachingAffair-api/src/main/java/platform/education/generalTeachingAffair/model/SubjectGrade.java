package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * 科目与年级关系
 * @author zhoujin
 *
 */

public class SubjectGrade implements Model<Integer>{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private Integer schoolId;
	
	private String gradeCode; 
	
	private String subjectName;
	
	private String subjectCode;
	
	private String stageCode;
	
	private boolean isDelete;
	
   private java.util.Date createDate;
   
   private java.util.Date modifyDate;
	

	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getGradeCode() {
		return gradeCode;
	}


	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}


	public String getSubjectName() {
		return subjectName;
	}


	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


	public String getSubjectCode() {
		return subjectCode;
	}


	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getStageCode() {
		return stageCode;
	}


	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
	public Integer getSchoolId() {
		return schoolId;
	}


	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	public boolean isDelete() {
		return isDelete;
	}


	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}


	public java.util.Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}


	public java.util.Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}


	@Override
	public Integer getKey() {
		return this.id;
	}

}
