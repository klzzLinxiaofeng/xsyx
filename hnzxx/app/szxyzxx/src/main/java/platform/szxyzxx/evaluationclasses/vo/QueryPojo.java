package platform.szxyzxx.evaluationclasses.vo;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:56
 * @Version 1.0
 */
public class QueryPojo extends ClassesBehavior {
    private Integer gradeId;
    private Integer teamId;
    private String startTime;
    private String endTime;

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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
