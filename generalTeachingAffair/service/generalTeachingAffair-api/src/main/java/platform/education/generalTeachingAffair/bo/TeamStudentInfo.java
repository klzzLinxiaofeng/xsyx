package platform.education.generalTeachingAffair.bo;

import java.util.List;

public class TeamStudentInfo {
    private Integer teamId;
    private List<StudentInfo> stuList;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<StudentInfo> getStuList() {
        return stuList;
    }

    public void setStuList(List<StudentInfo> stuList) {
        this.stuList = stuList;
    }
}
