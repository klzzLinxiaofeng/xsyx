package platform.szxyzxx.ishangkelilu.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.io.Serializable;

public class HabitsDaoChu implements Serializable {
    private Integer id;
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("学号")
    private String xueHao;
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("学生名称")
    private String studentName;
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("评价教师")
    private  String teacherName;
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("班级")
    private String teamName;
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("科目名称")
    private String subjectName;
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("评价类型")
    private String type;
    private Integer score;
    @ExcelColumnImport(index =7)
    @ExcelColumnExport("加分")
    private Integer pingjiaJiafen;
    @ExcelColumnImport(index =8)
    @ExcelColumnExport("减分")
    private Integer pingjiaJianfen;
    @ExcelColumnImport(index =9)
    @ExcelColumnExport("评语")
    private String pingyu;
    private String schoolYear;
    private String schoolTrem;
    private Integer gradeId;
    @ExcelColumnImport(index =6)
    @ExcelColumnExport("评价时间")
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getXueHao() {
        return xueHao;
    }

    public void setXueHao(String xueHao) {
        this.xueHao = xueHao;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPingjiaJiafen() {
        return pingjiaJiafen;
    }

    public void setPingjiaJiafen(Integer pingjiaJiafen) {
        this.pingjiaJiafen = pingjiaJiafen;
    }

    public Integer getPingjiaJianfen() {
        return pingjiaJianfen;
    }

    public void setPingjiaJianfen(Integer pingjiaJianfen) {
        this.pingjiaJianfen = pingjiaJianfen;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }
}
