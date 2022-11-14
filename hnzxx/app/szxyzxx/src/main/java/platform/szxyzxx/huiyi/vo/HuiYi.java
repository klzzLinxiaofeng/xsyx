package platform.szxyzxx.huiyi.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class HuiYi {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 主题
     */
    private String theme;
    /*
     * 部门id
     */
    private Integer departmentId;
    /*
     * 部门名称
     */
    private String departmentName;
    /*
     * 申请人id
     */
    private Integer applicant;
    /*
     * 申请人姓名
     */
    private String applicantName;
    /*
     * 活动负责人id userId
     */
    private Integer eventManager;
    /*
     * 活动负责人姓名
     */
    private String eventManagerName;
    /*
     * 会务负责人id userId
     */
    private Integer huiwufuzeId;
    /*
     * 会务负责人姓名
     */
    private String huiwufuzeName;
    /*
     * 审核人
     */
    private Integer reviewer;
    /*
     * 审核人姓名
     */
    private String reviewerName;
    /*
     *开始时间
     */
    private String kaishiTime;
    /*
     *场地名称
     */
    private String changdiName;
    /*
     *场地id
     */
    private Integer changdiId;
    /*
     *领导席位
     */
    private String leadership;
    /*
     *活动物资
     */
    private String activitySupplies;
    /*
     *场地设备
     */
    private String equipment;
    /*
     *会晤细则
     */
    private String meeting;
    /*
     *附件id
     */
    private String fujianId;
    /*
     *附件地址
     */
    private String fujianUrl;
    /*
     *工作人员 id字符串
     */
    private String staff;
    /*
     *工作人员 姓名字符串，隔开
     */
    private String staffName;
    /*
     *其他人员 姓名字符串，隔开
     */
    private String qitaRenYuan;
    /*
     *参会人数
     */
    private Integer attendanceNumber;
    /*
     *是否审核(0未审核，1已审核通过带准备， 2已审核未通过3已审核已准备)
     */
    private Integer isAudit;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *修改时间
     */
    private Date modiyTime;
    /*
     *
     */
    private Integer isDelete;
    /*
     *审核不通过理由
     */
    private String liyou;
    /*
     *审核不通过理由
     */
    private String schoolYear;
    /*
     *审核不通过理由
     */
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

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getQitaRenYuan() {
        return qitaRenYuan;
    }

    public void setQitaRenYuan(String qitaRenYuan) {
        this.qitaRenYuan = qitaRenYuan;
    }

    private List<Map<String,String>> fujian;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getApplicant() {
        return applicant;
    }

    public void setApplicant(Integer applicant) {
        this.applicant = applicant;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public Integer getEventManager() {
        return eventManager;
    }

    public void setEventManager(Integer eventManager) {
        this.eventManager = eventManager;
    }

    public String getEventManagerName() {
        return eventManagerName;
    }

    public void setEventManagerName(String eventManagerName) {
        this.eventManagerName = eventManagerName;
    }

    public Integer getHuiwufuzeId() {
        return huiwufuzeId;
    }

    public void setHuiwufuzeId(Integer huiwufuzeId) {
        this.huiwufuzeId = huiwufuzeId;
    }

    public String getHuiwufuzeName() {
        return huiwufuzeName;
    }

    public void setHuiwufuzeName(String huiwufuzeName) {
        this.huiwufuzeName = huiwufuzeName;
    }

    public Integer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Integer reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getKaishiTime() {
        return kaishiTime;
    }

    public void setKaishiTime(String kaishiTime) {
        this.kaishiTime = kaishiTime;
    }

    public String getChangdiName() {
        return changdiName;
    }

    public void setChangdiName(String changdiName) {
        this.changdiName = changdiName;
    }

    public Integer getChangdiId() {
        return changdiId;
    }

    public void setChangdiId(Integer changdiId) {
        this.changdiId = changdiId;
    }

    public String getLeadership() {
        return leadership;
    }

    public void setLeadership(String leadership) {
        this.leadership = leadership;
    }

    public String getActivitySupplies() {
        return activitySupplies;
    }

    public void setActivitySupplies(String activitySupplies) {
        this.activitySupplies = activitySupplies;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting;
    }

    public String getFujianId() {
        return fujianId;
    }

    public void setFujianId(String fujianId) {
        this.fujianId = fujianId;
    }

    public String getFujianUrl() {
        return fujianUrl;
    }

    public void setFujianUrl(String fujianUrl) {
        this.fujianUrl = fujianUrl;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public Integer getAttendanceNumber() {
        return attendanceNumber;
    }

    public void setAttendanceNumber(Integer attendanceNumber) {
        this.attendanceNumber = attendanceNumber;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
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

    public String getLiyou() {
        return liyou;
    }

    public void setLiyou(String liyou) {
        this.liyou = liyou;
    }

    public List<Map<String, String>> getFujian() {
        return fujian;
    }

    public void setFujian(List<Map<String, String>> fujian) {
        this.fujian = fujian;
    }
}
