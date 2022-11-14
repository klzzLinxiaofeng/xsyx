package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/3.
 */
public class ThreeRatiosVo implements Serializable{
    //考试ID
    private Integer examId;
    //班级ID
    private Integer teamId;
    //年级ID
    private Integer gradeId;
    //高分率
    private Float hight;
    //良好率
    private Float low;
    //及格率
    private Float pass;
    //不及格率
    private Float noPass;

    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Float getHight() {
        return hight;
    }

    public void setHight(Float hight) {
        this.hight = hight;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Float getPass() {
        return pass;
    }

    public void setPass(Float pass) {
        this.pass = pass;
    }

    public Float getNoPass() {
        return noPass;
    }

    public void setNoPass(Float noPass) {
        this.noPass = noPass;
    }
}
