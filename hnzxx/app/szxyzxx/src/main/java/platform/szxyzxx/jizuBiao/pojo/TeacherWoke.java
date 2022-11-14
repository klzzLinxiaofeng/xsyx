package platform.szxyzxx.jizuBiao.pojo;

import java.util.Date;

public class TeacherWoke {
    /*
    * id
    */
    private Integer id;
    /*
     * 教师id
     */
    private Integer teacherId;
    /*
     * 教师id
     */
    private String teacherName;
    /*
     * 布置地部门
     */
    private String numenjizuName;
    /*
     * 工作类容
     */
    private String wokeContent;
    /*
     * 创建时间
     */
    private Date craeteTime;
    /*
     * 修改时间
     */
    private Date modiyTime;

    /*
     *学年
     */
    private String schoolYear;
    /*
     *学期
     */
    private String schoolTrem;
    /*
     *周数
     */
    private Integer zhoushu;
    private Integer is_delete;

    private Integer jizuId;
    public TeacherWoke(){}
    public TeacherWoke(Integer id, Integer teacherId, String teacherName, String numenjizuName, String wokeContent, Date craeteTime, Date modiyTime, String schoolYear, String schoolTrem, Integer zhoushu, Integer is_delete, Integer jizuId) {
        this.id = id;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.numenjizuName = numenjizuName;
        this.wokeContent = wokeContent;
        this.craeteTime = craeteTime;
        this.modiyTime = modiyTime;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
        this.zhoushu = zhoushu;
        this.is_delete = is_delete;
        this.jizuId = jizuId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getJizuId() {
        return jizuId;
    }

    public void setJizuId(Integer jizuId) {
        this.jizuId = jizuId;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public Integer getZhoushu() {
        return zhoushu;
    }

    public void setZhoushu(Integer zhoushu) {
        this.zhoushu = zhoushu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getNumenjizuName() {
        return numenjizuName;
    }

    public void setNumenjizuName(String numenjizuName) {
        this.numenjizuName = numenjizuName;
    }

    public String getWokeContent() {
        return wokeContent;
    }

    public void setWokeContent(String wokeContent) {
        this.wokeContent = wokeContent;
    }

    public Date getCraeteTime() {
        return craeteTime;
    }

    public void setCraeteTime(Date craeteTime) {
        this.craeteTime = craeteTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }
}
