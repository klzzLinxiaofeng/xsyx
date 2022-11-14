package platform.szxyzxx.resultsStatistical.pojo;
/*
* 班级学科成绩分析
*/
public class AnalysisClassSubject {
    /*
    * 考试名称
    */
    private String testName;
    /*
     * 班级人数
     */
    private Integer classNumber;
    /*
     * 参考人数
     */
    private Integer referenceNumber;
    /*
     * 总分
     */
    private Double totalScore;
    /*
     * 平均分
     */
    private Double average;
    /*
     * 合格人数
     */
    private Integer qualifiedNumber;
    /*
     * 合格率
     */
    private Double percent;
    /*
    * 不合格人数
    */
    private Integer unqualifiedNumber;
    /*
     *不合格率
     */
    private Double unpercent;
    /*
    * 优秀人数
    */
    private Integer eugenics;
    /*
     *优生率
     */
    private Double healthy;
    /*
     * 90分以上人数
     */
    private Integer ninetyNumber;
    /*
     * 90分到81人数
     */
    private Integer eightyNumber;
    /*
     * 80分到71分人数
     */
    private Integer seventyNumber;
    /*
     * 70分到60分人数
     */
    private Integer sixtyNumber;
    /*
     * 59分到50分人数
     */
    private Integer fiftyNumber;
    /*
    * 老师名
    */
    private String teatherName;

    @Override
    public String toString() {
        return "AnalysisClassSubject{" +
                "testName='" + testName + '\'' +
                ", classNumber=" + classNumber +
                ", referenceNumber=" + referenceNumber +
                ", totalScore=" + totalScore +
                ", average=" + average +
                ", qualifiedNumber=" + qualifiedNumber +
                ", percent=" + percent +
                ", unqualifiedNumber=" + unqualifiedNumber +
                ", unpercent=" + unpercent +
                ", eugenics=" + eugenics +
                ", healthy=" + healthy +
                ", ninetyNumber=" + ninetyNumber +
                ", eightyNumber=" + eightyNumber +
                ", seventyNumber=" + seventyNumber +
                ", sixtyNumber=" + sixtyNumber +
                ", fiftyNumber=" + fiftyNumber +
                ", teatherName='" + teatherName + '\'' +
                '}';
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public Integer getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(Integer referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getQualifiedNumber() {
        return qualifiedNumber;
    }

    public void setQualifiedNumber(Integer qualifiedNumber) {
        this.qualifiedNumber = qualifiedNumber;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getUnqualifiedNumber() {
        return unqualifiedNumber;
    }

    public void setUnqualifiedNumber(Integer unqualifiedNumber) {
        this.unqualifiedNumber = unqualifiedNumber;
    }

    public Double getUnpercent() {
        return unpercent;
    }

    public void setUnpercent(Double unpercent) {
        this.unpercent = unpercent;
    }

    public Integer getEugenics() {
        return eugenics;
    }

    public void setEugenics(Integer eugenics) {
        this.eugenics = eugenics;
    }

    public Double getHealthy() {
        return healthy;
    }

    public void setHealthy(Double healthy) {
        this.healthy = healthy;
    }

    public Integer getNinetyNumber() {
        return ninetyNumber;
    }

    public void setNinetyNumber(Integer ninetyNumber) {
        this.ninetyNumber = ninetyNumber;
    }

    public Integer getEightyNumber() {
        return eightyNumber;
    }

    public void setEightyNumber(Integer eightyNumber) {
        this.eightyNumber = eightyNumber;
    }

    public Integer getSeventyNumber() {
        return seventyNumber;
    }

    public void setSeventyNumber(Integer seventyNumber) {
        this.seventyNumber = seventyNumber;
    }

    public Integer getSixtyNumber() {
        return sixtyNumber;
    }

    public void setSixtyNumber(Integer sixtyNumber) {
        this.sixtyNumber = sixtyNumber;
    }

    public Integer getFiftyNumber() {
        return fiftyNumber;
    }

    public void setFiftyNumber(Integer fiftyNumber) {
        this.fiftyNumber = fiftyNumber;
    }

    public String getTeatherName() {
        return teatherName;
    }

    public void setTeatherName(String teatherName) {
        this.teatherName = teatherName;
    }

    public AnalysisClassSubject(){}
    public AnalysisClassSubject(String testName, Integer classNumber, Integer referenceNumber, Double totalScore, Double average, Integer qualifiedNumber, Double percent, Integer unqualifiedNumber, Double unpercent, Integer eugenics, Double healthy, Integer ninetyNumber, Integer eightyNumber, Integer seventyNumber, Integer sixtyNumber, Integer fiftyNumber, String teatherName) {
        this.testName = testName;
        this.classNumber = classNumber;
        this.referenceNumber = referenceNumber;
        this.totalScore = totalScore;
        this.average = average;
        this.qualifiedNumber = qualifiedNumber;
        this.percent = percent;
        this.unqualifiedNumber = unqualifiedNumber;
        this.unpercent = unpercent;
        this.eugenics = eugenics;
        this.healthy = healthy;
        this.ninetyNumber = ninetyNumber;
        this.eightyNumber = eightyNumber;
        this.seventyNumber = seventyNumber;
        this.sixtyNumber = sixtyNumber;
        this.fiftyNumber = fiftyNumber;
        this.teatherName = teatherName;
    }
}
