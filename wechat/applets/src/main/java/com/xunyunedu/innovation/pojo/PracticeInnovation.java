package com.xunyunedu.innovation.pojo;

import com.xunyunedu.character.pojo.Picture;
import com.xunyunedu.personinfor.pojo.PublicClassPojo;

import java.util.List;

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
    private List<PublicClassPojo> publicClassList;

    /*
     * 图片
     */
    private List<Picture> PictureList;
    /*
    * 图片
    */
   private List<Picture> PictureListTwo;
    /*
     *比赛图片
     */
    private String pctreId;
    /*
     *比赛图片url
     */
    private String pctreUrl;
    /*
     *个人奖状
     */
    private String jiangzhuanId;
    /*
     *个人奖状url
     */
    private String jiangzhuanUrl;
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
    private String createTime;

    public PracticeInnovation(){}

    public PracticeInnovation(Integer id, Integer studentId, String studentName, Integer score, String teamJiaDay, List<PublicClassPojo> publicClassList, List<Picture> pictureList, List<Picture> pictureListTwo, String pctreId, String pctreUrl, String jiangzhuanId, String jiangzhuanUrl, Integer bookNumer, String pingyu, String createTime) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.score = score;
        this.teamJiaDay = teamJiaDay;
        this.publicClassList = publicClassList;
        PictureList = pictureList;
        PictureListTwo = pictureListTwo;
        this.pctreId = pctreId;
        this.pctreUrl = pctreUrl;
        this.jiangzhuanId = jiangzhuanId;
        this.jiangzhuanUrl = jiangzhuanUrl;
        this.bookNumer = bookNumer;
        this.pingyu = pingyu;
        this.createTime = createTime;
    }

    public List<Picture> getPictureList() {
        return PictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        PictureList = pictureList;
    }

    public List<Picture> getPictureListTwo() {
        return PictureListTwo;
    }

    public void setPictureListTwo(List<Picture> pictureListTwo) {
        PictureListTwo = pictureListTwo;
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

    public List<PublicClassPojo> getPublicClassList() {
        return publicClassList;
    }

    public void setPublicClassList(List<PublicClassPojo> publicClassList) {
        this.publicClassList = publicClassList;
    }

    public String getPctreId() {
        return pctreId;
    }

    public void setPctreId(String pctreId) {
        this.pctreId = pctreId;
    }

    public String getPctreUrl() {
        return pctreUrl;
    }

    public void setPctreUrl(String pctreUrl) {
        this.pctreUrl = pctreUrl;
    }

    public String getJiangzhuanId() {
        return jiangzhuanId;
    }

    public void setJiangzhuanId(String jiangzhuanId) {
        this.jiangzhuanId = jiangzhuanId;
    }

    public String getJiangzhuanUrl() {
        return jiangzhuanUrl;
    }

    public void setJiangzhuanUrl(String jiangzhuanUrl) {
        this.jiangzhuanUrl = jiangzhuanUrl;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
