package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学生请假管理
 *
 * @author: yhc
 * @Date: 2020/10/15 8:59
 * @Description:
 */
public class StudentAskingPojo implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 班级id
     */
    private Integer teamId;

    /**
     * 班级名称
     */
    private String teamName;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 审批班主任id
     */
    private Integer teacherId;

    /**
     * 班主任名称
     */

    private String teacherName;

    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 请求事由
     */
    private String remark;

    /**
     * 上传的文件uuid res_entity_file的uuid
     */
    private String uploadId;

    /**
     * 文件完整url
     */
    private String httpUrl;

    /**
     * 附件名称
     */
    private String fileName;

    /**
     * 审核状态（1：审核中 2：已通过 3：已驳回）
     */
    private Integer indiaStatus;

    /**
     * 驳回理由
     */
    private String rejected;

    /**
     * 审批时间
     */
    private Date reviewDate;

    private Integer isDeleted;

    /**
     * 创建时间
     */
    private Date createDate;

    private String searchDate;

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 请假时长
     */
    private BigDecimal timeConsuming;

    /**
     * 流水编号
     */
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(BigDecimal timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }


    public String getRejected() {
        return rejected;
    }

    public void setRejected(String rejected) {
        this.rejected = rejected;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取学生id
     *
     * @return student_id - 学生id
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置学生id
     *
     * @param studentId 学生id
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取审批班主任id
     *
     * @return teacher_id - 审批班主任id
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置审批班主任id
     *
     * @param teacherId 审批班主任id
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取开始时间
     *
     * @return begin_date - 开始时间
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * 设置开始时间
     *
     * @param beginDate 开始时间
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束时间
     *
     * @return end_date - 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置结束时间
     *
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取请求事由
     *
     * @return remark - 请求事由
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置请求事由
     *
     * @param remark 请求事由
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取上传的文件id res_entity_file的uuid
     *
     * @return upload_id - 上传的文件id res_entity_file的uuid
     */
    public String getUploadId() {
        return uploadId;
    }

    /**
     * 设置上传的文件id res_entity_file的uuid
     *
     * @param uploadId 上传的文件id res_entity_file的uuid
     */
    public void setUploadId(String uploadId) {
        this.uploadId = uploadId;
    }

    /**
     * 获取审核状态（1：审核中 2：已通过 3：已驳回）
     *
     * @return india_status - 审核状态（1：审核中 2：已通过 3：已驳回）
     */
    public Integer getIndiaStatus() {
        return indiaStatus;
    }

    /**
     * 设置审核状态（1：审核中 2：已通过 3：已驳回）
     *
     * @param indiaStatus 审核状态（1：审核中 2：已通过 3：已驳回）
     */
    public void setIndiaStatus(Integer indiaStatus) {
        this.indiaStatus = indiaStatus;
    }

    /**
     * 获取审批时间
     *
     * @return review_date - 审批时间
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * 设置审批时间
     *
     * @param reviewDate 审批时间
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    /**
     * @return is_deleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        return "StudentAskingPojo{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", stuName='" + stuName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", schoolId=" + schoolId +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                ", remark='" + remark + '\'' +
                ", uploadId='" + uploadId + '\'' +
                ", httpUrl='" + httpUrl + '\'' +
                ", fileName='" + fileName + '\'' +
                ", indiaStatus=" + indiaStatus +
                ", rejected='" + rejected + '\'' +
                ", reviewDate=" + reviewDate +
                ", isDeleted=" + isDeleted +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", timeConsuming=" + timeConsuming +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public Integer getKey() {
        return this.id;
    }
}