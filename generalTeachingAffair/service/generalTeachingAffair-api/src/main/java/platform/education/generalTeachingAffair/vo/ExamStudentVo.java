package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.ExamStudent;

/**
 * ExamStudent
 *
 * @author AutoCreate
 */
public class ExamStudentVo extends ExamStudent {
    private static final long serialVersionUID = 1L;

    private Boolean isFinished;
    private String sumScore;
    private Integer totalTime;
    private String timeStr; //秒数转时分秒
    //正确率
    private Float rightPercent;

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getSumScore() {
        return sumScore;
    }

    public void setSumScore(String sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public Float getRightPercent() {
        return rightPercent;
    }

    public void setRightPercent(Float rightPercent) {
        this.rightPercent = rightPercent;
    }
}