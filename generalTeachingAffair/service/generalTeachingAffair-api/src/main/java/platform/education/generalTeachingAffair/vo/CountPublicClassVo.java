package platform.education.generalTeachingAffair.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 导出--课程统计功能
 *
 * @author: yhc
 * @Date: 2020/11/27 13:42
 * @Description:
 */
public class CountPublicClassVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String className;

    /**
     * 人数上限
     */
    private String maxMember;

    /**
     * 已报名人数
     */
    private String enrollNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;

    private Integer schoolId;

    /**
     * 学生名称
     */
    private String stuName;

    /**
     * 班级名称
     */
    private String teamName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(String maxMember) {
        this.maxMember = maxMember;
    }

    public String getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        this.enrollNumber = enrollNumber;
    }
}