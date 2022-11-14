package platform.szxyzxx.ishangkelilu.pojo;

import java.util.Date;

public class StudyHabits {
    private Integer id;
    private Integer studentId;
    private String  studentName;
    private Integer score;
    private Integer dianpingren;
    private Date createTime;
    private Integer stats;
    private Integer leixing;
    private String leixingName;
    private Integer  teacherId;
    private String  teacherName;
    private Integer  teamId;
    private String  teamName;
    private Integer isDelete;
    private Integer subjectId;
    private String subjectName;
    private String pingyu;

    public String getLeixingName() {
        return leixingName;
    }

    public void setLeixingName(String leixingName) {
        this.leixingName = leixingName;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getLeixing() {
        return leixing;
    }

    public void setLeixing(Integer leixing) {
        this.leixing = leixing;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getDianpingren() {
        return dianpingren;
    }

    public void setDianpingren(Integer dianpingren) {
        this.dianpingren = dianpingren;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }
}
