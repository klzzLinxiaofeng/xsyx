package platform.education.oa.vo;

import platform.education.oa.model.AdjustClass;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-28 10:01
 */
public class AdjustClassVo extends AdjustClass {

    private static final long serialVersionUID = 1L;

    /**
     * 调课那天是星期几
     */
    private String applyWeek;

    /**
     * 被调课那天是星期几
     */
    private String setWeek;

    private Object applyLessonOne;

    private Object applyLessonTwo;

    private Object setLessonOne;

    private Object setLessonTwo;

    /**
     * 某个人是否有权限审批这份调课
     */
    private Boolean approveFlag;

    public String getApplyWeek() {
        return applyWeek;
    }

    public void setApplyWeek(String applyWeek) {
        this.applyWeek = applyWeek;
    }

    public String getSetWeek() {
        return setWeek;
    }

    public void setSetWeek(String setWeek) {
        this.setWeek = setWeek;
    }

    public Object getApplyLessonOne() {
        return applyLessonOne;
    }

    public void setApplyLessonOne(Object applyLessonOne) {
        this.applyLessonOne = applyLessonOne;
    }

    public Object getApplyLessonTwo() {
        return applyLessonTwo;
    }

    public void setApplyLessonTwo(Object applyLessonTwo) {
        this.applyLessonTwo = applyLessonTwo;
    }

    public Object getSetLessonOne() {
        return setLessonOne;
    }

    public void setSetLessonOne(Object setLessonOne) {
        this.setLessonOne = setLessonOne;
    }

    public Object getSetLessonTwo() {
        return setLessonTwo;
    }

    public void setSetLessonTwo(Object setLessonTwo) {
        this.setLessonTwo = setLessonTwo;
    }

    public Boolean getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(Boolean approveFlag) {
        this.approveFlag = approveFlag;
    }
}
