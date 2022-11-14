package platform.szxyzxx.wokeBiao.pojo;

import java.util.Date;

public class ZhouQi {
    /*
     * 主键id
     */
    private Integer id;
    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String xueqi;
    /*
     * 周数
     */
    private Integer zhoushu;
    /*
     * 是否确认 0否，1是
     */
    private Integer isStats;
    /*
     * 创建时间
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getXueqi() {
        return xueqi;
    }

    public void setXueqi(String xueqi) {
        this.xueqi = xueqi;
    }

    public Integer getZhoushu() {
        return zhoushu;
    }

    public void setZhoushu(Integer zhoushu) {
        this.zhoushu = zhoushu;
    }

    public Integer getIsStats() {
        return isStats;
    }

    public void setIsStats(Integer isStats) {
        this.isStats = isStats;
    }
}
