package platform.education.generalTeachingAffair.bo;


import platform.education.generalTeachingAffair.model.Team;

public class TeamBo extends Team {

    String gradeName;

    String stageCode;

    Integer gradeNumber;

    Integer schoolYear;

    Integer type;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Integer getShoolYear() {
        return schoolYear;
    }

    public void setShoolYear(Integer schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
