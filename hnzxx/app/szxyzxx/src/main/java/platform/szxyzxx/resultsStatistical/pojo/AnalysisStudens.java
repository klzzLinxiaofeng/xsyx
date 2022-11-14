package platform.szxyzxx.resultsStatistical.pojo;

import java.util.Date;

/*
 *学生成绩分析
 */
public class AnalysisStudens {
    private  String xn;
    private  String xq;
    /*
    * 科目编码
    */
    private String subjectCode;
    private Integer teamId;
    private  Integer gradeId;
    private Date createDate;
    /*
    * 学生id
    */
    private Integer studentId;


    /*
     * 学生姓名
     */
    private String studentName;
    /*
     * 科目名称
     */
    private String subjectName;
    /*
     * 考试名称
     */
    private String examName;
    /*
     * 分数
     */
    private Double score;
    /*
     * 排名
     */
    private Integer rank;
    /*
     * 排名波动
     */
    private Integer ranking;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public AnalysisStudens() {
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "AnalysisStudens{" +
                "xn='" + xn + '\'' +
                ", xq='" + xq + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", teamId=" + teamId +
                ", gradeId=" + gradeId +
                ", studentName='" + studentName + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", examName='" + examName + '\'' +
                ", score=" + score +
                ", rank=" + rank +
                ", ranking=" + ranking +
                '}';
    }

    public AnalysisStudens(String xn, String xq, String subjectCode, Integer teamId, Integer gradeId, Date createDate, Integer studentId, String studentName, String subjectName, String examName, Double score, Integer rank, Integer ranking) {
        this.xn = xn;
        this.xq = xq;
        this.subjectCode = subjectCode;
        this.teamId = teamId;
        this.gradeId = gradeId;
        this.createDate = createDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.examName = examName;
        this.score = score;
        this.rank = rank;
        this.ranking = ranking;
    }
}
