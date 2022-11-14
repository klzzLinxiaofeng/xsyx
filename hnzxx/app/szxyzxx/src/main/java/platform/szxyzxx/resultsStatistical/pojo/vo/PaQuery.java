package platform.szxyzxx.resultsStatistical.pojo.vo;

public class PaQuery {
    /**
     * 学年
     */

    private String xn;
    /**
     * 学期
     */
    private String xq;
    /**
     * 考试类型
     */
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public PaQuery(String xn, String xq, String testName) {
        this.xn = xn;
        this.xq = xq;
        this.testName = testName;
    }
    public PaQuery(){}
}
