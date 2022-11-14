package platform.szxyzxx.indicators.pojo;


import java.util.Date;
import java.util.List;

public class IndicatorsStudent {
    private  Integer id;
    private  Integer studentId;
    private String stuName;
    private  Integer baogaoId;
    private  Integer indicatorsId;
    private  String indicatorsName;
    private List<IndicatorsPojo> indicatorsPojo;
    private Integer score;
    private String danwei;
    private  String healthReport;
    private  String cervixReport;
    private  String yiWu;
    private Date createTime;
    private Date modieTime;
    private  String schoolYear;
    /*
    * 请假天数
    */
    private Integer tianshu;
    /*
     * 请假次数
     */
    private Integer jiaDay;
    private List<IndicatorsStudent> indicatorsStudents;
    public IndicatorsStudent(){}

    public List<IndicatorsStudent> getIndicatorsStudents() {
        return indicatorsStudents;
    }

    public void setIndicatorsStudents(List<IndicatorsStudent> indicatorsStudents) {
        this.indicatorsStudents = indicatorsStudents;
    }

    public IndicatorsStudent(Integer id, Integer studentId, String stuName, Integer baogaoId, Integer indicatorsId, String indicatorsName, List<IndicatorsPojo> indicatorsPojo, Integer score, String danwei, String healthReport, String cervixReport, String yiWu, Date createTime, Date modieTime, String schoolYear, Integer tianshu, Integer jiaDay, List<IndicatorsStudent> indicatorsStudents) {
        this.id = id;
        this.studentId = studentId;
        this.stuName = stuName;
        this.baogaoId = baogaoId;
        this.indicatorsId = indicatorsId;
        this.indicatorsName = indicatorsName;
        this.indicatorsPojo = indicatorsPojo;
        this.score = score;
        this.danwei = danwei;
        this.healthReport = healthReport;
        this.cervixReport = cervixReport;
        this.yiWu = yiWu;
        this.createTime = createTime;
        this.modieTime = modieTime;
        this.schoolYear = schoolYear;
        this.tianshu = tianshu;
        this.jiaDay = jiaDay;
        this.indicatorsStudents = indicatorsStudents;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public String getIndicatorsName() {
        return indicatorsName;
    }

    public void setIndicatorsName(String indicatorsName) {
        this.indicatorsName = indicatorsName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getBaogaoId() {
        return baogaoId;
    }

    public void setBaogaoId(Integer baogaoId) {
        this.baogaoId = baogaoId;
    }

    public Integer getIndicatorsId() {
        return indicatorsId;
    }

    public void setIndicatorsId(Integer indicatorsId) {
        this.indicatorsId = indicatorsId;
    }

    public List<IndicatorsPojo> getIndicatorsPojo() {
        return indicatorsPojo;
    }

    public void setIndicatorsPojo(List<IndicatorsPojo> indicatorsPojo) {
        this.indicatorsPojo = indicatorsPojo;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getHealthReport() {
        return healthReport;
    }

    public void setHealthReport(String healthReport) {
        this.healthReport = healthReport;
    }

    public String getCervixReport() {
        return cervixReport;
    }

    public void setCervixReport(String cervixReport) {
        this.cervixReport = cervixReport;
    }

    public String getYiWu() {
        return yiWu;
    }

    public void setYiWu(String yiWu) {
        this.yiWu = yiWu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModieTime() {
        return modieTime;
    }

    public void setModieTime(Date modieTime) {
        this.modieTime = modieTime;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getTianshu() {
        return tianshu;
    }

    public void setTianshu(Integer tianshu) {
        this.tianshu = tianshu;
    }

    public Integer getJiaDay() {
        return jiaDay;
    }

    public void setJiaDay(Integer jiaDay) {
        this.jiaDay = jiaDay;
    }
}
