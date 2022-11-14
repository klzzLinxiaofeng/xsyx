package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.SubjectGrade;

public class SubjectGradeVo extends SubjectGrade{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 学段名称
	 */
	private String stageName;
	/**
	 * 年级名称
	 */
	private String gradeName;
	
	private Long count;
	
	public String getStageName() {
		return stageName;
	}
	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	

}
