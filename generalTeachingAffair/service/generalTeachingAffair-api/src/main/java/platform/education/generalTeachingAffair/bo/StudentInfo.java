package platform.education.generalTeachingAffair.bo;

import java.util.List;

public class StudentInfo {
    private Integer studentCode;
    private String studentName;
    private String cardCode;
    private Integer gradeNumber;
    private Integer teamNumber;
    private List<StudentParentInfo> parentList;
    public Integer getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Integer studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public List<StudentParentInfo> getParentList() {
        return parentList;
    }

    public void setParentList(List<StudentParentInfo> parentList) {
        this.parentList = parentList;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public void setGradeNumber(Integer gradeNumber) {
        this.gradeNumber = gradeNumber;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(Integer teamNumber) {
        this.teamNumber = teamNumber;
    }
}
