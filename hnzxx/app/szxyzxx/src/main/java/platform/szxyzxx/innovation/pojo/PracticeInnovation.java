package platform.szxyzxx.innovation.pojo;


import platform.education.generalTeachingAffair.model.PublicClass;

import java.util.Date;
import java.util.List;
import java.util.Map;

/*
* 实践创新
*/
public class PracticeInnovation {
    /*
    *主键 id
    */
    private Integer id;
    /*
     *学生id
     */
    private Integer studentId;
    /*
     *学生name
     */
    private String studentName;
    /*
     *分数
     */
    private Integer score;
    /*
     *班级节假日课程
     */
    private String teamJiaDay;
    /*
    * 课程列表
    */
    private List<PublicClass> publicClassList;
    /*
     *比赛图片
     */
    private String pctreId;
    /*
     *比赛图片url
     */
    private String[] pctreUrl;
    /*
     *个人奖状
     */
    private String jiangzhuanId;
    /*
     *个人奖状url
     */
    private String [] jiangzhuanUrl;
    /*
     *图书借阅数据
     */
    private Integer bookNumer;
    /*
     *学校评语
     */
    private String pingyu;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *学年
     */
    private String schoolYear;
    /*
     *学期
     */
    private String schoolTrem;

    private Map<String,String> picter;
    private Map<String,String> jiangzhuanPicter;

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

    public Map<String, String> getPicter() {
        return picter;
    }

    public void setPicter(Map<String, String> picter) {
        this.picter = picter;
    }

    public Map<String, String> getJiangzhuanPicter() {
        return jiangzhuanPicter;
    }

    public void setJiangzhuanPicter(Map<String, String> jiangzhuanPicter) {
        this.jiangzhuanPicter = jiangzhuanPicter;
    }

    public String [] getPctreUrl() {
        return pctreUrl;
    }

    public void setPctreUrl(String [] pctreUrl) {
        this.pctreUrl = pctreUrl;
    }

    public String [] getJiangzhuanUrl() {
        return jiangzhuanUrl;
    }

    public void setJiangzhuanUrl(String [] jiangzhuanUrl) {
        this.jiangzhuanUrl = jiangzhuanUrl;
    }

    public List<PublicClass> getPublicClassList() {
        return publicClassList;
    }

    public void setPublicClassList(List<PublicClass> publicClassList) {
        this.publicClassList = publicClassList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeamJiaDay() {
        return teamJiaDay;
    }

    public void setTeamJiaDay(String teamJiaDay) {
        this.teamJiaDay = teamJiaDay;
    }

    public String getPctreId() {
        return pctreId;
    }

    public void setPctreId(String pctreId) {
        this.pctreId = pctreId;
    }


    public String getJiangzhuanId() {
        return jiangzhuanId;
    }

    public void setJiangzhuanId(String jiangzhuanId) {
        this.jiangzhuanId = jiangzhuanId;
    }


    public Integer getBookNumer() {
        return bookNumer;
    }

    public void setBookNumer(Integer bookNumer) {
        this.bookNumer = bookNumer;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
