package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentScore;
/**
 * 学生成绩
 * @author AutoCreate
 *
 */
public class StudentScoreCondition extends StudentScore {
	
	private static final long serialVersionUID = 1L;
	
	private Integer schoolId;//学校
	//private String schoolYear;//学年
	//private Integer gradeId;//年级
	//private Integer teamId;//班级
	//private String subjectCode;//科目代码
	//private String termCode;//学期代码
	//private String examName;//考试名称
	//private String examType;//考试类别
	private Integer rateType;//评分类型，0===未定义，1===标准分
	//private Integer examTeamSubjectId;//考务id
	private String errorMessage;//错误信息
	private String termName;//学期名称
	private String schoolYearName;//学年
	private String examCodeFlag;
	private String subjectCodeFlag;
	private String examTypeFlag;
	private String canSave;
	public static String TABLECODE_EXTYPE = "JY-KSXS";
	public static String NEW_TABLECODE_EXTYPE = "NEW_JY-KSLX";

	public String getExamTypeFlag() {
		return examTypeFlag;
	}
	public void setExamTypeFlag(String examTypeFlag) {
		this.examTypeFlag = examTypeFlag;
	}
	public String getCanSave() {
		return canSave;
	}
	public void setCanSave(String canSave) {
		this.canSave = canSave;
	}
	public String getExamCodeFlag() {
		return examCodeFlag;
	}
	public void setExamCodeFlag(String examCodeFlag) {
		this.examCodeFlag = examCodeFlag;
	}
	public String getSubjectCodeFlag() {
		return subjectCodeFlag;
	}
	public void setSubjectCodeFlag(String subjectCodeFlag) {
		this.subjectCodeFlag = subjectCodeFlag;
	}
	public String getSchoolYearName() {
		return schoolYearName;
	}
	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public Integer getRateType() {
		return rateType;
	}
	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}

	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	
	
}