package platform.szxyzxx.huojiang.vo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class HuoJiang {
    /*
    *获奖
    */
    private  Integer id;
    /*
     *主题
     */
    private  String theme;
    /*
     *获奖时间
     */
    private  String winningTime;
    /*
     *作评名称
     */
    private  String nameWoke;
    /*
     *发奖单位
     */
    private  String allocated;
    /*
     *获奖学生id
     */
    private  String studentIds;
    /*
     *获奖学生name
     */
    private  String studentNames;
    /*
     *获奖教师id
     */
    private  String teacherIds;
    /*
     *获奖教师name
     */
    private  String teacherNames;
    /*
     *获奖级别
     */
    private  Integer winningLevel;
    /*
     *获奖级别name
     */
    private  String winningLevelName;
    /*
     *等次
     */
    private  Integer dengci;
    /*
     *等次name
     */
    private  String dengciName;
    /*
     *图片id
     */
    private  String pictureId;
    /*
     *创建时间
     */
    private Date createTime;
    /*
     *修改时间
     */
    private  String modiyTime;
    /*
     *是否删除
     */
    private  Integer isDelete;
    /*
     *是否审核
     */
    private  Integer isAuit;
    /*
     *申请人id
     */
    private  Integer shenqingren;
    /*
     *学年
     */
    private  String schoolYear;
    /*
     *学期
     */
    private  String schoolTrem;
    /*
     *申请人name
     */
    private  String shenqingName;
    /*
    * 图片list
    */
    private List<Map<String,Object>> pictureList;

    /*
    * 获奖属性
    */
    private  String shuXing;
    /*
     * 获奖类型
     */
    private  String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShuXing() {
        return shuXing;
    }

    public void setShuXing(String shuXing) {
        this.shuXing = shuXing;
    }

    public List<Map<String, Object>> getPictureList() {
        return pictureList;
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

    public void setPictureList(List<Map<String, Object>> pictureList) {
        this.pictureList = pictureList;
    }

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

    public String getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(String winningTime) {
        this.winningTime = winningTime;
    }

    public String getNameWoke() {
        return nameWoke;
    }

    public void setNameWoke(String nameWoke) {
        this.nameWoke = nameWoke;
    }

    public String getAllocated() {
        return allocated;
    }

    public void setAllocated(String allocated) {
        this.allocated = allocated;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public String getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentNames) {
        this.studentNames = studentNames;
    }

    public String getTeacherIds() {
        return teacherIds;
    }

    public void setTeacherIds(String teacherIds) {
        this.teacherIds = teacherIds;
    }

    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public Integer getWinningLevel() {
        return winningLevel;
    }

    public void setWinningLevel(Integer winningLevel) {
        this.winningLevel = winningLevel;
    }

    public String getWinningLevelName() {
        return winningLevelName;
    }

    public void setWinningLevelName(String winningLevelName) {
        this.winningLevelName = winningLevelName;
    }

    public Integer getDengci() {
        return dengci;
    }

    public void setDengci(Integer dengci) {
        this.dengci = dengci;
    }

    public String getDengciName() {
        return dengciName;
    }

    public void setDengciName(String dengciName) {
        this.dengciName = dengciName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(String modiyTime) {
        this.modiyTime = modiyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsAuit() {
        return isAuit;
    }

    public void setIsAuit(Integer isAuit) {
        this.isAuit = isAuit;
    }

    public Integer getShenqingren() {
        return shenqingren;
    }

    public void setShenqingren(Integer shenqingren) {
        this.shenqingren = shenqingren;
    }

    public String getShenqingName() {
        return shenqingName;
    }

    public void setShenqingName(String shenqingName) {
        this.shenqingName = shenqingName;
    }

    @Override
    public String toString() {
        return "HuoJiang{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", winningTime='" + winningTime + '\'' +
                ", nameWoke='" + nameWoke + '\'' +
                ", allocated='" + allocated + '\'' +
                ", studentIds='" + studentIds + '\'' +
                ", studentNames='" + studentNames + '\'' +
                ", teacherIds='" + teacherIds + '\'' +
                ", teacherNames='" + teacherNames + '\'' +
                ", winningLevel=" + winningLevel +
                ", winningLevelName='" + winningLevelName + '\'' +
                ", dengci=" + dengci +
                ", dengciName='" + dengciName + '\'' +
                ", pictureId='" + pictureId + '\'' +
                ", createTime=" + createTime +
                ", modiyTime='" + modiyTime + '\'' +
                ", isDelete=" + isDelete +
                ", isAuit=" + isAuit +
                ", shenqingren=" + shenqingren +
                ", schoolYear='" + schoolYear + '\'' +
                ", schoolTrem='" + schoolTrem + '\'' +
                ", shenqingName='" + shenqingName + '\'' +
                ", pictureList=" + pictureList +
                ", shuXing='" + shuXing + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
