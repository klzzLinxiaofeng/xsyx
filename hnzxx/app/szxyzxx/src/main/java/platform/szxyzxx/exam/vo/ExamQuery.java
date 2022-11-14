package platform.szxyzxx.exam.vo;


/*
* 条件类
*/
public class ExamQuery {
    /*
    * 学年
    */
    private String schoolYear;
    /*
     * 学期
     */
    private String schoolTrem;
    /*
     * 年级
     */
    private Integer gradeId;
    /*
     * 班级
     */
    private Integer teamId;
    /*
     * subjectCode
     */
    private String subjectCode;
    /*
     * 考试名称
     */
           private String examName;
    /*
     * 考试id
     */
    private Integer examId;
    /*
     * 考试type
     */
    private Integer examType;

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
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

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
