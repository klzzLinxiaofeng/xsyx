package platform.szxyzxx.pingyumoban.vo;

import java.util.Date;

public class PingYuMoBan {
    private Integer id;
    private String text;
    private Integer subjectId;
    private  String subjectName;
     private Integer subjctZhiBiaoId;

     private String subjectZhiBiaoName;

    private Integer gradeId;

    private String gradeName;
    private Integer pingYuId;
    /*
    * 评语类型名称
    */
    private String pingYuTypeName;

    private Date createTime;

    private String dengji;

    private Date modiyTime;

    private Integer isDelete;

    private Integer minScore;
    private Integer maxScore;

    private String schoolYear;

    private String schoolTrem;

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

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getSubjctZhiBiaoId() {
        return subjctZhiBiaoId;
    }

    public void setSubjctZhiBiaoId(Integer subjctZhiBiaoId) {
        this.subjctZhiBiaoId = subjctZhiBiaoId;
    }

    public String getSubjectZhiBiaoName() {
        return subjectZhiBiaoName;
    }

    public void setSubjectZhiBiaoName(String subjectZhiBiaoName) {
        this.subjectZhiBiaoName = subjectZhiBiaoName;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getPingYuTypeName() {
        return pingYuTypeName;
    }

    public void setPingYuTypeName(String pingYuTypeName) {
        this.pingYuTypeName = pingYuTypeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getPingYuId() {
        return pingYuId;
    }

    public void setPingYuId(Integer pingYuId) {
        this.pingYuId = pingYuId;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
