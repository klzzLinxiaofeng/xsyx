package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

/**
 * PublicClass
 *
 * @author AutoCreate
 */
public class PublicClass implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * 课程ID
     */
    private Integer id;
    /**
     * 所在学校pj_shool.id
     */
    private Integer schoolId;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 上课老师 pj_teacher.id
     */
    private String teacherId;
    private String teacherName;

    /**
     * 学生id
     */
    private String studentId;
    private Integer stuId;
    /**
     * 上课时间标签id
     */
    private String timeId;

    /**
     * 上课时间
     */
    private String classTime;

    /**
     * 开始上课日期
     */
    private java.util.Date beginDate;
    /**
     * 课程总节数
     */
    private Integer classNumber;
    /**
     * 人数上限
     */
    private Integer maxMember;
    /**
     * 已报名人数
     */
    private Integer enrollNumber;
    /**
     * 报名截止日期
     */
    private java.util.Date expiryDate;
    /**
     * 报名详情
     */
    private String enrollDesc;
    /**
     * 课程详情
     */
    private String classDesc;
    /**
     * 作废标记
     */
    private Boolean isDelete;
    /**
     * 创建日期
     */
    private java.util.Date createDate;
    /**
     * 修改日期
     */
    private java.util.Date modifyDate;

    /**
     * 类别
     */
    private Integer leixing;
    /**
     * 学年
     */
    private String  schoolYear;

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Integer getLeixing() {
        return leixing;
    }

    public void setLeixing(Integer leixing) {
        this.leixing = leixing;
    }

    /**
     * @Date：11:08 2020/9/21
     * @Description：年级
     */
    private String grade;
    public String getFullName() {
        return fullName;
    }

    /**
     * 年级数组
     */
    private String fullNameArr;

    /**
     * 封面uuid res_entity_file
     */
    private String coverUuid;

    /**
     * 封面完整url
     */
    private String coverUrl;

    /**
     * 0: 学校社团
     * 1: 课后5+2课程
     * 2：寒暑假
     */
    private Integer classType;

    /*
    * 学费
    */
    private Double  xuefei;

    /*
     * 材料费
     */
    private Double  cailiaofei;
    /*
    * 是否需要材料费
    */
    private Integer  isCailiao;

    public Integer getIsCailiao() {
        return isCailiao;
    }

    public void setIsCailiao(Integer isCailiao) {
        this.isCailiao = isCailiao;
    }

    /**
     * 标识是否是新增学生，修改选课的人数1:添加 2：删除
     */
    private Integer enrollNumberFlg;


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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Integer getEnrollNumberFlg() {
        return enrollNumberFlg;
    }

    public void setEnrollNumberFlg(Integer enrollNumberFlg) {
        this.enrollNumberFlg = enrollNumberFlg;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getFullNameArr() {
        return fullNameArr;
    }

    public void setFullNameArr(String fullNameArr) {
        this.fullNameArr = fullNameArr;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    private String fullName;


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public PublicClass() {

    }

    public PublicClass(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取课程ID
     *
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置课程ID
     *
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所在学校pj_shool.id
     *
     * @return java.lang.Integer
     */
    public Integer getSchoolId() {
        return this.schoolId;
    }

    /**
     * 设置所在学校pj_shool.id
     *
     * @param schoolId
     * @type java.lang.Integer
     */
    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    /**
     * 获取课程名称
     *
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置课程名称
     *
     * @param name
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }

    /**
     * 获取开始上课日期
     *
     * @return java.util.Date
     */
    public java.util.Date getBeginDate() {
        return this.beginDate;
    }

    /**
     * 设置开始上课日期
     *
     * @param beginDate
     * @type java.util.Date
     */
    public void setBeginDate(java.util.Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * 获取课程总节数
     *
     * @return java.lang.Integer
     */
    public Integer getClassNumber() {
        return this.classNumber;
    }

    /**
     * 设置课程总节数
     *
     * @param classNumber
     * @type java.lang.Integer
     */
    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    /**
     * 获取人数上限
     *
     * @return java.lang.Integer
     */
    public Integer getMaxMember() {
        return this.maxMember;
    }

    /**
     * 设置人数上限
     *
     * @param maxMember
     * @type java.lang.Integer
     */
    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    /**
     * 获取已报名人数
     *
     * @return java.lang.String
     */
    public Integer getEnrollNumber() {
        return this.enrollNumber;
    }

    /**
     * 设置已报名人数
     *
     * @param enrollNumber
     * @type java.lang.String
     */
    public void setEnrollNumber(Integer enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    /**
     * 获取报名截止日期
     *
     * @return java.util.Date
     */
    public java.util.Date getExpiryDate() {
        return this.expiryDate;
    }

    /**
     * 设置报名截止日期
     *
     * @param expiryDate
     * @type java.util.Date
     */
    public void setExpiryDate(java.util.Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * 获取报名详情
     *
     * @return java.lang.String
     */
    public String getEnrollDesc() {
        return this.enrollDesc;
    }

    /**
     * 设置报名详情
     *
     * @param enrollDesc
     * @type java.lang.String
     */
    public void setEnrollDesc(String enrollDesc) {
        this.enrollDesc = enrollDesc;
    }

    /**
     * 获取课程详情
     *
     * @return java.lang.String
     */
    public String getClassDesc() {
        return this.classDesc;
    }

    /**
     * 设置课程详情
     *
     * @param classDesc
     * @type java.lang.String
     */
    public void setClassDesc(String classDesc) {
        this.classDesc = classDesc;
    }

    /**
     * 获取作废标记
     *
     * @return java.lang.Boolean
     */
    public Boolean getIsDelete() {
        return this.isDelete;
    }

    /**
     * 设置作废标记
     *
     * @param isDelete
     * @type java.lang.Boolean
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建日期
     *
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改日期
     *
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置修改日期
     *
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}