package com.xunyunedu.PublishAndAcceptJob.pojo;

import java.util.Date;

public class StudentHomeWoke {
    private Integer id;
    private Integer jobId;
    private Date createTime;
    private Date modieTime;
    private Integer isDelete;
    private Integer HomeWokeId;
    private  Integer studentId;
    private  String studentName;
    private String teamName;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /*
    * 作业状态 0待提交，1已提交 ，2缺交，3 补交
    */
    private Integer zhuantai;
    /*
     * 作业状态 0待提交，1已提交 ，2缺交，3 补交
     */
    private String zhuangzhongwen;

    /*
    * 评价分值
    */

    private Float fenzhi;
    /*
     * 评价等级   a+ 100.a90--99.5 ,b+85-89.5 b80-89.5  c70-79.5 d60-69.5 E<60
     */

    private String dengji;
    /*
    * 评语
    */
    private String pingyu;

    private String errorInfo;//错误信息
    //教师名称
    private String teacherName;
    //科目名称
    private String subjectName;
    //图片id
    private String picter_uuid;
    //图片地址
    private String picterUrl;
    //作业内容
    private String contont;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getPicter_uuid() {
        return picter_uuid;
    }

    public void setPicter_uuid(String picter_uuid) {
        this.picter_uuid = picter_uuid;
    }

    public String getPicterUrl() {
        return picterUrl;
    }

    public void setPicterUrl(String picterUrl) {
        this.picterUrl = picterUrl;
    }

    public String getContont() {
        return contont;
    }

    public void setContont(String contont) {
        this.contont = contont;
    }

    @Override
    public String toString() {
        return "StudentHomeWoke{" +
                "id=" + id +
                ", jobId=" + jobId +
                ", createTime=" + createTime +
                ", modieTime=" + modieTime +
                ", isDelete=" + isDelete +
                ", HomeWokeId=" + HomeWokeId +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", zhuantai=" + zhuantai +
                ", zhuangzhongwen='" + zhuangzhongwen + '\'' +
                ", fenzhi=" + fenzhi +
                ", dengji='" + dengji + '\'' +
                ", pingyu='" + pingyu + '\'' +
                ", errorInfo='" + errorInfo + '\'' +
                '}';
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getZhuangzhongwen() {
        return zhuangzhongwen;
    }

    public void setZhuangzhongwen(String zhuangzhongwen) {
        this.zhuangzhongwen = zhuangzhongwen;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Float getFenzhi() {
        return fenzhi;
    }

    public void setFenzhi(Float fenzhi) {
        this.fenzhi = fenzhi;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getHomeWokeId() {
        return HomeWokeId;
    }

    public void setHomeWokeId(Integer homeWokeId) {
        HomeWokeId = homeWokeId;
    }

    public Integer getZhuantai() {
        return zhuantai;
    }

    public void setZhuantai(Integer zhuantai) {
        this.zhuantai = zhuantai;
    }

}
