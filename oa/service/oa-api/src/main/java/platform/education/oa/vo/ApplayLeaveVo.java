package platform.education.oa.vo;

import platform.education.oa.model.ApplayLeave;

/**
 * ApplayLeave
 *
 * @author AutoCreate
 */
public class ApplayLeaveVo extends ApplayLeave {
    private static final long serialVersionUID = 1L;

    //部门名称
    private String propserDepartmentName;

    //请假的天数
    private Integer daySum;


    //请假的总天数
    private Integer allDaySum;


    //请假次数
    private Integer ciSum;

    //请假人数
    private Integer renshu;

    //某个人请假的
    private Integer oneSum;

    //	/某个请假申请的代课教师的姓名集合
    private String daikeName;

    //某个人是否有权限审批这份请假条
    private Boolean isApprove;

    public Boolean getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Boolean isApprove) {
        this.isApprove = isApprove;
    }


    public String getPropserDepartmentName() {
        return propserDepartmentName;
    }

    public void setPropserDepartmentName(String propserDepartmentName) {
        this.propserDepartmentName = propserDepartmentName;
    }

    public Integer getDaySum() {
        return daySum;
    }

    public void setDaySum(Integer daySum) {
        this.daySum = daySum;
    }


    public String getDaikeName() {
        return daikeName;
    }

    public void setDaikeName(String daikeName) {
        this.daikeName = daikeName;
    }

    public Integer getCiSum() {
        return ciSum;
    }

    public void setCiSum(Integer ciSum) {
        this.ciSum = ciSum;
    }

    public Integer getRenshu() {
        return renshu;
    }

    public void setRenshu(Integer renshu) {
        this.renshu = renshu;
    }


    public Integer getOneSum() {
        return oneSum;
    }

    public void setOneSum(Integer oneSum) {
        this.oneSum = oneSum;
    }

    public Integer getAllDaySum() {
        return allDaySum;
    }

    public void setAllDaySum(Integer allDaySum) {
        this.allDaySum = allDaySum;
    }

}