package platform.szxyzxx.ishangkelilu.pojo;

public class ZuoWei {
    private Integer id;
    private Integer studentId;
    private String studentName;
    private Integer haoMa;
    private Integer teamId;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getHaoMa() {
        return haoMa;
    }

    public void setHaoMa(Integer haoMa) {
        this.haoMa = haoMa;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
    public ZuoWei(){}
    public ZuoWei(Integer id, Integer studentId, String studentName, Integer haoMa, Integer teamId) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.haoMa = haoMa;
        this.teamId = teamId;
    }
}

