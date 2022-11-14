package com.xunyunedu.personinfor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xunyunedu.teacher.pojo.PublicTeacherPojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 选课信息
 *
 * @author: yhc
 * @Date: 2020/9/19 16:53
 * @Description: 选课信息
 */
public class PublicClassPojo implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 选课id
     */
    private Integer cid;
    /**
     * 选课名称
     */
    private String cname;

    /**
     * 人数上限
     */
    private Integer maxMember;

    /**
     * 已选人数
     */
    private Integer enrollNumber;

    /**
     * 选课截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date expiryDate;

    /**
     * 选课开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginDate;

    /**
     * 课程详情
     */
    private String classDesc;

    /**
     * 老师名称
     */
    private String tname;

    /**
     * 年级 1-6
     */
    private Integer grade;

    /**
     * 当前课程是否选择 1：是 0 ：否
     */
    private Integer chooseState;

    /**
     * 课程是否删除
     */
    private Integer isDelete;
    /**
     * 课程总节数
     */
    private Integer classNmber;

    /**
     * 学生选课时间
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    /**
     * 选课人数是否已经满了 0:没有 1：已满
     */
    private Integer isFull;

    /**
     * 这门课程对应的学生是否时间冲突0: 没有 1是
     */
    private Integer isConflict;

    /**
     * 0: 学校社团1: 课后5+2课程3 寒暑假
     */
    private Integer classType;


    /**
     * 封面uuid res_entity_file
     */
    private String coverUuid;

    /**
     * 封面完整url
     */
    private String coverUrl;

    /**
     * 上课时间
     */
    private String classTime;

    private Integer schoolId;

    /**
     * 选课截止日期超时后保存选课信息，0:否 1:是
     */
    private Integer isSaveHistory;
    /**
     * 选课学费
     */
    private Double xuefei;
    /**
     * 选课材料费
     */
    private Double cailiaofei;
    /**
     * 是否有材料费 0没有 1有
     */
    private Integer isCailiao;

    /**
     * 类型
     */
    private Integer leixing;
    /**
     * 星期
     */
    private Integer weekDate;
    /**
     * 下课时间
     */
    private String classEndTime;
    /**
     * 上课时间
     */
    private String classStartTime;

    public Integer getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(Integer weekDate) {
        this.weekDate = weekDate;
    }

    public String getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(String classEndTime) {
        this.classEndTime = classEndTime;
    }

    public String getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(String classStartTime) {
        this.classStartTime = classStartTime;
    }

    public Integer getLeixing() {
        return leixing;
    }

    public void setLeixing(Integer leixing) {
        this.leixing = leixing;
    }

    public Double getXuefei() {
        return xuefei;
    }

    public void setXuefei(Double xuefei) {
        this.xuefei = xuefei;
    }

    public Double getCailiaofei() {
        return cailiaofei;
    }

    public void setCailiaofei(Double cailiaofei) {
        this.cailiaofei = cailiaofei;
    }

    public Integer getIsCailiao() {
        return isCailiao;
    }

    public void setIsCailiao(Integer isCailiao) {
        this.isCailiao = isCailiao;
    }

    /**
     * 课程对应的教师简介
     */
    private List<PublicTeacherPojo> listTeachers;

    public Integer getIsSaveHistory() {
        return isSaveHistory;
    }

    public void setIsSaveHistory(Integer isSaveHistory) {
        this.isSaveHistory = isSaveHistory;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public List<PublicTeacherPojo> getListTeachers() {
        return listTeachers;
    }

    public void setListTeachers(List<PublicTeacherPojo> listTeachers) {
        this.listTeachers = listTeachers;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    public String getCoverUuid() {
        return coverUuid;
    }

    public void setCoverUuid(String coverUuid) {
        this.coverUuid = coverUuid;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Integer getIsConflict() {
        return isConflict;
    }

    public void setIsConflict(Integer isConflict) {
        this.isConflict = isConflict;
    }

    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getClassNmber() {
        return classNmber;
    }

    public void setClassNmber(Integer classNmber) {
        this.classNmber = classNmber;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public Integer getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getClassDesc() {
        return classDesc;
    }

    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Integer getChooseState() {
        return chooseState;
    }

    public void setChooseState(Integer chooseState) {
        this.chooseState = chooseState;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "PublicClassPojo{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", maxMember=" + maxMember +
                ", enrollNumber=" + enrollNumber +
                ", expiryDate=" + expiryDate +
                ", beginDate=" + beginDate +
                ", classDesc='" + classDesc + '\'' +
                ", tname='" + tname + '\'' +
                ", grade=" + grade +
                ", chooseState=" + chooseState +
                ", isDelete=" + isDelete +
                ", classNmber=" + classNmber +
                ", createdAt=" + createdAt +
                ", isFull=" + isFull +
                ", isConflict=" + isConflict +
                ", classType=" + classType +
                ", coverUuid='" + coverUuid + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", classTime='" + classTime + '\'' +
                ", listTeachers=" + listTeachers +
                '}';
    }
}
