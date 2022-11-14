package platform.education.generalTeachingAffair.vo;
import java.util.List;

/**
 * StudentScoreBean
 * @author wang
 *
 */
public class StudentScoreBean{

	private List<StudentScoreVo> studentScoreList;//学生成绩list
	private Integer examTeamSubjectId;//考务id
	private Integer rateType;//评分类型
	private String subjectCode;
	/**
	 * 方便统计学生成绩字段
	 */
	private String schoolYear;//学年
	private String termCode;//学期
	private  Integer gradeId;//年级
	private  Integer teamId;//班级
	private String examType;//考试类型
	private String examName;//考试名称
	
	
	
	public String getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public Integer getExamTeamSubjectId() {
		return examTeamSubjectId;
	}

	public void setExamTeamSubjectId(Integer examTeamSubjectId) {
		this.examTeamSubjectId = examTeamSubjectId;
	}

	public List<StudentScoreVo> getStudentScoreList() {
		return studentScoreList;
	}

	public void setStudentScoreList(List<StudentScoreVo> studentScoreList) {
		this.studentScoreList = studentScoreList;
	}
	
	
}