package platform.education.generalTeachingAffair.model;


import java.util.Date;

/**
 * ExamQuestionVo
 * @author Zy
 *
 */
public class ExamQuestionVo {
    /*
    * 考试题目表id
    */
    private Integer id;
    /*
    * 考试日程id
    */
    private Integer examTeamSubjectId;

    /*
     * 考试题目标题
     */
    private String title;

    /*
     * 科目code
     */
    private String subjectCode;
    /*
     * 是否删除
     */
    private Boolean isDelete;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modiyTime;
    /*
     * 学年
     */
    private String schoolYear;
    /*
     * 学期
     */
    private String  schoolTrem;
    /*
     * 年级id
     */
    private Integer  gradeId;
    /*
     * 班级id
     */
    private Integer  teamId;
    /*
     * 学校id
     */
    private Integer  schoolId;
    /*
     * 考试类型
     */
    private String  examType;
    /*
     * exam_name
     */
    private String  examName;
    /*
     * 题号
     */
    private Integer order;
    /*
     * 分值
     */
    private Integer fenzhi;

    public Integer getFenzhi() {
        return fenzhi;
    }

    public void setFenzhi(Integer fenzhi) {
        this.fenzhi = fenzhi;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamTeamSubjectId() {
        return examTeamSubjectId;
    }

    public void setExamTeamSubjectId(Integer examTeamSubjectId) {
        this.examTeamSubjectId = examTeamSubjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
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

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
}
