package platform.szxyzxx.jizuBiao.pojo;

public class JiZuBiao{
    /*
     *主键标识
     */
    private Integer id;

    /*
     *级组名称
     */
    private String jizuName;
    /*
     *创建时间
     */
    private String createTime;
    /*
     *修改时间
     */
    private String modiyTime;
    /*
     *是否删除
     */
    private Integer isDelete;
     /*
      *teacherId
      */
     private Integer teacherId;
     
     private String teacherName;
     private Integer zijizu;
    private Integer fuzeId;
    private Integer isJizuceng;

    public Integer getZijizu() {
        return zijizu;
    }

    public void setZijizu(Integer zijizu) {
        this.zijizu = zijizu;
    }

    public Integer getIsJizuceng() {
        return isJizuceng;
    }

    public void setIsJizuceng(Integer isJizuceng) {
        this.isJizuceng = isJizuceng;
    }

    public JiZuBiao(){}

    public Integer getFuzeId() {
        return fuzeId;
    }

    public void setFuzeId(Integer fuzeId) {
        this.fuzeId = fuzeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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


    public String getJizuName() {
        return jizuName;
    }

    public void setJizuName(String jizuName) {
        this.jizuName = jizuName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(String modiyTime) {
        this.modiyTime = modiyTime;
    }
}
