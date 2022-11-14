package platform.szxyzxx.indicators.pojo;

public class StudentIndicaPojo {
    private  Integer indicatorsId;
    private  Integer studentId;
    private Integer score;
    private Integer isDelete;
    private String schoolYear;
    private Integer baogaoId;
    public StudentIndicaPojo(){}

    public StudentIndicaPojo(Integer indicatorsId, Integer studentId, Integer score, Integer isDelete, String schoolYear, Integer baogaoId) {
        this.indicatorsId = indicatorsId;
        this.studentId = studentId;
        this.score = score;
        this.isDelete = isDelete;
        this.schoolYear = schoolYear;
        this.baogaoId = baogaoId;
    }

    public Integer getIndicatorsId() {
        return indicatorsId;
    }

    public void setIndicatorsId(Integer indicatorsId) {
        this.indicatorsId = indicatorsId;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getBaogaoId() {
        return baogaoId;
    }

    public void setBaogaoId(Integer baogaoId) {
        this.baogaoId = baogaoId;
    }
}
