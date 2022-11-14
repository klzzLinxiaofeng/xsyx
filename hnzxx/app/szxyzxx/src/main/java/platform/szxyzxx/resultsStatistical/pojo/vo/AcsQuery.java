package platform.szxyzxx.resultsStatistical.pojo.vo;
/*
* 班级学科成绩分析 条件类
*/
public class AcsQuery {
    /*学年*/
    private String xn;
    /*学期*/
    private String xq;
    /*科目code*/
    private String subjectCode;
    /*班级id*/
    private Integer teamId;
    /*年级id*/
    private Integer gradeId;

    @Override
    public String toString() {
        return "AcsQuery{" +
                "xn='" + xn + '\'' +
                ", xq='" + xq + '\'' +
                ", subjectCode='" + subjectCode + '\'' +
                ", teamId=" + teamId +
                ", gradeId=" + gradeId +
                '}';
    }

    public AcsQuery(){}
    public AcsQuery(String xn, String xq, String subjectCode, Integer teamId, Integer gradeId) {
        this.xn = xn;
        this.xq = xq;
        this.subjectCode = subjectCode;
        this.teamId = teamId;
        this.gradeId = gradeId;
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
}
