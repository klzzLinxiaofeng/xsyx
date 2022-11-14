package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 选课时间和选课信息关联表
 *
 * @author: yhc
 * @Date: 2020/11/17 19:23
 * @Description:
 */
public class PublicTimeRelatedVo implements Model<Integer> {

    /**
     *
     */
    private Integer id;

    /**
     * 选课时间 pj_public_time.id
     */
    private Integer timeId;

    /**
     * 开课管理id pj_public_class
     */
    private Integer publicClassId;

    /**
     * 对应的学校id
     */
    private Integer schoolId;

    /**
     *
     */
    private Integer isDeleted;

    /**
     * 插入时间
     */
    private Date createdAt;

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

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public Integer getPublicClassId() {
        return publicClassId;
    }

    public void setPublicClassId(Integer publicClassId) {
        this.publicClassId = publicClassId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}

