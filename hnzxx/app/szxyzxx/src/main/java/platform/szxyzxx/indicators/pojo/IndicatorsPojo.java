package platform.szxyzxx.indicators.pojo;

import java.util.Date;

public class IndicatorsPojo {
    /*
    * 主键id
    */
    private Integer id;
    /*
     *班级id
     */
    private Integer gradeId;
    /*
     * 班级name
     */
    private String gradeName;
    /*
     * 体测指标名称
     */
    private String name;
    /*
     * 单位
     */
    private String danwei;
    /*
     * 分数
     */
    private String score;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modieTime;
    /*
     * 主键id
     */
    private Integer isDelete;
    /*
     * 学年
     */
    private String schoolYear;

    /*
    *  学校id
    */
   private  Integer schoolId;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDanwei() {
        return danwei;
    }

    public void setDanwei(String danwei) {
        this.danwei = danwei;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModieTime() {
        return modieTime;
    }

    public void setModieTime(Date modieTime) {
        this.modieTime = modieTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}
