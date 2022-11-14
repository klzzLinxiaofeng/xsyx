package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

/**
 * 功能描述：学校学年（pj_school_year）
 * @author 
 * @Date 2016-01-13
 */
public class ExtSchoolYearVo {
	
	/**
	 * 学年值
	 */
	private String schoolYear;
	
	/**
	 * 学年名称
	 */
	private String name;
	
	/**
	 * 是否是当前学年标志
	 */
	Boolean isCurrent;
	
	/**
	 * 该学年下的所有学期集合
	 */
	private List<ExtSchoolTermVo> terms;

	/*================ Added on 2017-09-01 BEGIN ======================*/
	/**
	 * 该学年下的所有年级集合
	 */
	private List<ExtGradeVo> grades;

	/*================ Added on 2017-09-01 END ======================*/

	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ExtSchoolTermVo> getTerms() {
		return terms;
	}

	public void setTerms(List<ExtSchoolTermVo> terms) {
		this.terms = terms;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public List<ExtGradeVo> getGrades() {
		return grades;
	}

	public void setGrades(List<ExtGradeVo> grades) {
		this.grades = grades;
	}
}
