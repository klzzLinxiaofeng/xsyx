package platform.szxyzxx.resultsStatistical.pojo;

/*
 * 年级学科成绩统计  组合查询
 * */
public class GradeSubjectScores {
    /*
    *班级名称
    */
    private String className;
    /*
    *班级人数
    */
    private Integer classNumber;
    /*
    *参考人数
    */
    private Integer referenceNumber;
    /*
    *总分
    */
    private Double totalScore;
    /*
     *平均分
     */
    private Double average;
    /*
     *全镇平均分
     */
    private Double paAverage;
    /*
     *合格人数
     */
    private Integer qualifiedNumber;
    /*
    * 合格率
    */
    private Double percent;
    /*
     *全镇合格率
     */
    private Double paPercent;
    /*不合格人数*/
    private Integer unqualifiedNumber;
    /*
    *不合格率
    */
    private Double unpercent;
    /*优秀人数*/
    private Integer eugenics;
    /*
     *优生率
     */
    private Double healthy;
    private Double paHealthy;
    /*老师名*/
    private String teatherName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public Double getPaAverage() {
        return paAverage;
    }

    public void setPaAverage(Double paAverage) {
        this.paAverage = paAverage;
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

    public Double getPaHealthy() {
        return paHealthy;
    }

    public void setPaHealthy(Double paHealthy) {
        this.paHealthy = paHealthy;
    }

    public String getTeatherName() {
        return teatherName;
    }

    public void setTeatherName(String teatherName) {
        this.teatherName = teatherName;
    }

    public GradeSubjectScores(){}

    public GradeSubjectScores(String className, Integer classNumber, Integer referenceNumber, Double totalScore, Double average, Double paAverage, Integer qualifiedNumber, Double percent, Double paPercent, Integer unqualifiedNumber, Double unpercent, Integer eugenics, Double healthy, Double paHealthy, String teatherName) {
        this.className = className;
        this.classNumber = classNumber;
        this.referenceNumber = referenceNumber;
        this.totalScore = totalScore;
        this.average = average;
        this.paAverage = paAverage;
        this.qualifiedNumber = qualifiedNumber;
        this.percent = percent;
        this.paPercent = paPercent;
        this.unqualifiedNumber = unqualifiedNumber;
        this.unpercent = unpercent;
        this.eugenics = eugenics;
        this.healthy = healthy;
        this.paHealthy = paHealthy;
        this.teatherName = teatherName;
    }

    public Double getPaPercent() {
        return paPercent;
    }

    public void setPaPercent(Double paPercent) {
        this.paPercent = paPercent;
    }
}
