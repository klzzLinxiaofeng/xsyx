package platform.szxyzxx.resultsStatistical.pojo.vo;


import java.util.Date;

/*
*学生成绩分析 条件类
*/
public class AsQuery {
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
    /*考试名称*/
    private String examName;
    /*创建时间*/
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
    public AsQuery(){}

    public AsQuery(String xn, String xq, String subjectCode, Integer teamId, Integer gradeId, String examName, Date createDate) {
        this.xn = xn;
        this.xq = xq;
        this.subjectCode = subjectCode;
        this.teamId = teamId;
        this.gradeId = gradeId;
        this.examName = examName;
        this.createDate = createDate;
    }
}
