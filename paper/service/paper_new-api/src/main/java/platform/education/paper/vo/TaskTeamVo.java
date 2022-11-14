package platform.education.paper.vo;

import platform.education.generalTeachingAffair.vo.ExamTeamScoreVo;
import platform.education.paper.model.TaskTeam;

import java.util.List;

/**
 * TaskTeam
 *
 * @author AutoCreate
 */
public class TaskTeamVo extends TaskTeam implements Comparable<TaskTeamVo> {
    private static final long serialVersionUID = 1L;

    private String teamName;
    private String finishRate;
    private String totalOfCompleted;
    private String averageScore;
    private List<TaskUserVo> taskUserVoList;
    private List<ExamTeamScoreVo> examTeamScoreVoList;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(String finishRate) {
        this.finishRate = finishRate;
    }

    public String getTotalOfCompleted() {
        return totalOfCompleted;
    }

    public void setTotalOfCompleted(String totalOfCompleted) {
        this.totalOfCompleted = totalOfCompleted;
    }

    public String getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(String averageScore) {
        this.averageScore = averageScore;
    }

    public List<TaskUserVo> getTaskUserVoList() {
        return taskUserVoList;
    }

    public void setTaskUserVoList(List<TaskUserVo> taskUserVoList) {
        this.taskUserVoList = taskUserVoList;
    }

    public List<ExamTeamScoreVo> getExamTeamScoreVoList() {
        return examTeamScoreVoList;
    }

    public void setExamTeamScoreVoList(List<ExamTeamScoreVo> examTeamScoreVoList) {
        this.examTeamScoreVoList = examTeamScoreVoList;
    }

    @Override
    public int compareTo(TaskTeamVo taskTeamVo) {
        float a = Float.parseFloat(taskTeamVo.averageScore);
        float b = Float.parseFloat(this.averageScore);
        return (a - b) > 0 ? 1 : ((a - b) < 0 ? -1 : 0);
    }
}