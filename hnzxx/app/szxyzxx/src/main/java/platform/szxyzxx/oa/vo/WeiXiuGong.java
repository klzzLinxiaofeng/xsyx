package platform.szxyzxx.oa.vo;

import java.util.Date;

public class WeiXiuGong {
    /*
     * id
     */
    private Integer id;
    /*
     * id  308
     */
    private String teacherName;
    /*
     *
     */
    private Integer teacherId;
    /*
     * 维修id
     */
    private String atId;
    /*
     * 维修id
     */
    private Integer isDelete;
    /*
     * 维修id
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        createTime = createTime;
    }
}
