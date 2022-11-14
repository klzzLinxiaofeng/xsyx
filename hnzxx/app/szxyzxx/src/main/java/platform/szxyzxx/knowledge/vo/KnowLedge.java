package platform.szxyzxx.knowledge.vo;


import java.util.Date;

/*
*知识点
*/
public class KnowLedge {
    /*
    *主键id
    */
    private  Integer id;
    /*
     *年级id
     */
    private  Integer gradeId;
    /*
     *年级Name
     */
    private  String  gradeName;
    /*
     *年级subjectId
     */
    private  Integer subjectId;
    /*
     *年级subjectName
     */
    private  String subjectName;
    /*
     *学期
     */
    private  String  schoolTrem;
    /*
     *学年
     */
    private  String  schoolYear;
    /*
     *是否删除
     */
    private  Integer   isDelete;
    /*
    * 名称
    */
    private  String name;
    /*
    * 创建时间
    */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modiyTime;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }



    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
