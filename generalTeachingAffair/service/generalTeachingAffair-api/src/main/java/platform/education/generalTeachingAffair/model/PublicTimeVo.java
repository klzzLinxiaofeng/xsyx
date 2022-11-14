package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 选课时间
 *
 * @author AutoCreate
 */
public class PublicTimeVo implements Model<Integer> {
    /**
     * 课程ID
     */
    private Integer id;
    /**
     * 原上课时间
     */
    private String classTime;
    /**
     * xin上课时间
     */
    private String classTimeStart;
    /**
     * 下课时间
     */
    private String classTimeEnd;
    /**
     * 星期
     */
    private Integer weekDate;

    public String getClassTimeStart() {
        return classTimeStart;
    }

    public void setClassTimeStart(String classTimeStart) {
        this.classTimeStart = classTimeStart;
    }

    public String getClassTimeEnd() {
        return classTimeEnd;
    }

    public void setClassTimeEnd(String classTimeEnd) {
        this.classTimeEnd = classTimeEnd;
    }

    public Integer getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(Integer weekDate) {
        this.weekDate = weekDate;
    }

    private Integer schoolId;

    /**
     * 作废标记
     */
    private Integer isDelete;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改日期
     */
    private Date modifyDate;

    @Override
    public Integer getKey() {
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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