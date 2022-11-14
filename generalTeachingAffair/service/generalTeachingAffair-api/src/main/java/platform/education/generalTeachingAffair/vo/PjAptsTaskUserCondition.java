package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.PjAptsTaskUser;

/**
 * PjAptsTaskUser
 *
 * @author AutoCreate
 */
public class PjAptsTaskUserCondition extends PjAptsTaskUser {
    private static final long serialVersionUID = 1L;

    private String teacherName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    //教师姓名
    private String name;

    //科目code
    private String subjectCode;

    //周期开始时间
    private String weekStartDate;

    private String weekFinishDate;

    public String getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekFinishDate() {
        return weekFinishDate;
    }

    public void setWeekFinishDate(String weekFinishDate) {
        this.weekFinishDate = weekFinishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
}