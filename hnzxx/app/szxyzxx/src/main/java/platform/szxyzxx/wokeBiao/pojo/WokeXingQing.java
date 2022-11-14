package platform.szxyzxx.wokeBiao.pojo;

import java.util.Date;


/*
* 表zy_zhouqi_xiangqing
* autor ZY
* data  2022-01-16
*/
public class WokeXingQing {
    /*
     * 主键id
     */
    private Integer id;
    /*
     * ZhouQi表主键id
     */
    private Integer zhouqiId;
    /*
     * 星期数1,2,3,4,5,6,7
     */
    private Integer weekNum;
    /*
     * 课程节数
     */
    private Integer jieNum;
    /*
     * 课程安排内容
     */
    private String  contont;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modiyTime;
    /*
    * 上课时间
    */
    private String classTime;

    private String classDate;
    /*
    * zhuantai
    */
    private Integer isStats;

    public Integer getIsStats() {
        return isStats;
    }

    public void setIsStats(Integer isStats) {
        this.isStats = isStats;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZhouqiId() {
        return zhouqiId;
    }

    public void setZhouqiId(Integer zhouqiId) {
        this.zhouqiId = zhouqiId;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public Integer getJieNum() {
        return jieNum;
    }

    public void setJieNum(Integer jieNum) {
        this.jieNum = jieNum;
    }

    public String getContont() {
        return contont;
    }

    public void setContont(String contont) {
        this.contont = contont;
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
}
