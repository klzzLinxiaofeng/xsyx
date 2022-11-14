package platform.szxyzxx.aesthetic.pojo;

import java.util.Date;

public class ErWerMa {
    private Integer id;
    private String url;
    private Date createTime;
    private Integer iDelete;
    private Integer studentId;
    private Integer teamId;
    private Integer gradeId;
    private String teamName;
    private String studnetName;
    private String schoolYear;
    private String xq;
    private String uuid;

    @Override
    public String toString() {
        return "ErWerMa{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", iDelete=" + iDelete +
                ", studentId=" + studentId +
                ", teamId=" + teamId +
                ", gradeId=" + gradeId +
                ", teamName='" + teamName + '\'' +
                ", studnetName='" + studnetName + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                ", xq='" + xq + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStudnetName() {
        return studnetName;
    }

    public void setStudnetName(String studnetName) {
        this.studnetName = studnetName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getiDelete() {
        return iDelete;
    }

    public void setiDelete(Integer iDelete) {
        this.iDelete = iDelete;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
