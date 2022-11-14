package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 学生选课和课程信息
 */
public class PublicUserVo implements Model<Integer> {

    @Override
    public Integer getKey() {
        return this.id;
    }

    /**
     *
     */
    private Integer id;

    /**
     * 学生账号 pj_student.id
     */
    private Integer studentId;

    /**
     * 开课管理id pj_public_class
     */
    private Integer publicClassId;

    /**
     * 插入时间
     */
    private Date createdAt;

    /**
     * 对应的学校id
     */
    private Integer schoolId;

    /**
     * 作废标记
     */
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPublicClassId() {
        return publicClassId;
    }

    public void setPublicClassId(Integer publicClassId) {
        this.publicClassId = publicClassId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
}

