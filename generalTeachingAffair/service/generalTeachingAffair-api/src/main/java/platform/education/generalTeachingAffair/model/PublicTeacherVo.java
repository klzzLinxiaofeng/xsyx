package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 选课教师信息
 *
 * @author AutoCreate
 */
public class PublicTeacherVo implements Model<Integer> {

    /**
     * 课程ID
     */
    private Integer id;

//    private Integer[] ids;
//
//    public Integer[] getIds() {
//        return ids;
//    }
//
//    public void setIds(Integer[] ids) {
//        this.ids = ids;
//    }

    /**
     * 教师名称
     */
    private String name;

    private Integer schoolId;

    /**
     * 封面uuid res_entity_file
     */
    private String coverUuid;

    /**
     * 封面完整url
     */
    private String coverUrl;

    /**
     * 教师详情
     */
    private String teacherDesc;
    /**
     * 作废标记
     */
    private Integer isDelete;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date modifyDate;

    @Override
    public Integer getKey() {
        return this.id;
    }
    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUuid() {
        return coverUuid;
    }

    public void setCoverUuid(String coverUuid) {
        this.coverUuid = coverUuid;
    }

    public String getTeacherDesc() {
        return teacherDesc;
    }

    public void setTeacherDesc(String teacherDesc) {
        this.teacherDesc = teacherDesc;
    }

    public Integer getDelete() {
        return isDelete;
    }

    public void setDelete(Integer delete) {
        isDelete = delete;
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

    @Override
    public String toString() {
        return "PublicTeacherVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coverUuid='" + coverUuid + '\'' +
                ", teacherDesc='" + teacherDesc + '\'' +
                ", isDelete=" + isDelete +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                '}';
    }
}