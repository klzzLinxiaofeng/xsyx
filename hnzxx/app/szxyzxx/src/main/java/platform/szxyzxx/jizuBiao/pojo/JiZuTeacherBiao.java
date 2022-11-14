package platform.szxyzxx.jizuBiao.pojo;

import java.util.Date;

public class JiZuTeacherBiao {
    private Integer id;
    private Integer jizuId;
    private Integer teacherId;
    private Date createTime;
    private Date modiyTime;
    private Integer isDelete;
    private String teacherName;
    private String isPrincipal;


    public String getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(String isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    /*
    * 教师工作量
    */
    private Integer zongshu;
    public JiZuTeacherBiao(){}

    public Integer getZongshu() {
        return zongshu;
    }

    public void setZongshu(Integer zongshu) {
        this.zongshu = zongshu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJizuId() {
        return jizuId;
    }

    public void setJizuId(Integer jizuId) {
        this.jizuId = jizuId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
