package platform.education.generalTeachingAffair.vo;

import java.util.Date;

/**
 *  @author: yhc
 *  @Date: 2021/5/8 15:58
 *  @Description:
 */
public class WeekStuVo {
    /**
     * id
     */
    private Integer id;

    /**
     * 对应 周明星学生对应的学年学期id
     */
    private Integer weekId;

    /**
     * 班级id
     */
    private Integer teamId;
    private Integer gradeId;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 学生评分
     */
    private Integer score;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

