package platform.szxyzxx.homewoke.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.io.Serializable;
import java.util.Date;

public class HomeWokePojo implements Serializable {
    private Integer id;
    private Integer schoolId;
    private Integer teamId;
    private Integer gradeId;

    private Integer teacherId;
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("教师名称")
    private String teachName;

    @ExcelColumnImport(index =0)
    @ExcelColumnExport("班级名称")
    private String teamName;
    /*
    * 科目id
    */
    private Integer subjectId;
    /**/
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("科目名称")
    private String subjectName;
    private Date createTime;
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("创建时间")
    private String createTimeTwo;
    private Date modieTime;
    private Integer isDelete;
    /*
    * 内容
    */
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("内容")
    private String text;
    /*
    *图片uuid
     */
    private  String pictureUuid;
    /*
     *图片url
     */
    private  String picturUrl;
    /*
    * 是否收过作业 0 没有 1收过
    */
    private Integer isStats;
    /*
    * 是否家作 0否，1是
    */
    private Integer isHome;
    /*
     * 学期
     */
    private String schoolTrem;
    /*
     * 学年
     */
    private String schoolYear;

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getCreateTimeTwo() {
        return createTimeTwo;
    }

    public void setCreateTimeTwo(String createTimeTwo) {
        this.createTimeTwo = createTimeTwo;
    }

    public Integer getIsHome() {
        return isHome;
    }

    public void setIsHome(Integer isHome) {
        this.isHome = isHome;
    }

    public Integer getIsStats() {
        return isStats;
    }

    public void setIsStats(Integer isStats) {
        this.isStats = isStats;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    @Override
    public String toString() {
        return "HomeWokePojo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", teamId=" + teamId +
                ", gradeId=" + gradeId +
                ", teacherId=" + teacherId +
                ", teachName='" + teachName + '\'' +
                ", subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", createTime=" + createTime +
                ", modieTime=" + modieTime +
                ", isDelete=" + isDelete +
                ", text='" + text + '\'' +
                ", pictureUuid='" + pictureUuid + '\'' +
                ", picturUrl='" + picturUrl + '\'' +
                '}';
    }

    public String getPictureUuid() {
        return pictureUuid;
    }

    public void setPictureUuid(String pictureUuid) {
        this.pictureUuid = pictureUuid;
    }

    public String getPicturUrl() {
        return picturUrl;
    }

    public void setPicturUrl(String picturUrl) {
        this.picturUrl = picturUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
