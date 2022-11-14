package platform.szxyzxx.literacy.pojo;

public class SubjectScorePojo {

    private String xn;
    private String xq;
    private Integer gradeId;
    private Integer subjectId;
    private String subjectCord;
    private Integer teamId;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectCord() {
        return subjectCord;
    }

    public void setSubjectCord(String subjectCord) {
        this.subjectCord = subjectCord;
    }

    @Override
    public String toString() {
        return "SubjectScorePojo{" +
                "xn='" + xn + '\'' +
                ", xq='" + xq + '\'' +
                ", gradeId=" + gradeId +
                ", subjectId=" + subjectId +
                ", subjectCord='" + subjectCord + '\'' +
                ", teamId=" + teamId +
                '}';
    }
}
