package platform.szxyzxx.aesthetic.pojo;

import java.util.Date;
import java.util.Map;

public class AestheticPojo {
    /*
     * 主键id
     */
    private Integer id;
    /*
     * 学生_id
     */
    private Integer studentId;
    /*
     * 学生name
     */
    private String studentName;
    /*
     * 美术作品id
     */
    private String fineArtId;
    /*
     * 美术作品Url
     */
    private String fineArtUrl;
    /*
     * 点评
     */
    private String review;
    /*
     * 评分
     */
    private Integer reviewSore;
    /*
     * 比赛作品id
     */
    private String gameWorksId;
    /*
     * 比赛作品Url
     */
    private String gameWorksUrl;
    /*
     * 个人奖状id
     */
    private String jiangzhuanId;
    /*
     * 个人奖状url
     */
    private String jiangzhuanUrl;
    /*
     * 评语
     */
    private String comments;

    /*
    * 作品数
    */
    private Integer bookNumber;
    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String schoolTrem;
    private Map<String,String> fineArtPicter;
    private Map<String,String> gameWorksPicter;
    private Map<String,String> jiangzhuanPicter;
    private String[] fineArtUrlarr;
    private String[] gameWorksUrlarr;
    private String[] jiangzhuanUrlarr;

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

    public String[] getFineArtUrlarr() {
        return fineArtUrlarr;
    }

    public void setFineArtUrlarr(String[] fineArtUrlarr) {
        this.fineArtUrlarr = fineArtUrlarr;
    }

    public String[] getGameWorksUrlarr() {
        return gameWorksUrlarr;
    }

    public void setGameWorksUrlarr(String[] gameWorksUrlarr) {
        this.gameWorksUrlarr = gameWorksUrlarr;
    }

    public String[] getJiangzhuanUrlarr() {
        return jiangzhuanUrlarr;
    }

    public void setJiangzhuanUrlarr(String[] jiangzhuanUrlarr) {
        this.jiangzhuanUrlarr = jiangzhuanUrlarr;
    }

    public Map<String, String> getFineArtPicter() {
        return fineArtPicter;
    }

    public void setFineArtPicter(Map<String, String> fineArtPicter) {
        this.fineArtPicter = fineArtPicter;
    }

    public Map<String, String> getGameWorksPicter() {
        return gameWorksPicter;
    }

    public void setGameWorksPicter(Map<String, String> gameWorksPicter) {
        this.gameWorksPicter = gameWorksPicter;
    }

    public Map<String, String> getJiangzhuanPicter() {
        return jiangzhuanPicter;
    }

    public void setJiangzhuanPicter(Map<String, String> jiangzhuanPicter) {
        this.jiangzhuanPicter = jiangzhuanPicter;
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
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

    public String getFineArtId() {
        return fineArtId;
    }

    public void setFineArtId(String fineArtId) {
        this.fineArtId = fineArtId;
    }

    public String getFineArtUrl() {
        return fineArtUrl;
    }

    public void setFineArtUrl(String fineArtUrl) {
        this.fineArtUrl = fineArtUrl;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getReviewSore() {
        return reviewSore;
    }

    public void setReviewSore(Integer reviewSore) {
        this.reviewSore = reviewSore;
    }

    public String getGameWorksId() {
        return gameWorksId;
    }

    public void setGameWorksId(String gameWorksId) {
        this.gameWorksId = gameWorksId;
    }

    public String getGameWorksUrl() {
        return gameWorksUrl;
    }

    public void setGameWorksUrl(String gameWorksUrl) {
        this.gameWorksUrl = gameWorksUrl;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
