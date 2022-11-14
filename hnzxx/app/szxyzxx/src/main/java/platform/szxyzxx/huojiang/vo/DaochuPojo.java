package platform.szxyzxx.huojiang.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class DaochuPojo {
    /*
     *主题
     */
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("主题")
    private  String theme;
    /*
     *获奖时间
     */
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("获奖时间")
    private  String winningTime;
    /*
     *作评名称
     */
    @ExcelColumnImport(index =7)
    @ExcelColumnExport("作评名称")
    private  String nameWoke;
    /*
     *发奖单位
     */
    @ExcelColumnImport(index =10)
    @ExcelColumnExport("发奖单位")
    private  String allocated;
    /*
     *获奖学生name
     */
    @ExcelColumnImport(index =6)
    @ExcelColumnExport("获奖学生")
    private  String studentNames;
    /*
     *获奖教师name
     */
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("获奖教师")
    private  String teacherNames;
    /*
     *获奖级别name
     */
    @ExcelColumnImport(index =8)
    @ExcelColumnExport("获奖级别")
    private  String winningLevelName;
    /*
     *等次name
     */
    @ExcelColumnImport(index =9)
    @ExcelColumnExport("获奖等次")
    private  String dengciName;
    /*
     * 获奖属性
     */
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("获奖属性")
    private  String shuXing;
    /*
     * 获奖类型
     */
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("获奖类型")
    private  String type;
    /*
     * 得分
     */
    @ExcelColumnImport(index =11)
    @ExcelColumnExport("得分")
    private Integer score;
    /*
     * 绩效得分
     */
    @ExcelColumnImport(index =12)
    @ExcelColumnExport("绩效得分")
    private Integer jiXiaoDeFen;

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

    public String getStudentNames() {
        return studentNames;
    }

    public void setStudentNames(String studentNames) {
        this.studentNames = studentNames;
    }

    public String getTeacherNames() {
        return teacherNames;
    }

    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }

    public String getWinningLevelName() {
        return winningLevelName;
    }

    public void setWinningLevelName(String winningLevelName) {
        this.winningLevelName = winningLevelName;
    }

    public String getDengciName() {
        return dengciName;
    }

    public void setDengciName(String dengciName) {
        this.dengciName = dengciName;
    }

    public String getShuXing() {
        return shuXing;
    }

    public void setShuXing(String shuXing) {
        this.shuXing = shuXing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getJiXiaoDeFen() {
        return jiXiaoDeFen;
    }

    public void setJiXiaoDeFen(Integer jiXiaoDeFen) {
        this.jiXiaoDeFen = jiXiaoDeFen;
    }
}
