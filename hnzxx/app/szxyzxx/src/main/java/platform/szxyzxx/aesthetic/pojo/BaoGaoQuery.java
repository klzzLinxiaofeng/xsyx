package platform.szxyzxx.aesthetic.pojo;

public class BaoGaoQuery {
    /*
    * 学年
    */
    private String  year;
    /*
     * 学期
     */
    private String  xn;
    /*
     * 年级
     */
    private Integer  gradeId;
    /*
     * 班级
     */
    private Integer  teamId;
    /*
     * 姓名
     */
    private String  studentName;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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
}
