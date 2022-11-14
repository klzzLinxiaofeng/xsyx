package platform.education.paper.vo;

import platform.education.paper.model.TaskUser;

/**
 * TaskUser
 *
 * @author AutoCreate
 */
public class TaskUserVo extends TaskUser {
    private static final long serialVersionUID = 1L;

    private String personRate;
    private String studentName;
    private String finishStatus;
    private String finishTime;

    public String getPersonRate() {
        return personRate;
    }

    public void setPersonRate(String personRate) {
        this.personRate = personRate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(String finishStatus) {
        this.finishStatus = finishStatus;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "TaskUserVo{" +
                "personRate='" + personRate + '\'' +
                ", studentName='" + studentName + '\'' +
                ", finishStatus='" + finishStatus + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}