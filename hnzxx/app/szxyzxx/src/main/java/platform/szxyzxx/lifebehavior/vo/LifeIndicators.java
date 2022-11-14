package platform.szxyzxx.lifebehavior.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:26
 * @Version 1.0
 */
public class LifeIndicators {
    /*
    * id
    */
    private Integer id;
    /*
     * 指标名称
     */
    private String  name;
    /*
     * 指标名称
     */
    private Integer score;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date  modiyTime;
    /*
     *
     */
    private Integer  isDelete;
    private String  schoolYear;
    private String  schoolTrem;
    public LifeIndicators(){}

    public LifeIndicators(Integer id, String name, Integer score, Date createTime, Date modiyTime, Integer isDelete, String schoolYear, String schoolTrem) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.createTime = createTime;
        this.modiyTime = modiyTime;
        this.isDelete = isDelete;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
