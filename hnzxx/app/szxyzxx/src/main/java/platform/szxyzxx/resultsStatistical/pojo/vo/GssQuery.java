package platform.szxyzxx.resultsStatistical.pojo.vo;

public class GssQuery {
    private String xn;
    private String xq;
    private String grade;
    private String subject;
    private String testName;

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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public GssQuery(){}

    public GssQuery(String xn, String xq, String grade, String subject, String testName) {
        this.xn = xn;
        this.xq = xq;
        this.grade = grade;
        this.subject = subject;
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "GssQuery{" +
                "xn='" + xn + '\'' +
                ", xq='" + xq + '\'' +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }
}
